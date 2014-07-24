package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import de.hopf.mobile.Point;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public abstract class BaseDrawable implements Drawable {

    protected void initDice(List<List<Point>> pointsDice) {
        pointsDice.clear();
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
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
