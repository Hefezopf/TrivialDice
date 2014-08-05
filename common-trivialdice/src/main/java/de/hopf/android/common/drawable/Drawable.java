package de.hopf.android.common.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;

public interface Drawable {
    public List<List<List<Point>>> initDrawableList(ItemAmountType diceAmountType);
    
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, final int kantenLaenge);
}
