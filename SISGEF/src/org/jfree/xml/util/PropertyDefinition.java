/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
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
 * -----------------------
 * PropertyDefinition.java
 * -----------------------
 * (C)opyright 2003-2005, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: PropertyDefinition.java,v 1.2 2005/03/04 10:01:44 taqua Exp $
 *
 * Changes 
 * -------
 * 24.09.2003 : Initial version
 *  
 */

package org.jfree.xml.util;

/**
 * A property definition.
 */
public class PropertyDefinition {
    
    /** The property name. */
    private String propertyName;
    
    /** The element name. */
    private String elementName;

    /**
     * Creates a new property definition.
     * 
     * @param propertyName  the property name.
     * @param elementName  the element name.
     */
    public PropertyDefinition(final String propertyName, final String elementName) {
        this.elementName = elementName;
        this.propertyName = propertyName;
    }

    /**
     * Returns the property name.
     * 
     * @return the property name.
     */
    public String getPropertyName() {
        return this.propertyName;
    }

    /**
     * Returns the element name.
     * 
     * @return the element name.
     */
    public String getElementName() {
        return this.elementName;
    }
    
}
