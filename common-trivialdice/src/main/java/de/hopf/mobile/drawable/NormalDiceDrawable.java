package de.hopf.mobile.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class NormalDiceDrawable extends BaseDrawable {

    public NormalDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        initDice(pointsDices, ItemAmountType.ONE);
        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getX(), ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));
     }

    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
        initDice(pointsDices, diceAmountType);
        if (diceAmountType == ItemAmountType.ONE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getX(), ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));
        } else if (diceAmountType == ItemAmountType.TWO) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointTwo().getY(), pointsDices.get(1));
        } else if (diceAmountType == ItemAmountType.THREE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointTwo().getY(), pointsDices.get(1));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.THREE.getPointThree().getY(), pointsDices.get(2));
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        return pointsDices;
    }
    
    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand,
            int offsetX, int offsetY, List<List<Point>> pointsDice) {
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
