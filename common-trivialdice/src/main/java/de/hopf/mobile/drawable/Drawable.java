package de.hopf.mobile.drawable;

import java.util.List;

import de.hopf.mobile.Point;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Drawable {
    public List<List<Point>> getDrawableList();
    
    public void drawContent(int number, Paint paint, Canvas canvas, final int kantenLaengeWuerfel, List<List<Point>> points);
}
