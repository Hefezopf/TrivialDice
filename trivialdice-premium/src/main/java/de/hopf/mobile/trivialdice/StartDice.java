package de.hopf.mobile.trivialdice;

import de.hopf.android.common.StartDiceBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.NormalDiceDrawable;

public class StartDice extends StartDiceBase {
    
    @Override
    public void initDelegate() {
        Drawable drawable = new NormalDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6, false);
    }    
}