package de.hopf.android.trivialdice.color.lite;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.ColorDiceDrawable;

public class StartDice extends StartBase {

    @Override
    public void initDelegate() {         
        Drawable drawable = new ColorDiceDrawable(kantenLaenge, linkerWuerfelRand, obererWürfelRand);        
        this.startDiceDelegate = new StartDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, R.string.link, drawable, 6, false);
    }
}