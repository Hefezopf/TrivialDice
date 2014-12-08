package de.hopf.android.common;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import de.hopf.android.common.drawable.base.BaseDrawable;

@RunWith(RobolectricTestRunner.class)
public class DrawViewImageTest {

    private DrawViewImage view;

    @Before
    public void setup(){
//        Activity activity = Robolectric.buildActivity(Mockito.mock(Activity.class)).create().get();
//        Activity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
    	ContextData context = Mockito.mock(ContextData.class);
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(context.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));
        BaseDrawable drawable = Mockito.mock(BaseDrawable.class);
        view = new DrawViewImage(context, windowManager, 1, drawable, true);
    }

    @Test
    public void testOnDraw() {
        Canvas canvas = Mockito.mock(Canvas.class);
        view.onDraw(canvas);
    }

    @Test
    public void testOnTouch() {
        MotionEvent event = Mockito.mock(MotionEvent.class);
        view.onTouch(null, event);
    }

    @Test
    public void testDrawItem() {
        Canvas canvas = Mockito.mock(Canvas.class);
        view.drawItem(canvas);
    }
 }
