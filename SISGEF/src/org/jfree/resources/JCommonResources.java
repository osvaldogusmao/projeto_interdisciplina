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
 * ---------------------
 * JCommonResources.java
 * ---------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: JCommonResources.java,v 1.10 2005/06/01 14:12:29 taqua Exp $
 *
 */

package org.jfree.resources;

import java.util.ListResourceBundle;

/**
 * Localised resources for the JCommon Class Library.
 *
 * @author David Gilbert
 */
public class JCommonResources extends ListResourceBundle {

    /**
     * Default constructor.
     */
    public JCommonResources() {
    }

    /**
     * Returns the array of strings in the resource bundle.
     * 
     * @return The resources.
     */
    public Object[][] getContents() {
        return CONTENTS;
    }

    /** The resources to be localised. */
    private static final Object[][] CONTENTS = {

        {"project.name",      "JCommon"},
        {"project.version",   "1.0.0-rc1"},
        {"project.info",      "http://www.jfree.org/jcommon/index.html"},
        {"project.copyright", 
            "(C)opyright 2000-2005, by Object Refinery Limited and Contributors"}

    };

}
