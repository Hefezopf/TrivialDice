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
import android.view.View;
import android.view.WindowManager;
import de.hopf.android.common.drawable.base.BaseDiceDrawable;

@RunWith(RobolectricTestRunner.class)
public class DrawViewDiceTest {

    private DrawViewDice view;

    @Before
    public void setup(){
        ContextData context = Mockito.mock(ContextData.class);
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(context.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));
        BaseDiceDrawable drawable = Mockito.mock(BaseDiceDrawable.class);

        view = new DrawViewDice(context, windowManager, 1, 2, drawable, 8, true);
    }

    @Test
    public void testOnTouch() {
        View v = Mockito.mock(View.class);
        MotionEvent motionEvent = Mockito.mock(MotionEvent.class);
        view.onTouch(v, motionEvent);
    }

    @Test
    public void testOnDraw() {
        Canvas canvas = Mockito.mock(Canvas.class);
        view.onDraw(canvas);

        ContextData context = Mockito.mock(ContextData.class);
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getDisplayMetrics()).thenReturn(Mockito.mock(DisplayMetrics.class));
        Mockito.when(context.getResources()).thenReturn(resources);
        WindowManager windowManager = Mockito.mock(WindowManager.class);
        Mockito.when(windowManager.getDefaultDisplay()).thenReturn(Mockito.mock(Display.class));
        BaseDiceDrawable drawable = Mockito.mock(BaseDiceDrawable.class);

        view = new DrawViewDice(context, windowManager, 1, 2, drawable, 8, false);
        view.onDraw(canvas);
    }


 }
