package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import android.app.Activity;
import android.view.Window;
import android.widget.Button;
import de.hopf.android.common.drawable.Drawable;

public class StartDelegateTest {

    private StartDelegate delegate;

    @Before
    public void setup(){
        Activity activity = Mockito.mock(Activity.class);
        Mockito.when(activity.getWindow()).thenReturn(Mockito.mock(Window.class));
//      Mockito.when(activity.requestWindowFeature(Window.FEATURE_NO_TITLE)).thenReturn(true);
        Mockito.when(activity.findViewById(Mockito.anyInt())).thenReturn(Mockito.mock(Button.class));

        Drawable drawable = Mockito.mock(Drawable.class);
        delegate = new StartDelegate(activity, 1, 2, 3, drawable, 8, null);
    }

    @Test
    public void testNumber() {
        delegate.setNumber(1);
        Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Ignore
    @Test
    public void testOnCreate() {
        delegate.onCreate(null);
    }

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
 }
