package de.hopf.android.trivialdice.doubling.lite;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.DoublingDiceDrawable;

public class StartDice extends StartBase {

    final private int NUM_64 = 5;
    
    @Override
    public void initDelegate() {
        Drawable drawable = new DoublingDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWuerfelRand);
        this.startDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, R.string.link, drawable, 6, NUM_64);
    }
}