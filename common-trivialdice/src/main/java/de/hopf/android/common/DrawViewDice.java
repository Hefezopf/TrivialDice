package de.hopf.android.common;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.base.BaseDiceDrawable;

public class DrawViewDice extends DrawViewBase {
    private static final long serialVersionUID = 1L;

    private boolean soundOn = true;
    private MediaPlayer mediaPlayer;
    private final int diceSoundKey;
    private Bitmap soundBitmap;
    private Bitmap amountDiceBitmap;    
    private final int maxNum;
    
    public DrawViewDice(Context context, WindowManager windowManager, int diceSoundKey, int hitMsgKey, Drawable drawable, int maxNum, boolean bLite) {
        super(context, windowManager, hitMsgKey, drawable, bLite);

        this.diceSoundKey = diceSoundKey;
        this.maxNum = maxNum;
        mediaPlayer = MediaPlayer.create(this.getContext(), diceSoundKey);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSpeakerBitmap(canvas);
        drawAmountDiceBitmap(canvas);
        playSound();     
    }

    private int calculateNumber(int maxNum) {
        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber(new Integer((int) (Math.random() * maxNum)));
        }

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber() == null) {
            ((Data) this.getContext()).setNumber(0);
        }

        return ((Data) this.getContext()).getNumber();
    }

    private int calculateNumber2(int maxNum) {
        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber2(new Integer((int) (Math.random() * maxNum)));
        }

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber2() == null) {
            ((Data) this.getContext()).setNumber2(0);
        }

        return ((Data) this.getContext()).getNumber2();
    }


    private int calculateNumber3(int maxNum) {
        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber3(new Integer((int) (Math.random() * maxNum)));
        }

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber3() == null) {
            ((Data) this.getContext()).setNumber3(0);
        }

        return ((Data) this.getContext()).getNumber3();
    }

    @Override
    protected void drawItem(Canvas canvas) {
        List<Integer> numberList = new ArrayList<Integer>();
        if (itemAmountType == ItemAmountType.ONE) {
            numberList.add(calculateNumber(maxNum)); 
        } else if (itemAmountType == ItemAmountType.TWO) {
            numberList.add(calculateNumber(maxNum)); 
            numberList.add(calculateNumber2(maxNum));
        } else if (itemAmountType == ItemAmountType.THREE) {
            numberList.add(calculateNumber(maxNum)); 
            numberList.add(calculateNumber2(maxNum));
            numberList.add(calculateNumber3(maxNum));
        } else {
            throw new IllegalArgumentException("Unbekannter ItemAmountType: " + itemAmountType);
        }
        
        final int kantenLaenge = getWidth() / 2;
        if (itemAmountType == ItemAmountType.ONE) {
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, itemAmountType.getPointOne().getX(), itemAmountType.getPointOne().getY());
            drawable.drawContent(numberList, paint, canvas, kantenLaenge);        
        } else if (itemAmountType == ItemAmountType.TWO) {
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointOne().getY());
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointTwo().getY());
            drawable.drawContent(numberList, paint, canvas, kantenLaenge);        
        } else if (itemAmountType == ItemAmountType.THREE) {
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointOne().getY());
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointTwo().getY());
            ((BaseDiceDrawable)drawable).drawShape(this, paint, canvas, metrics, kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * itemAmountType.getPointThree().getY());
            drawable.drawContent(numberList, paint, canvas, kantenLaenge);        
        } else {
            throw new IllegalArgumentException("Unbekannter ItemAmountType: " + itemAmountType);
        }
    }

    private void playSound() {
        if (soundOn && ((Data) this.getContext()).hasRolled().equals(Boolean.TRUE)) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this.getContext(), diceSoundKey);
            } else if (mediaPlayer != null) { // Reported Bug v1.5 Dec 23, 2010 2:59:25
                                     // PM
                mediaPlayer.setVolume(2f, 2f);
                mediaPlayer.start();
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

    private void drawAmountDiceBitmap(Canvas canvas) {
        canvas.drawBitmap(getAmountDiceBitmap(), getLeftAmountDiceBitmapPos(metrics.widthPixels),
                getTopAmountDiceBitmapPos(metrics.heightPixels), paint);
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
        if (itemAmountType == ItemAmountType.ONE) {
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_play);
        } else if (itemAmountType == ItemAmountType.TWO) {
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_ff);
        } else if (itemAmountType == ItemAmountType.THREE) {
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_next);
        } else {
            throw new IllegalArgumentException("Unbekannter ItemAmountType: " + itemAmountType);
        }

        return amountDiceBitmap;
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
            if (itemAmountType == ItemAmountType.ONE) {
                itemAmountType = ItemAmountType.TWO;
            } else if (itemAmountType == ItemAmountType.TWO) {
                itemAmountType = ItemAmountType.THREE;
            } else if (itemAmountType == ItemAmountType.THREE) {
                itemAmountType = ItemAmountType.ONE;
            } else {
                throw new IllegalArgumentException("Unbekannter ItemAmountType: " + itemAmountType);
            }

            drawable.initDrawableList(itemAmountType);

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
        return width / 2 - getAmountDiceBitmap().getWidth() / 2 +  /* Offset */getAmountDiceBitmap().getWidth() * 3;
    }
}