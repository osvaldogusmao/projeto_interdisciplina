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
 * --------------
 * SubSystem.java
 * --------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: SubSystem.java,v 1.3 2005/06/01 14:12:28 taqua Exp $
 *
 * Changes
 * -------
 * 10-Jul-2003 : Initial version
 * 07-Jun-2004 : Added JCommon header (DG);
 * 
 */

package org.jfree.base.modules;

import org.jfree.util.Configuration;

/**
 * A sub-system holds a separate collection of modules.
 * <p>
 * On a simple level, subsystems can be just libraries.
 * Libraries offering services need a controlled way to
 * initialize these services before dependent code starts
 * using the library. This can be achived by embedding the
 * library services into an own subsystem.
 *
 * @author Thomas Morgner
 */
public interface SubSystem {

    /**
     * Returns the global configuration.
     * 
     * @return The global configuration.
     */
    public Configuration getGlobalConfig();
    
    /**
     * Returns the package manager.
     * 
     * @return The package manager.
     */
    public PackageManager getPackageManager();

}
