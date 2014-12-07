package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Test;

public class PointTest
{
    float x, y;

    @Test
    public void testToString() {
        Point p = new Point(1 ,2);
        Assert.assertEquals(p.toString(), "1, 2");
    }

    @Test
    public void testGetter() {
        Point p = new Point(1 ,2);
        Assert.assertEquals(p.getX(), 1);
        Assert.assertEquals(p.getY(), 2);
    }

}
