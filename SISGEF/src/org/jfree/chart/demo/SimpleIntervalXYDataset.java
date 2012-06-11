/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
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
 * ----------------------------
 * SimpleIntervalXYDataset.java
 * ----------------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: SimpleIntervalXYDataset.java,v 1.10 2004/05/06 08:38:54 mungady Exp $
 *
 * Changes (since 11-Oct-2002)
 * ---------------------------
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 05-May-2004 : Now extends AbstractIntervalXYDataset (DG);
 *
 */

package org.jfree.chart.demo;

import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;

/**
 * A quick and dirty sample dataset.
 */
public class SimpleIntervalXYDataset extends AbstractIntervalXYDataset 
                                     implements IntervalXYDataset {

    /** The start values. */
    private Double[] xStart = new Double[3];
    
    /** The end values. */
    private Double[] xEnd = new Double[3];

    /** The y values. */
    private Double[] yValues = new Double[3];

    /**
     * Creates a new dataset.
     */
    public SimpleIntervalXYDataset() {

        this.xStart[0] = new Double(0.0);
        this.xStart[1] = new Double(2.0);
        this.xStart[2] = new Double(3.5);

        this.xEnd[0] = new Double(2.0);
        this.xEnd[1] = new Double(3.5);
        this.xEnd[2] = new Double(4.0);

        this.yValues[0] = new Double(3.0);
        this.yValues[1] = new Double(4.5);
        this.yValues[2] = new Double(2.5);
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return the number of series in the dataset.
     */
    public int getSeriesCount() {
        return 1;
    }

    /**
     * Returns the name of a series.
     *
     * @param series the series (zero-based index).
     *
     * @return the series name.
     */
    public String getSeriesName(final int series) {
        return "Series 1";
    }

    /**
     * Returns the number of items in a series.
     *
     * @param series the series (zero-based index).
     *
     * @return the number of items within a series.
     */
    public int getItemCount(final int series) {
        return 3;
    }

    /**
     * Returns the x-value for an item within a series.
     * <P>
     * The implementation is responsible for ensuring that the x-values are presented in ascending
     * order.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return  the x-value for an item within a series.
     */
    public double getXValue(final int series, final int item) {
        return this.xStart[item].doubleValue();
    }

    /**
     * Returns the y-value for an item within a series.
     *
     * @param series  the series (zero-based index).
     * @param item  the item (zero-based index).
     *
     * @return the y-value for an item within a series.
     */
    public double getYValue(final int series, final int item) {
        return this.yValues[item].doubleValue();
    }

    /**
     * Returns the starting X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return the start x value.
     */
    public double getStartXValue(final int series, final int item) {
        return this.xStart[item].doubleValue();
    }

    /**
     * Returns the ending X value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return the end x value.
     */
    public double getEndXValue(final int series, final int item) {
        return this.xEnd[item].doubleValue();
    }

    /**
     * Returns the starting Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return the start y value.
     */
    public double getStartYValue(final int series, final int item) {
        return this.yValues[item].doubleValue();
    }

    /**
     * Returns the ending Y value for the specified series and item.
     *
     * @param series  the series (zero-based index).
     * @param item  the item within a series (zero-based index).
     *
     * @return the end y value.
     */
    public double getEndYValue(final int series, final int item) {
        return this.yValues[item].doubleValue();
    }

    /**
     * Registers an object for notification of changes to the dataset.
     *
     * @param listener  the object to register.
     */
    public void addChangeListener(final DatasetChangeListener listener) {
        // ignored
    }

    /**
     * Deregisters an object for notification of changes to the dataset.
     *
     * @param listener  the object to deregister.
     */
    public void removeChangeListener(final DatasetChangeListener listener) {
        // ignored
    }

	/* (non-Javadoc)
	 * @see org.jfree.data.general.AbstractSeriesDataset#getSeriesKey(int)
	 */
	public Comparable getSeriesKey(int series) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.IntervalXYDataset#getStartX(int, int)
	 */
	public Number getStartX(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.IntervalXYDataset#getEndX(int, int)
	 */
	public Number getEndX(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.IntervalXYDataset#getStartY(int, int)
	 */
	public Number getStartY(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.IntervalXYDataset#getEndY(int, int)
	 */
	public Number getEndY(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.XYDataset#getX(int, int)
	 */
	public Number getX(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfree.data.xy.XYDataset#getY(int, int)
	 */
	public Number getY(int series, int item) {
		// TODO Auto-generated method stub
		return null;
	}

}
