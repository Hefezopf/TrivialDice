package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import de.hopf.android.common.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class StartDelegateTest {

    private StartDelegate delegate;

    @Before
    public void setup(){
        Activity activity = Mockito.mock(Activity.class);
        Mockito.when(activity.getWindow()).thenReturn(Mockito.mock(Window.class));
        Mockito.when(activity.findViewById(Mockito.anyInt())).thenReturn(Mockito.mock(Button.class));
        Drawable drawable = Mockito.mock(Drawable.class);
        DrawViewBase drawView = Mockito.mock(DrawViewBase.class);
        ContextData context = Mockito.mock(ContextData.class);
        Mockito.when(drawView.getContext()).thenReturn(context);
        Mockito.when(context.getNumber()).thenReturn(null);
        drawView.onDraw(new Canvas());

        delegate = new StartDelegate(activity, 1, 2, 3, drawable, drawView);
    }

    @Test
    public void testNumber() {
        delegate.setNumber(1);
        Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Test
    public void testOnCreate() {
        delegate.onCreate(new Bundle());
    }

    @Test
    public void testShowSplashScreen() {
        delegate.showSplashScreen();
    }


    @Test
    public void testLite() {
        Activity activity = Mockito.mock(Activity.class);
        Drawable drawable = Mockito.mock(Drawable.class);
        StartDelegate d = new StartDelegate(activity, 1, 2, 3, 4, 5, 6, 7, drawable, null);
        Assert.assertNotNull(d);
    }

    @Test
    public void testOnSaveInstanceState() {
        delegate.onSaveInstanceState(new Bundle());
    }

    @Test
    public void testSetterGetter() {
        delegate.setCounter(1);
        Assert.assertEquals(1, delegate.getCounter());
        delegate.setInterrupted(true);
        Assert.assertEquals(true, delegate.hasInterrupted());
        delegate.setRolled(true);
        Assert.assertEquals(true, delegate.hasRolled());
        delegate.setNumber(1);
        Assert.assertEquals(1, delegate.getNumber().intValue());
        Assert.assertEquals(2, delegate.getCounter());
        delegate.setNumber2(2);
        Assert.assertEquals(2, delegate.getNumber2().intValue());
        delegate.setNumber3(3);
        Assert.assertEquals(3, delegate.getNumber3().intValue());
        delegate.setNumber4(4);
        Assert.assertEquals(4, delegate.getNumber4().intValue());
        delegate.setNumber5(5);
        Assert.assertEquals(5, delegate.getNumber5().intValue());
        delegate.setNumber6(6);
        Assert.assertEquals(6, delegate.getNumber6().intValue());
    }
 }
