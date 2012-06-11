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
 * -------------------
 * XmlReadHandler.java
 * -------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: XmlReadHandler.java,v 1.2 2005/02/02 16:14:59 taqua Exp $
 *
 * Changes (from 25-Nov-2003)
 * --------------------------
 * 25-Nov-2003 : Added Javadocs (DG);
 *  
 */

package org.jfree.xml.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A handler for reading an XML element.
 */
public interface XmlReadHandler {
    
    /**
     * This method is called at the start of an element.
     * 
     * @param tagName  the tag name.
     * @param attrs  the attributes.
     * 
     * @throws SAXException if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    public void startElement(String tagName, Attributes attrs)
        throws SAXException, XmlReaderException;
    
    /**
     * This method is called to process the character data between element tags.
     * 
     * @param ch  the character buffer.
     * @param start  the start index.
     * @param length  the length.
     * 
     * @throws SAXException if there is a parsing error.
     */
    public void characters(char[] ch, int start, int length)
        throws SAXException;
    
    /**
     * This method is called at the end of an element.
     * 
     * @param tagName  the tag name.
     * 
     * @throws SAXException if there is a parsing error.
     * @throws XmlReaderException if there is a reader error.
     */
    public void endElement(String tagName)
        throws SAXException, XmlReaderException;
    
    /**
     * Returns the object for this element or null, if this element does
     * not create an object.
     * 
     * @return the object.
     * 
     * @throws XmlReaderException if there is a parsing error.
     */
    public Object getObject() throws XmlReaderException;
    
    /**
     * Initialise.
     * 
     * @param rootHandler  the root handler.
     * @param tagName  the tag name.
     */
    public void init(RootXmlReadHandler rootHandler, String tagName);

}
