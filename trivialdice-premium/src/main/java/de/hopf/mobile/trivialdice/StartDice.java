package de.hopf.mobile.trivialdice;

import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;
import de.hopf.mobile.drawable.Drawable;
import de.hopf.mobile.drawable.NormalDiceDrawable;

public class StartDice extends StartDiceBase {
    
    @Override
    public void initDelegate() {
        Drawable drawable = new NormalDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6, false);
    }    
}