package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Test;

public class ItemAmountTypeTest
{
    @Test
    public void testToString() {
        ItemAmountType t = ItemAmountType.ONE;
        Assert.assertEquals(t.toString(), "ONE");
    }

    @Test
    public void testGetPointOne() {
        ItemAmountType t = ItemAmountType.ONE;
        Assert.assertEquals(t.getPointOne().toString(), "0, 0");
    }


    @Test
    public void testGetFaktor() {
        Assert.assertEquals(ItemAmountType.getFaktor(), 20);
    }
}
