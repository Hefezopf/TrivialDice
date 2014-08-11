package de.hopf.android.trivialdice.doubling;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.DoublingDiceDrawable;

public class StartDice extends StartBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new DoublingDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWuerfelRand);
        this.startDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6);
    }
}