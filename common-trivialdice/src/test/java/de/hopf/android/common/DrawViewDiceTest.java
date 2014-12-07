package de.hopf.android.common;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import de.hopf.android.common.drawable.Drawable;

public class DrawViewDiceTest {

    private DrawViewDice view;

    @Before
    public void setup(){
        Drawable drawable = Mockito.mock(Drawable.class);
        view = new DrawViewDice(null, null, 1, 2, drawable, 8, true);
    }

    @Ignore
    @Test
    public void testOnDraw() {
        view.onDraw(null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }
 }
