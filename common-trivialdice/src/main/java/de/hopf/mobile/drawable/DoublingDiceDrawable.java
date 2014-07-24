package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.hopf.mobile.Point;
import de.hopf.mobile.drawable.BaseDrawable;

public class DoublingDiceDrawable extends BaseDrawable {

    private final List<List<Point>> pointsDiceOne = new ArrayList<List<Point>>(); 
        
    public DoublingDiceDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
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
    public void drawContent(int number, Paint paint, Canvas canvas, int kantenLaengeWuerfel, List<List<Point>> points) {
        paint.setColor(Color.WHITE);
        String text = "";
        switch (number) {
        case 0:
            text = "2";
            break;
        case 1:
            text = "4";
            break;
        case 2:
            text = "8";
            break;
        case 3:
            text = "16";
            break;
        case 4:
            text = "32";
            break;
        case 5:
            text = "64";
            break;
        default:
            throw new IllegalArgumentException("Nummer nicht gültig!");
        }

        paint.setTextSize(kantenLaengeWuerfel / 2);
        for (Point point : points.get(0)) {
            canvas.drawText(text, point.getX(), point.getY() + (paint.getTextSize() / 3), paint);
        }
        paint.setTextSize(kantenLaengeWuerfel / 10);
    }
}
