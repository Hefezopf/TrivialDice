package de.hopf.android.common;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import de.hopf.android.common.drawable.Drawable;

public abstract class DrawViewBase extends View implements OnTouchListener, Serializable {
    private static final long serialVersionUID = 1L;

    protected final Paint paint = new Paint();
    protected final DisplayMetrics metrics = new DisplayMetrics();
    protected ItemAmountType itemAmountType = ItemAmountType.ONE;
    protected final int hitMsgKey;
    protected final Drawable drawable; 
    protected final boolean bLite;
    
    abstract protected void drawItem(Canvas canvas);
    
    public DrawViewBase(Context context, WindowManager windowManager, int hitMsgKey, Drawable drawable, boolean bLite) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        this.hitMsgKey = hitMsgKey;
        this.drawable = drawable; 
        this.bLite = bLite;

        windowManager.getDefaultDisplay().getMetrics(metrics);
        drawable.initDrawableList(itemAmountType);
        
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.LEFT);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCopyright(canvas);
        drawHitMessage(canvas);
        drawItem(canvas); // abstract
    }

    private void drawCopyright(Canvas canvas) {
        if(bLite){
            int kantenLaenge = getWidth() / 2;
            paint.setTextSize(metrics.widthPixels / 20);        
            paint.setTextAlign(Align.LEFT);
            canvas.drawText("\u00A9" + " hopf-it.de", 10, kantenLaenge / 10, paint);
        }
    }

    private void drawHitMessage(Canvas canvas) {
        if(((Data) this.getContext()).getNumber() == null){ // Nur beim Ersten mal anzeigen
            final int kantenLaenge = getWidth() / 2;
            int linkesEck = (metrics.widthPixels - kantenLaenge) / 2;
            int oberesEck = (metrics.heightPixels - kantenLaenge) / 2;
    
            String hit_text = this.getContext().getString(hitMsgKey);
            paint.setTextAlign(Align.CENTER);
            paint.setTextSize(metrics.widthPixels / 20);
            
            canvas.drawText(hit_text, linkesEck + (kantenLaenge / 2), oberesEck + kantenLaenge
                    + (kantenLaenge / 10), paint);
        }
    }    
}