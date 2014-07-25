package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class NormalDiceDrawable extends BaseDrawable {

    private final List<List<List<Point>>> pointsDices = new ArrayList<List<List<Point>>>(); 
       
    public NormalDiceDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);
        initDice(pointsDices, ItemAmountType.ONE);
        setupDice(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, 0, pointsDices.get(0));
     }

    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
        if (diceAmountType == ItemAmountType.ONE) {
            initDice(pointsDices, ItemAmountType.ONE);
            setupDice(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getOffset1(), pointsDices.get(0));
        } else if (diceAmountType == ItemAmountType.TWO) {
            initDice(pointsDices, ItemAmountType.TWO);
            setupDice(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, kantenLaengeWuerfel / ItemAmountType.getFaktor() * ItemAmountType.TWO.getOffset1(), pointsDices.get(0));
            setupDice(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, kantenLaengeWuerfel / ItemAmountType.getFaktor() * ItemAmountType.TWO.getOffset2(), pointsDices.get(1));
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        return pointsDices;
    }
    
    private void setupDice(final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand,
            int offsetX, List<List<Point>> pointsDice) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 2),
                pointsDice.get(0));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 4),
                pointsDice.get(1));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(1));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 4),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 2),
                pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(2));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 4),
                pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 4),
                pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 2),
                pointsDice.get(4));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 4),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand + offsetX + (kantenLaengeWuerfel / 2),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand + offsetX
                + (kantenLaengeWuerfel / 2), pointsDice.get(5));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaengeWuerfel, List<List<List<Point>>> points) {
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
            drawPoints(paint, canvas, kantenLaengeWuerfel, pointsList, kantenLaengeWuerfel / 10);
        }
    }
}
