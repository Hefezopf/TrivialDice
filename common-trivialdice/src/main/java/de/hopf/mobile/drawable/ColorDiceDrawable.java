package de.hopf.mobile.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class ColorDiceDrawable extends BaseDrawable {

    public ColorDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        initDice(pointsDices, ItemAmountType.ONE);
        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, 0, pointsDices.get(0));
    }

    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
        if (diceAmountType == ItemAmountType.ONE) {
            initDice(pointsDices, ItemAmountType.ONE);
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getOffset1(), pointsDices.get(0));
        } else if (diceAmountType == ItemAmountType.TWO) {
            initDice(pointsDices, ItemAmountType.TWO);
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getOffset1(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getOffset2(), pointsDices.get(1));
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        return pointsDices;
    }

    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand, int offset,
            List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2), obererWürfelRand + offset + (kantenLaenge / 2),
                pointsDice.get(0));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, final int kantenLaenge,
            List<List<List<Point>>> points) {
        int index = 0;
        for (List<List<Point>> pointList : points) {
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

            drawPoints(paint, canvas, kantenLaenge, pointList.get(0), kantenLaenge / 3);
            paint.setColor(Color.WHITE);
        }
    }
}
