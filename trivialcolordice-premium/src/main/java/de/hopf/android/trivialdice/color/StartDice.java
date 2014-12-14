package de.hopf.android.trivialdice.color;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.ColorDiceDrawable;

public class StartDice extends StartBase {

    final private int GREEN = 3;
    
    @Override
    public void initDelegate() {
        Drawable drawable = new ColorDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWuerfelRand);  
        this.startDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6, GREEN);
    }
}