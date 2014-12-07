package de.hopf.android.bottlespin.lite;

import de.hopf.android.common.DrawViewImage;
import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartImageDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.BottleSpinDrawable;

public class StartDice extends StartBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new BottleSpinDrawable(this.getResources(), R.raw.bottle, kantenLaenge, linkerWuerfelRand, obererWuerfelRand);
        DrawViewImage drawViewImage = new DrawViewImage(this, this.getWindowManager(), R.string.hit, drawable, true);
        this.startDelegate = new StartImageDelegate(this, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, R.string.link, drawable, drawViewImage);
    }
}