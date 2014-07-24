package de.hopf.android.trivialdice.doubling.lite;

import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, DiceType.DICE_DOUBLING, R.string.link);
    }
}