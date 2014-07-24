package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.mobile.Point;

public class NormalDiceDrawable extends BaseDrawable {

    private final List<List<Point>> pointsDiceOne = new ArrayList<List<Point>>(); 
       
    public NormalDiceDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
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

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 4),
                pointsDice.get(1));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(1));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 4),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset + (kantenLaengeWuerfel / 2),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(2));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 4),
                pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 4),
                pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset + (kantenLaengeWuerfel / 2),
                pointsDice.get(4));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 4),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offset + (kantenLaengeWuerfel / 2),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offset
                + (kantenLaengeWuerfel / 2), pointsDice.get(5));
    }

    @Override
    public void drawContent(int number, Paint paint, Canvas canvas, int kantenLaengeWuerfel, List<List<Point>> points) {
        List<Point> pointsList = null;

        switch (number) {
        case 0:
            pointsList = points.get(0);
            break;
        case 1:
            pointsList = points.get(1);
            break;
        case 2:
            pointsList = points.get(2);
            break;
        case 3:
            pointsList = points.get(3);
            break;
        case 4:
            pointsList = points.get(4);
            break;
        case 5:
            pointsList = points.get(5);
            break;
        default:
        }
        drawPoints(paint, canvas, kantenLaengeWuerfel, pointsList, kantenLaengeWuerfel / 10);
    }
}
