package de.hopf.mobile;

import org.junit.Assert;
import org.junit.Test;

public class PointTest
{
	float x, y;

    @Test
	public void testToString() {
        Point p = new Point();
        p.setX(1);
        p.setY(2);
		Assert.assertEquals(p.toString(), "1.0, 2.0");
	}
}
