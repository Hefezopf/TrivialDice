package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.hopf.mobile.Point;
import de.hopf.mobile.drawable.BaseDrawable;

public class ColorDiceDrawable extends BaseDrawable {

    private final List<List<Point>> pointsDiceOne = new ArrayList<List<Point>>(); 
        
    public ColorDiceDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
        super();
        initDice(pointsDiceOne);
        setupDice(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, 0, pointsDiceOne);
     }

    @Override
    public List<List<Point>> getDrawableList() {
        return pointsDiceOne;
    }
    
    private void setupDice(final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand,
            int offset, List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset + (kantenLaengeWuerfel / 2),
                pointsDice.get(0));
    }
    
    @Override
    public void drawContent(int number, Paint paint, Canvas canvas, final int kantenLaengeWuerfel, List<List<Point>> points) {
        switch (number) {
        case 0:
            paint.setColor(Color.CYAN);
            break;
        case 1:
            paint.setColor(Color.BLUE);
            break;
        case 2:
            paint.setColor(Color.RED);
            break;
        case 3:
            paint.setColor(Color.GREEN);
            break;
        case 4:
            paint.setColor(Color.WHITE);
            break;
        case 5:
            paint.setColor(Color.YELLOW);
            break;
        default:
        }

        drawPoints(paint, canvas, kantenLaengeWuerfel, points.get(0), kantenLaengeWuerfel / 3);
        paint.setColor(Color.WHITE);
    }        
}
