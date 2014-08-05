package de.hopf.android.trivialdice.doubling;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.DoublingDiceDrawable;

public class StartDice extends StartBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new DoublingDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, drawable, 6, false);
    }
}