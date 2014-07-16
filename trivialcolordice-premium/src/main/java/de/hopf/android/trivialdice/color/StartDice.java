package de.hopf.android.trivialdice.color;

import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, 0, 0, 0,
                false, DiceType.DICE_COLOR);
    }
}