package de.hopf.mobile.drawable;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class DoublingDiceDrawable extends BaseDrawable {

    private final List<List<List<Point>>> pointsDices = new ArrayList<List<List<Point>>>();
    
    public DoublingDiceDrawable(int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
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
            int offset, List<List<Point>> pointsDiceList) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset + (kantenLaengeWuerfel / 2),
                pointsDiceList.get(0));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaengeWuerfel, List<List<List<Point>>> points) {
        paint.setColor(Color.WHITE);
        int index = 0;
        for (List<List<Point>> pointList : points) {
            String text = "";
            switch (numberList.get(index++)) {
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
    //        for (List<List<Point>> pointList : points) {
                for (Point point : pointList.get(0)) {
                    canvas.drawText(text, point.getX(), point.getY() + (paint.getTextSize() / 3), paint);
                }
    //        }
            }
        paint.setTextSize(kantenLaengeWuerfel / 10);
    }
}
