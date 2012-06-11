/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 * 
 * -----------------
 * AbstractBoot.java
 * -----------------
 * (C)opyright 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: AbstractBoot.java,v 1.10 2005/06/01 14:12:28 taqua Exp $
 *
 * Changes
 * -------
 * 07-Jun-2004 : Added source headers (DG);
 *
 */

package org.jfree.base;

import java.lang.reflect.Method;

import org.jfree.base.config.HierarchicalConfiguration;
import org.jfree.base.config.PropertyFileConfiguration;
import org.jfree.base.config.SystemPropertyConfiguration;
import org.jfree.base.modules.PackageManager;
import org.jfree.base.modules.SubSystem;
import org.jfree.util.Configuration;
import org.jfree.util.Log;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.ExtendedConfiguration;
import org.jfree.util.ExtendedConfigurationWrapper;

/**
 * The common base for all Boot classes.
 * <p>
 * This initializes the subsystem and all dependent subsystems.
 * Implementors of this class have to provide a public static
 * getInstance() method which returns a singleton instance of the
 * booter implementation.
 * <p>
 * Further creation of Boot object should be prevented using
 * protected or private constructors in that class, or proper
 * initialzation cannot be guaranteed.
 *
 * @author Thomas Morgner
 */
public abstract class AbstractBoot implements SubSystem {

    /** The configuration wrapper around the plain configuration. */
    private ExtendedConfigurationWrapper extWrapper;

    /** A packageManager instance of the package manager. */
    private PackageManager packageManager;
  
    /** Global configuration. */
    private Configuration globalConfig;

    /** A flag indicating whether the booting is currenly in progress. */
    private boolean bootInProgress;
    
    /** A flag indicating whether the booting is complete. */
    private boolean bootDone;

    /**
     * Default constructor.
     */
    protected AbstractBoot() {
    }

    /**
     * Returns the packageManager instance of the package manager.
     *
     * @return The package manager.
     */
    public synchronized PackageManager getPackageManager() {
        if (this.packageManager == null) {
            this.packageManager = PackageManager.createInstance(this);
        }
        return this.packageManager;
    }

    /**
     * Returns the global configuration.
     * 
     * @return The global configuration.
     */
    public synchronized Configuration getGlobalConfig() {
        if (this.globalConfig == null) {
            this.globalConfig = loadConfiguration();
            start();
        }
        return this.globalConfig;
    }

    /**
     * Checks, whether the booting is in progress.
     *
     * @return true, if the booting is in progress, false otherwise.
     */
    public final synchronized boolean isBootInProgress() {
        return this.bootInProgress;
    }

    /**
     * Checks, whether the booting is complete.
     *
     * @return true, if the booting is complete, false otherwise.
     */
    public final synchronized boolean isBootDone() {
        return this.bootDone;
    }

    /**
     * Loads the configuration. This will be called exactly once.
     * 
     * @return The configuration.
     */
    protected abstract Configuration loadConfiguration();

    /**
     * Starts the boot process.
     */
    public final void start() {

        synchronized(this) {
            if (isBootInProgress() || isBootDone()) {
                return;
            }
            this.bootInProgress = true;
        }

        // boot dependent libraries ...
        final BootableProjectInfo info = getProjectInfo();
        if (info != null) {
            Log.info (info.getName() + " " + info.getVersion());
            final BootableProjectInfo[] childs = info.getDependencies();
            for (int i = 0; i < childs.length; i++) {
                final AbstractBoot boot = loadBooter(childs[i].getBootClass());
                if (boot != null) {
                    boot.start();
                }
            }
        }
        performBoot();

        synchronized(this) {
            this.bootInProgress = false;
            this.bootDone = true;
        }
    }

    /**
     * Performs the boot.
     */
    protected abstract void performBoot();

    /**
     * Returns the project info.
     *
     * @return The project info.
     */
    protected abstract BootableProjectInfo getProjectInfo();

    /**
     * Loads the specified booter implementation.
     * 
     * @param classname  the class name.
     * 
     * @return The boot class.
     */
    protected AbstractBoot loadBooter(final String classname) {
        if (classname == null) {
            return null;
        }
        try {
            final Class c = ObjectUtilities.getClassLoader(getClass()).loadClass(classname);
            final Method m = c.getMethod("getInstance", null);
            return (AbstractBoot) m.invoke(null, null);
        }
        catch(Exception e) {
            Log.info ("Unable to boot dependent class: " + classname);
            return null;
        }
    }

    /**
     * Creates a default configuration setup, which loads its settings from
     * the static configuration (defaults provided by the developers of the
     * library) and the user configuration (settings provided by the deployer).
     * The deployer's settings override the developer's settings.
     *
     * If the parameter <code>addSysProps</code> is set to true, the system
     * properties will be added as third configuration layer. The system properties
     * configuration allows to override all other settings.
     *
     * @param staticConfig the resource name of the developers configuration
     * @param userConfig the resource name of the deployers configuration
     * @param addSysProps a flag defining whether to include the system properties into
     * the configuration.
     * @return the configured Configuration instance.
     */
    protected Configuration createDefaultHierarchicalConfiguration
        (final String staticConfig, final String userConfig, final boolean addSysProps)
    {
        final HierarchicalConfiguration globalConfig = new HierarchicalConfiguration();

        if (staticConfig != null) {
          final PropertyFileConfiguration rootProperty = new PropertyFileConfiguration();
          rootProperty.load(staticConfig);
          globalConfig.insertConfiguration(rootProperty);
          globalConfig.insertConfiguration(getPackageManager().getPackageConfiguration());
        }
        if (userConfig != null)
        {
          final PropertyFileConfiguration baseProperty = new PropertyFileConfiguration();
          baseProperty.load(userConfig);
          globalConfig.insertConfiguration(baseProperty);
        }
        final SystemPropertyConfiguration systemConfig = new SystemPropertyConfiguration();
        globalConfig.insertConfiguration(systemConfig);
        return globalConfig;
    }

    /**
     * Returns the global configuration as extended configuration.
     *
     * @return the extended configuration.
     */
    public synchronized ExtendedConfiguration getExtendedConfig ()
    {
      if (extWrapper == null)
      {
        extWrapper = new ExtendedConfigurationWrapper(getGlobalConfig());
      }
      return extWrapper;
    }
}
