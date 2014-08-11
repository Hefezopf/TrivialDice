package de.hopf.android.common.drawable.base;

import java.util.List;

import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;

public abstract class BaseDiceDrawable extends BaseDrawable {

    protected abstract void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand, int offsetX, int offsetY, List<List<Point>> pointsDice);
        
    public BaseDiceDrawable(int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        initDice(ItemAmountType.ONE);
        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getX(), ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));        
    }

    @Override
    public List<List<List<Point>>> initDrawableList(ItemAmountType amountType) {
        initDice(amountType);
        if (amountType == ItemAmountType.ONE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, amountType.getPointOne().getX(), amountType.getPointOne().getY(), pointsDices.get(0));
        } else if (amountType == ItemAmountType.TWO) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getY(), pointsDices.get(1));
        } else if (amountType == ItemAmountType.THREE) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getY(), pointsDices.get(1));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointThree().getY(), pointsDices.get(2));
        } else if (amountType == ItemAmountType.FOUR) {
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointOne().getY(), pointsDices.get(0));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointTwo().getY(), pointsDices.get(1));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointThree().getY(), pointsDices.get(2));
            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointFour().getX(), kantenLaenge / ItemAmountType.getFaktor() * amountType.getPointFour().getY(), pointsDices.get(3));
        } else {
            throw new IllegalArgumentException("Unbekannter amountType: " + amountType);
        }
        return pointsDices;
    }
}