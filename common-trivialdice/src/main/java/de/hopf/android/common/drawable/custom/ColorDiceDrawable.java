package de.hopf.android.common.drawable.custom;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.base.BaseDiceDrawable;

public class ColorDiceDrawable extends BaseDiceDrawable {

    public ColorDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
    }

    @Override
    public void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand, int offsetX, int offsetY, List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(0));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, final int kantenLaenge) {
        int index = 0;
        for (List<List<Point>> pointList : pointsDices) {
            switch (numberList.get(index++)) {
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

            drawPoints(paint, canvas, kantenLaenge, pointList.get(0), kantenLaenge / 4);
            paint.setColor(Color.WHITE);
        }
    }
}
