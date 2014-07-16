package de.hopf.android.trivialdice.doubling;

import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, DiceType.DICE_DOUBLING);
    }
}