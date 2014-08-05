package de.hopf.android.common.drawable.base;

import java.util.List;

import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;

public abstract class BaseDiceDrawable extends BaseDrawable {

    protected abstract void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand, int offsetX, int offsetY, List<List<Point>> pointsDice);
        
    public BaseDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
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
}