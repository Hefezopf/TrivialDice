package de.hopf.android.common.drawable.custom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import de.hopf.android.common.ContextData;
import de.hopf.android.common.DrawViewImage;
import de.hopf.android.common.ItemAmountType;

@RunWith(RobolectricTestRunner.class)
public class BottleSpinDiceDrawableTest extends BaseDiceDrawableTest {

    @Before
    public void setup() {
        drawable = new BottleSpinDrawable(Mockito.mock(Resources.class), 1, 2, 3, 4);
        drawable.initDrawableList(ItemAmountType.SIX);
    }

    @Override
    @Test
    public void testDrawShape() {
        DrawViewImage drawView = Mockito.mock(DrawViewImage.class);
        ContextData context = Mockito.mock(ContextData.class);
        Mockito.when(drawView.getContext()).thenReturn(context);
        Paint paint = Mockito.mock(Paint.class);
        Canvas canvas = Mockito.mock(Canvas.class);
        DisplayMetrics metrics = Mockito.mock(DisplayMetrics.class);

        ((BottleSpinDrawable) drawable).drawShape(drawView, paint, canvas, metrics, 1, 2);

        context.setNumber(1);

        ((BottleSpinDrawable) drawable).drawShape(drawView, paint, canvas, metrics, 1, 2);
    }
}
