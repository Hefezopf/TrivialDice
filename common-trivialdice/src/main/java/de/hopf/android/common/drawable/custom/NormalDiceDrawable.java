package de.hopf.android.common.drawable.custom;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.base.BaseDiceDrawable;

public class NormalDiceDrawable extends BaseDiceDrawable {

    public NormalDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
     }
      
    @Override
    public void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand, int offsetX, int offsetY, List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(0));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
                pointsDice.get(1));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(1));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(2));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
                pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(3));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
                pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(4));

        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge / 2), pointsDice.get(5));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaenge, List<List<List<Point>>> points) {
        int index = 0;

        for (List<List<Point>> pointList : points) {        
            List<Point> pointsList = null;
    
            switch (numberList.get(index++)) {
            case 0:
                pointsList = pointList.get(0);
                break;
            case 1:
                pointsList = pointList.get(1);
                break;
            case 2:
                pointsList = pointList.get(2);
                break;
            case 3:
                pointsList = pointList.get(3);
                break;
            case 4:
                pointsList = pointList.get(4);
                break;
            case 5:
                pointsList = pointList.get(5);
                break;
            default:
            }
            drawPoints(paint, canvas, kantenLaenge, pointsList, kantenLaenge / 10);
        }
    }
}
