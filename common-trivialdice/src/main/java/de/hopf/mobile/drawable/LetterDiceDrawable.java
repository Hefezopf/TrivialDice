package de.hopf.mobile.drawable;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import de.hopf.mobile.ItemAmountType;
import de.hopf.mobile.Point;

public class LetterDiceDrawable extends BaseDrawable {
       
    public LetterDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
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

        addPoint(linkerWuerfelRand + (kantenLaenge / 4), obererWürfelRand + offsetX + (kantenLaenge / 4),
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
        addPoint(linkerWuerfelRand + (kantenLaenge / 4 + offsetX), obererWürfelRand + offsetY + (kantenLaenge / 2),
                pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
                + (kantenLaenge / 2), pointsDice.get(5));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaenge, List<List<List<Point>>> points) {
        paint.setColor(Color.WHITE);
        int index = 0;
        for (List<List<Point>> pointList : points) {
            String text = "";
            switch (numberList.get(index++)) {
            case 0:
                text = "A";
                break;
            case 1:
                text = "B";
                break;
            case 2:
                text = "C";
                break;
            case 3:
                text = "D";
                break;
            case 4:
                text = "E";
                break;
            case 5:
                text = "F";
                break;
            case 6:
                text = "G";
                break;
            case 7:
                text = "H";
                break;
            case 8:
                text = "I";
                break;
            case 9:
                text = "J";
                break;
            case 10:
                text = "K";
                break;
            case 11:
                text = "L";
                break;
            case 12:
                text = "M";
                break;
            case 13:
                text = "N";
                break;
            case 14:
                text = "O";
                break;
            case 15:
                text = "P";
                break;
            case 16:
                text = "Q";
                break;
            case 17:
                text = "R";
                break;
            case 18:
                text = "S";
                break;
            case 19:
                text = "T";
                break;
            case 20:
                text = "U";
                break;
            case 21:
                text = "V";
                break;
            case 22:
                text = "W";
                break;
            case 23:
                text = "X";
                break;
            case 24:
                text = "Y";
                break;
            case 25:
                text = "Z";
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
