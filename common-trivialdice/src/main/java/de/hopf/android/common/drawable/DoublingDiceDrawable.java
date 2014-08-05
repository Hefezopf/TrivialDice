package de.hopf.android.common.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;

public class DoublingDiceDrawable extends BaseDrawable {

    public DoublingDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        initDice(pointsDices, ItemAmountType.ONE);
        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getX(), ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));
     }

    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
        initDice(pointsDices, diceAmountType);
        if (diceAmountType == ItemAmountType.ONE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, diceAmountType.getPointOne().getX(), diceAmountType.getPointOne().getY(), pointsDices.get(0));
        } else if (diceAmountType == ItemAmountType.TWO) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getY(), pointsDices.get(1));
        } else if (diceAmountType == ItemAmountType.THREE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getY(), pointsDices.get(1));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointThree().getY(), pointsDices.get(2));
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        return pointsDices;
    }
    
    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand,
            int offsetX, int offsetY, List<List<Point>> pointsDiceList) {
        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
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
