package de.hopf.android.trivialdice.color;

import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;
import de.hopf.mobile.drawable.ColorDiceDrawable;
import de.hopf.mobile.drawable.Drawable;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new ColorDiceDrawable(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);  
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, DiceType.DICE_COLOR, drawable);
    }
}