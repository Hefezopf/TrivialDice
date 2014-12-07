package de.hopf.android.common;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hopf.android.common.drawable.Drawable;

public class DrawViewImageTest {

    DrawViewImage view;

    @Before
    public void setup(){
        Drawable drawable = Mockito.mock(Drawable.class);
        view = new DrawViewImage(null, null, 1, drawable, true);
    }

    @Test
    public void testOnDraw() {
        view.onDraw(null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Test
    public void testOnTouch() {
        view.onTouch(null, null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Test
    public void testDrawItem() {
        view.drawItem(null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }
 }
