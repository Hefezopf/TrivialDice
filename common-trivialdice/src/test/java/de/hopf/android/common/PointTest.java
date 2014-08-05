package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Test;

import de.hopf.android.common.Point;

public class PointTest
{
	float x, y;

    @Test
	public void testToString() {
        Point p = new Point(1 ,2);
		Assert.assertEquals(p.toString(), "1, 2");
	}
}
