package de.hopf.mobile.trivialdice.letter;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.LetterDiceDrawable;

public class StartDice extends StartBase {
    
    @Override
    public void initDelegate() {
        Drawable drawable = new LetterDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 26, false);
    }    
}