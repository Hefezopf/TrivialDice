package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class NormalDiceDrawable extends BaseDrawable {

    private final List<List<List<Point>>> pointsDices = new ArrayList<List<List<Point>>>(); 
       
    public NormalDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
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
    
    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand,
            int offsetX, List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2), obererWürfelRand + offsetX + (kantenLaenge / 2),
                pointsDice.get(0));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
                pointsDice.get(1));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(1));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaenge / 2), obererWürfelRand + offsetX + (kantenLaenge / 2),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(2));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
                pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(3));

        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
                pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaenge / 2), obererWürfelRand + offsetX + (kantenLaenge / 2),
                pointsDice.get(4));

        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaenge / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX
                + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 2),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4), obererWürfelRand + offsetX
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
