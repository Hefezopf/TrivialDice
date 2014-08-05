package de.hopf.android.common;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.base.BaseDrawable;

public class DrawViewImage extends DrawViewBase {
    private static final long serialVersionUID = 1L;
    
    public DrawViewImage(Context context, WindowManager windowManager, int hitMsgKey, Drawable drawable, boolean bLite) {
        super(context, windowManager, hitMsgKey, drawable, bLite);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void drawItem(Canvas canvas) {
        ((Data) this.getContext()).setNumber(new Integer((int) (Math.random() * 9999)));
        ((BaseDrawable)drawable).drawShape(this, paint, canvas, metrics, itemAmountType.getPointOne().getX(), itemAmountType.getPointOne().getY());
    }
    
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        ((Data) this.getContext()).setRolled(Boolean.TRUE);
        ((Data) this.getContext()).setInterrupted(Boolean.FALSE);
        invalidate();

        return true;
    }
}