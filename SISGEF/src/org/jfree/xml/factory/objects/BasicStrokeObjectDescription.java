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
 * ---------------------------------
 * BasicStrokeObjectDescription.java
 * ---------------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: BasicStrokeObjectDescription.java,v 1.1 2004/07/15 14:58:42 mungady Exp $
 *
 * Changes (from 19-Feb-2003)
 * -------------------------
 * 19-Feb-2003 : Added standard header and Javadocs (DG);
 * 29-Apr-2003 : Destilled from the JFreeReport project and moved into JCommon
 *
 */

package org.jfree.xml.factory.objects;

import java.awt.BasicStroke;

/**
 * An object-description for a <code>BasicStroke</code> object.
 *
 * @author Thomas Morgner
 */
public class BasicStrokeObjectDescription extends AbstractObjectDescription {

    /**
     * Creates a new object description.
     */
    public BasicStrokeObjectDescription() {
        super(BasicStroke.class);
        setParameterDefinition("value", String.class);
    }

    /**
     * Returns a parameter as a float.
     *
     * @param param  the parameter name.
     *
     * @return The float value.
     */
    private float getFloatParameter(final String param) {
        final String p = (String) getParameter(param);
        if (p == null) {
            return 0;
        }
        try {
            return Float.parseFloat(p);
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * Creates a new <code>BasicStroke</code> object based on this description.
     *
     * @return The <code>BasicStroke</code> object.
     */
    public Object createObject() {
        final float width = getFloatParameter("value");
        return new BasicStroke(width);
    }

    /**
     * Sets the parameters for this description to match the supplied object.
     *
     * @param o  the object (instance of <code>BasicStroke</code> required).
     *
     * @throws ObjectFactoryException if the supplied object is not an instance of
     *         <code>BasicStroke</code>.
     */
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof BasicStroke)) {
            throw new ObjectFactoryException("Expected object of type BasicStroke");
        }
        final BasicStroke bs = (BasicStroke) o;
        setParameter("value", String.valueOf(bs.getLineWidth()));
    }
}
