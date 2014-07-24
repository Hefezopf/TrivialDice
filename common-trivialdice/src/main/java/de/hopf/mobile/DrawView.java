package de.hopf.mobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import de.hopf.mobile.drawable.BaseDrawable;
import de.hopf.mobile.drawable.Drawable;

public class DrawView extends View implements OnTouchListener, Serializable {
    private static final long serialVersionUID = 1L;

    private List<List<List<Point>>> pointsDices;

    private final WindowManager wm;
    private Paint paint;
    private MediaPlayer mp;
    private DisplayMetrics metrics;

    private final int diceSoundKey;
    private final int hitMsgKey;

    private boolean soundOn = true;
    private Bitmap soundBitmap;

    private DiceAmountType diceAmountType = DiceAmountType.ONE;
    private Bitmap amountDiceBitmap;
    
    Drawable drawable;
    
    public DrawView(Context context, WindowManager wm, int diceSoundKey, int hitMsgKey, Drawable drawable) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        if (mp == null) {
            mp = MediaPlayer.create(this.getContext(), diceSoundKey);
        }
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;

        if (paint == null) {
            paint = new Paint();
        }
        this.wm = wm;

        if (metrics == null) {
            metrics = new DisplayMetrics();
        }
        this.wm.getDefaultDisplay().getMetrics(metrics);

        final int kantenLaengeWuerfel = metrics.widthPixels / 2;

        paint.setColor(Color.WHITE);
        paint.setTextSize(kantenLaengeWuerfel / 10);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.LEFT);

        this.drawable = drawable;
        pointsDices = drawable.getDrawableList(diceAmountType);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (metrics == null) {
            metrics = new DisplayMetrics();
        }
        this.wm.getDefaultDisplay().getMetrics(metrics);
        final int kantenLaengeWuerfel = metrics.widthPixels / 2;

        drawCopyright(canvas, kantenLaengeWuerfel);
        drawHitMessage(canvas, kantenLaengeWuerfel);
        drawSpeakerBitmap(canvas);
        drawAmountDiceBitmap(canvas);
        playSound();

        List<Integer> numberList = new ArrayList<Integer>();
        if (diceAmountType == DiceAmountType.ONE) {
            numberList.add(calculateNumber()); 
        } else if (diceAmountType == DiceAmountType.TWO) {
            numberList.add(calculateNumber()); 
            numberList.add(calculateNumber2());
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
        
        drawCompleteDice(numberList, canvas, kantenLaengeWuerfel, 0, pointsDices);
    }

    private int calculateNumber() {
        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber(new Integer((int) (Math.random() * 6)));
        }

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber() == null) {
            ((Data) this.getContext()).setNumber(0);
        }

        return ((Data) this.getContext()).getNumber();
    }

    private int calculateNumber2() {
        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber2(new Integer((int) (Math.random() * 6)));
        }

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber2() == null) {
            ((Data) this.getContext()).setNumber2(0);
        }

        return ((Data) this.getContext()).getNumber2();
    }

    private void drawCompleteDice(List<Integer> numberList, Canvas canvas, final int kantenLaengeWuerfel, int offset,
            List<List<List<Point>>> points) {
        if (diceAmountType == DiceAmountType.ONE) {
            ((BaseDrawable)drawable).drawDiceBorder(paint, canvas, metrics, kantenLaengeWuerfel, 0);
            drawable.drawContent(numberList, paint, canvas, kantenLaengeWuerfel, points);        
        } else if (diceAmountType == DiceAmountType.TWO) {
            ((BaseDrawable)drawable).drawDiceBorder(paint, canvas, metrics, kantenLaengeWuerfel, -kantenLaengeWuerfel / 20 * 13);
            ((BaseDrawable)drawable).drawDiceBorder(paint, canvas, metrics, kantenLaengeWuerfel, kantenLaengeWuerfel / 20 * 8);
            drawable.drawContent(numberList, paint, canvas, kantenLaengeWuerfel, points);        
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
    }

    private void drawCopyright(Canvas canvas, final int kantenLaengeWuerfel) {
        paint.setTextAlign(Align.LEFT);
        canvas.drawText("\u00A9" + " hopf-it.de", 10, kantenLaengeWuerfel / 10, paint);
    }

    private void drawHitMessage(Canvas canvas, final int kantenLaengeWuerfel) {
        int linkesEck = (metrics.widthPixels - kantenLaengeWuerfel) / 2;
        int oberesEck = (metrics.heightPixels - kantenLaengeWuerfel) / 2;

        String hit_text = this.getContext().getString(hitMsgKey);
        paint.setTextAlign(Align.CENTER);

        if (diceAmountType == DiceAmountType.ONE) {
            canvas.drawText(hit_text, linkesEck + (kantenLaengeWuerfel / 2), oberesEck + kantenLaengeWuerfel
                    + (kantenLaengeWuerfel / 10), paint);
        } else if (diceAmountType == DiceAmountType.TWO) {
            canvas.drawText(hit_text, linkesEck + (kantenLaengeWuerfel / 2), oberesEck + kantenLaengeWuerfel
                    + (kantenLaengeWuerfel / 2), paint);
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }
    }

    private void playSound() {
        if (soundOn && ((Data) this.getContext()).hasRolled().equals(Boolean.TRUE)) {
            if (mp == null) {
                mp = MediaPlayer.create(this.getContext(), diceSoundKey);
            } else if (mp != null) { // Reported Bug v1.5 Dec 23, 2010 2:59:25
                                     // PM
                mp.setVolume(2f, 2f);
                mp.start();
            }

            ((Data) this.getContext()).setRolled(Boolean.FALSE);

            // Rausgenommen V1.28 am 17.7.2ß14
            // invalidate();
            // return;
            // Rausgenommen V1.28 am 17.7.2ß14

        } else {
            // Nicht beim Ersten mal!
            if (((Data) this.getContext()).getNumber() != null) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawSpeakerBitmap(Canvas canvas) {
        canvas.drawBitmap(getSoundBitmap(), getLeftSoundBitmapPos(metrics.widthPixels),
                getTopSoundBitmapPos(metrics.heightPixels), paint);
    }

    private Bitmap getSoundBitmap() {
        if (soundOn) {
            soundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode_off);
        } else {
            soundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode);
        }

        return soundBitmap;
    }

    private Bitmap getAmountDiceBitmap() {
        if (diceAmountType == DiceAmountType.ONE) {
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_star_big_off);
        } else if (diceAmountType == DiceAmountType.TWO) {
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_star_big_on);
        } else {
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }

        return amountDiceBitmap;
    }

    private void drawAmountDiceBitmap(Canvas canvas) {
        canvas.drawBitmap(getAmountDiceBitmap(), getLeftAmountDiceBitmapPos(metrics.widthPixels),
                getTopAmountDiceBitmapPos(metrics.heightPixels), paint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        // Toggle Sound
        if (isSoundOnBitmapTouched(event)) {
            soundOn = !soundOn;
            ((Data) this.getContext()).setRolled(Boolean.FALSE);
            ((Data) this.getContext()).setInterrupted(Boolean.TRUE);
            invalidate();
            return true;
        }

        // Toggle Ansicht
        if (isAmountDiceBitmapTouched(event)) {
            if (diceAmountType == DiceAmountType.ONE) {
                diceAmountType = DiceAmountType.TWO;
            } else if (diceAmountType == DiceAmountType.TWO) {
                diceAmountType = DiceAmountType.ONE;
            } else {
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }

            pointsDices = drawable.getDrawableList(diceAmountType);

            ((Data) this.getContext()).setRolled(Boolean.TRUE);
            ((Data) this.getContext()).setInterrupted(Boolean.FALSE);
            invalidate();
            return true;
        }

        ((Data) this.getContext()).setRolled(Boolean.TRUE);
        ((Data) this.getContext()).setInterrupted(Boolean.FALSE);
        invalidate();

        return true;
    }

    private boolean isSoundOnBitmapTouched(MotionEvent event) {
        if (event.getRawX() >= getLeftSoundBitmapPos(metrics.widthPixels)
                && event.getRawX() < (getLeftSoundBitmapPos(metrics.widthPixels) + getSoundBitmap().getWidth())
                && event.getRawY() >= getTopSoundBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopSoundBitmapPos(metrics.heightPixels) + getSoundBitmap().getHeight())) {
            return true;
        }
        return false;
    }

    private boolean isAmountDiceBitmapTouched(MotionEvent event) {
        if (event.getRawX() >= getLeftAmountDiceBitmapPos(metrics.widthPixels)
                && event.getRawX() < (getLeftAmountDiceBitmapPos(metrics.widthPixels) + getAmountDiceBitmap().getWidth())
                && event.getRawY() >= getTopAmountDiceBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopAmountDiceBitmapPos(metrics.heightPixels) + getAmountDiceBitmap().getHeight())) {
            return true;
        }
        return false;
    }

    private int getTopSoundBitmapPos(int height) {
        return height - getSoundBitmap().getHeight() * 2 / 10 * 8;
    }

    private int getLeftSoundBitmapPos(int width) {
        return width / 2 - getSoundBitmap().getWidth() / 2;
    }

    private int getTopAmountDiceBitmapPos(int height) {
        return height - getAmountDiceBitmap().getHeight() * 2 / 10 * 8;
    }

    private int getLeftAmountDiceBitmapPos(int width) {
        return width / 2 - getAmountDiceBitmap().getWidth() / 2 + /* Offset */getAmountDiceBitmap().getWidth() * 3;
    }
}