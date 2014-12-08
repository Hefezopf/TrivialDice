package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.base.BaseDiceDrawable;

@RunWith(RobolectricTestRunner.class)
public class StartDiceDelegateTest {

    private StartDiceDelegate delegate;

    @Before
    public void setup(){
        Activity activity = Mockito.mock(Activity.class);
        Mockito.when(activity.getWindow()).thenReturn(Mockito.mock(Window.class));
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(activity.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));        
        Mockito.when(activity.getWindowManager()).thenReturn(windowManager);
        BaseDiceDrawable drawable = Mockito.mock(BaseDiceDrawable.class);

        delegate = new StartDiceDelegate(activity, 1, 2, 3, drawable, 8);
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

    @Ignore
    @Test
    public void testShowSplashScreen() {
        delegate.showSplashScreen();
    }


    @Test
    public void testLite() {
        Activity activity = Mockito.mock(Activity.class);
        Drawable drawable = Mockito.mock(Drawable.class);
        StartDelegate d = new StartDelegate(activity, 1, 2, 3, 4, 5, 6, 7, drawable, 8, null);
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
