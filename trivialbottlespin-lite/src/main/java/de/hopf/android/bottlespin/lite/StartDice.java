package de.hopf.android.bottlespin.lite;

import de.hopf.android.common.StartDiceBase;
import de.hopf.android.common.StartDiceDelegate;
import de.hopf.android.common.drawable.BottleSpinDrawable;
import de.hopf.android.common.drawable.Drawable;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new BottleSpinDrawable(this.getResources(), R.raw.bottle, kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, 0, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, R.string.link, drawable, 0, true);
    }
}