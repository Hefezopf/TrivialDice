package de.hopf.android.common.drawable.base;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import de.hopf.android.common.DrawView;
import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.Drawable;

public abstract class BaseDrawable implements Drawable {

    protected final List<List<List<Point>>> pointsDices = new ArrayList<List<List<Point>>>(); 
    
    protected int kantenLaenge;
    protected int linkerWuerfelRand;
    protected int obererWürfelRand;
  
    public BaseDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super();
        this.kantenLaenge = kantenLaenge;
        this.linkerWuerfelRand = linkerWuerfelRand;
        this.obererWürfelRand = obererWürfelRand;
    }
    
    protected void initDice(List<List<List<Point>>> pointsDice, ItemAmountType diceAmountType) {
        pointsDice.clear();
        if (diceAmountType == ItemAmountType.ONE) {
            pointsDice.add(new ArrayList<List<Point>>()); 
        } else if (diceAmountType == ItemAmountType.TWO) {
            pointsDice.add(new ArrayList<List<Point>>()); 
            pointsDice.add(new ArrayList<List<Point>>()); 
        } else if (diceAmountType == ItemAmountType.THREE) {
            pointsDice.add(new ArrayList<List<Point>>()); 
            pointsDice.add(new ArrayList<List<Point>>()); 
            pointsDice.add(new ArrayList<List<Point>>()); 
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        
        for (List<List<Point>> pointList : pointsDice) {
            pointList.clear();
            // TODO ???
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
        }
    }     
    
    public void drawShape(DrawView dv, Paint paint, Canvas canvas, DisplayMetrics metrics, int offsetX, int offsetY) {
        int linkesEck = (metrics.widthPixels) / 2 + offsetX;
        int oberesEck = (metrics.heightPixels) / 2 + offsetY;

        float width = metrics.widthPixels;
        float height = metrics.heightPixels;
        float radius;

        if (width > height) {
            radius = height / 4;
        } else {
            radius = width / 4;
        }
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        final RectF rect = new RectF();
        rect.set(linkesEck - radius, oberesEck - radius, linkesEck + radius, oberesEck + radius);

        canvas.drawRoundRect(rect, 10, 10, paint);
        paint.setStyle(Paint.Style.FILL);
    }
    
    protected void addPoint(int x, int y, List<Point> points) {
        Point point = new Point(x, y);
        points.add(point);
    }
    
    protected void drawPoints(Paint paint, Canvas canvas, final int kantenLaenge, List<Point> points, int radius) {
        for (Point point : points) {
            canvas.drawCircle(point.getX(), point.getY(), radius, paint);
        }
    }        
}
