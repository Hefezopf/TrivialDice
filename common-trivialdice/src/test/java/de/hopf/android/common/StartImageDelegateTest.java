package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import de.hopf.android.common.drawable.base.BaseDrawable;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class StartImageDelegateTest {

    @Test
    public void testPremium() {
        Activity activity = Mockito.mock(Activity.class);
        ContextData context = Mockito.mock(ContextData.class);
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(context.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));
        BaseDrawable drawable = Mockito.mock(BaseDrawable.class);
        
        DrawViewImage drawViewImage = new DrawViewImage(context, windowManager, 1, drawable, false);
        StartImageDelegate d = new StartImageDelegate(activity, 1, 2, drawable, drawViewImage);
        Assert.assertNotNull(d);
        d.setNumber(1);
    }
 }
