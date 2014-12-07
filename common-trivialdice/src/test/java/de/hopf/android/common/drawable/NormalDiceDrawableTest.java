package de.hopf.android.common.drawable;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.custom.NormalDiceDrawable;

public class NormalDiceDrawableTest {

    NormalDiceDrawable drawable;

    @Before
    public void setup(){
        drawable = new NormalDiceDrawable(1, 2, 3);
        drawable.initDrawableList(ItemAmountType.SIX);
    }

    @Ignore
    @Test
    public void testSetupDice() {
        List<List<Point>> list = new ArrayList<List<Point>>();
        List<Point> listInner = new ArrayList<Point>();
        listInner.add(new Point(1,1));
        listInner.add(new Point(2,2));
        list.add(listInner);
        drawable.setupDice(1 ,2, 3, 4, 5, list);
    }

 }
