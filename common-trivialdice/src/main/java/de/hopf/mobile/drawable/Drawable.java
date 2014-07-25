package de.hopf.mobile.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public interface Drawable {
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType);
    
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, final int kantenLaenge, List<List<List<Point>>> points);
}
