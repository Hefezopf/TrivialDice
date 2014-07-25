package de.hopf.mobile.trivialdice.letter;

import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;
import de.hopf.mobile.drawable.Drawable;
import de.hopf.mobile.drawable.LetterDiceDrawable;

public class StartDice extends StartDiceBase {
    
    @Override
    public void initDelegate() {
        Drawable drawable = new LetterDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 26);
    }    
}