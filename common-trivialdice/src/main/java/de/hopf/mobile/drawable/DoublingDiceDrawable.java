package de.hopf.mobile.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class DoublingDiceDrawable extends BaseDrawable {

    public DoublingDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        initDice(pointsDices, ItemAmountType.ONE);
        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, 0, pointsDices.get(0));
     }

    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
        if (diceAmountType == ItemAmountType.ONE) {
            initDice(pointsDices, ItemAmountType.ONE);
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));
        } else if (diceAmountType == ItemAmountType.TWO) {
            initDice(pointsDices, ItemAmountType.TWO);
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * ItemAmountType.TWO.getPointTwo().getY(), pointsDices.get(1));
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        return pointsDices;
    }
    
    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand,
            int offset, List<List<Point>> pointsDiceList) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2), obererWürfelRand + offset + (kantenLaenge / 2),
                pointsDiceList.get(0));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaenge, List<List<List<Point>>> points) {
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
    
            paint.setTextSize(kantenLaenge / 2);
            paint.setTextAlign(Align.CENTER);
            for (Point point : pointList.get(0)) {
                canvas.drawText(text, point.getX(), point.getY() + (paint.getTextSize() / 3), paint);
            }
            }
        paint.setTextSize(kantenLaenge / 10);
    }
}
