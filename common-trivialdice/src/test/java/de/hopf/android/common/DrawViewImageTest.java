package de.hopf.android.common;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import de.hopf.android.common.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
public class DrawViewImageTest {

    private DrawViewImage view;

    @Before
    public void setup(){
//        Activity activity = Robolectric.buildActivity(Mockito.mock(Activity.class)).create().get();

//        Activity activity = Robolectric.buildActivity(LoginActivity.class).create().get();

        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(context.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));
        Drawable drawable = Mockito.mock(Drawable.class);
        view = new DrawViewImage(context, windowManager, 1, drawable, true);
    }

    @Ignore
    @Test
    public void testOnDraw() {
        Canvas canvas = Mockito.mock(Canvas.class);
        view.onDraw(canvas);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Ignore
    @Test
    public void testOnTouch() {
        view.onTouch(null, null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }

    @Ignore
    @Test
    public void testDrawItem() {
        view.drawItem(null);
        //Assert.assertEquals(delegate.getNumber().longValue(), 1);
    }
 }
