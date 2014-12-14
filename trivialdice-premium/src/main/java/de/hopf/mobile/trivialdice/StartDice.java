package de.hopf.mobile.trivialdice;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.NormalDiceDrawable;

public class StartDice extends StartBase {
    
    final private int NUM_64 = 5;    
    @Override
    public void initDelegate() {
        Drawable drawable = new NormalDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWuerfelRand);
        this.startDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6, NUM_64);
    }    
}