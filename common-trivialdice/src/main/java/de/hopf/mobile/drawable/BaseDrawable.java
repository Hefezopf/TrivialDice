package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import de.hopf.mobile.DiceAmountType;
import de.hopf.mobile.Point;

public abstract class BaseDrawable implements Drawable {

    protected int kantenLaengeWuerfel;
    protected int linkerWuerfelRand;
    protected int obererWürfelRand;
  
    public BaseDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
        super();
        this.kantenLaengeWuerfel = kantenLaengeWuerfel;
        this.linkerWuerfelRand = linkerWuerfelRand;
        this.obererWürfelRand = obererWürfelRand;
    }
    
    protected void initDice(List<List<List<Point>>> pointsDice, DiceAmountType diceAmountType) {
        pointsDice.clear();
        if (diceAmountType == DiceAmountType.ONE) {
            pointsDice.add(new ArrayList<List<Point>>()); 
        } else if (diceAmountType == DiceAmountType.TWO) {
            pointsDice.add(new ArrayList<List<Point>>()); 
            pointsDice.add(new ArrayList<List<Point>>()); 
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        for (List<List<Point>> pointList : pointsDice) {
            pointList.clear();
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
            pointList.add(new ArrayList<Point>());
        }
    }     
    
    public void drawDiceBorder(Paint paint, Canvas canvas, DisplayMetrics metrics, final int kantenLaengeWuerfel, int offset) {
        int linkesEck = (metrics.widthPixels) / 2;
        int oberesEck = (metrics.heightPixels) / 2 + offset;

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
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        points.add(point);
    }
    
    protected void drawPoints(Paint paint, Canvas canvas, final int kantenLaengeWuerfel, List<Point> points, int radius) {
        for (Point point : points) {
            canvas.drawCircle(point.getX(), point.getY(), radius, paint);
        }
    }        
}
