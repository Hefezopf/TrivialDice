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

public class DrawView extends View implements OnTouchListener, Serializable {
    private static final long serialVersionUID = 1L;
    
    // 1. Würfel
    private final List<List<Point>> pointsDiceOne = new ArrayList<List<Point>>();
    // 2. Würfel
    private final List<List<Point>> pointsDiceTwo = new ArrayList<List<Point>>();
    
    private final WindowManager wm;
    private Paint paint;
    private MediaPlayer mp;
    private DisplayMetrics metrics;

    private final int diceSoundKey;
    private final int hitMsgKey;
    private final DiceType diceType;

    private boolean soundOn = true;
    private Bitmap soundBitmap;
    
    private final DiceAmountType diceAmountType = DiceAmountType.ONE;
//    private final DiceAmountType diceAmountType = DiceAmountType.TWO;

    public DrawView(Context context, WindowManager wm, int diceSoundKey, int hitMsgKey, DiceType diceType) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        if (mp == null) {
            mp = MediaPlayer.create(this.getContext(), diceSoundKey);
        }
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;
        this.diceType = diceType;
                
        initDice(pointsDiceOne);
        initDice(pointsDiceTwo);

        if (paint == null) {
            paint = new Paint();
        }
        this.wm = wm;

        if (metrics == null) {
            metrics = new DisplayMetrics();
        }
        this.wm.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        final int kantenLaenge = width / 2;

        int linkerRand = (width - kantenLaenge) / 2;
        int obererRand = (height - kantenLaenge) / 2;

        paint.setColor(Color.WHITE);
        paint.setTextSize(kantenLaenge / 10);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.LEFT);

        switch (diceType) {
        case DICE_NORMAL:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceNormal(kantenLaenge, linkerRand, obererRand, 0, pointsDiceOne);
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceNormal(kantenLaenge, linkerRand, obererRand, -kantenLaenge/20*13, pointsDiceOne);
                setupDiceNormal(kantenLaenge, linkerRand, obererRand, kantenLaenge/20*8, pointsDiceTwo);
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        case DICE_COLOR:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceColor(kantenLaenge, linkerRand, obererRand, 0, pointsDiceOne.get(0));
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceColor(kantenLaenge, linkerRand, obererRand, -kantenLaenge/20*13, pointsDiceOne.get(0));
                setupDiceColor(kantenLaenge, linkerRand, obererRand, kantenLaenge/20*8, pointsDiceTwo.get(0));
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        case DICE_DOUBLING:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceDoubling(kantenLaenge, linkerRand, obererRand, 0, pointsDiceOne.get(0));
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceDoubling(kantenLaenge, linkerRand, obererRand, -kantenLaenge/20*13, pointsDiceOne.get(0));
                setupDiceDoubling(kantenLaenge, linkerRand, obererRand, kantenLaenge/20*8, pointsDiceTwo.get(0));
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        default:
            throw new IllegalArgumentException("Unbekannter Enum DiceType!");
        }
    }

    private void setupDiceNormal(final int kantenLaenge, int linkerRand, int obererRand, int offset, List<List<Point>> pointsDice ) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand  + offset + (kantenLaenge / 2), pointsDice.get(0));

        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(1));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(1));

        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(2));
        addPoint(linkerRand + (kantenLaenge / 2), obererRand  + offset + (kantenLaenge / 2), pointsDice.get(2));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(2));

        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(3));
        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(3));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(3));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(3));

        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(4));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(4));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(4));
        addPoint(linkerRand + (kantenLaenge / 2), obererRand  + offset + (kantenLaenge / 2), pointsDice.get(4));

        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(5));
        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge * 3 / 4), pointsDice.get(5));
        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 4), pointsDice.get(5));
        addPoint(linkerRand + (kantenLaenge / 4), obererRand  + offset + (kantenLaenge / 2), pointsDice.get(5));
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand  + offset + (kantenLaenge / 2), pointsDice.get(5));
    }

    private void setupDiceColor(final int kantenLaenge, int linkerRand, int obererRand, int offset, List<Point> points) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + offset  + (kantenLaenge / 2), points);
    }

    private void setupDiceDoubling(final int kantenLaenge, int linkerRand, int obererRand, int offset, List<Point> points) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + offset + (kantenLaenge / 2), points);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (metrics == null) {
            metrics = new DisplayMetrics();
        }
        this.wm.getDefaultDisplay().getMetrics(metrics);
        final int kantenLaenge = metrics.widthPixels / 2;

        drawCopyright(canvas, kantenLaenge);
        drawHitMessage(canvas, kantenLaenge);
        drawSpeakerBitmap(canvas);
        playSound();

        if(diceAmountType == DiceAmountType.ONE){
            int number2 = calculateNumber2(); // TODO
            drawCompleteDice(calculateNumber(), canvas, kantenLaenge, 0, pointsDiceOne.get(0));
        }
        else if(diceAmountType == DiceAmountType.TWO){
            int number2 = calculateNumber2();
            drawCompleteDice(calculateNumber(), canvas, kantenLaenge, -kantenLaenge/20*13, pointsDiceOne.get(0));
            drawCompleteDice(number2, canvas, kantenLaenge, kantenLaenge/20*8, pointsDiceTwo.get(0));
        }
        else{
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }                    
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

    private void drawCompleteDice(int number, Canvas canvas, final int kantenLaenge, int offset, List<Point> points) {
        drawDiceBorder(canvas, kantenLaenge, offset);
        drawDiceNumbers(number, canvas, kantenLaenge, points);
    }

    private void drawCopyright(Canvas canvas, final int kantenLaenge) {
        paint.setTextAlign(Align.LEFT);
        canvas.drawText("\u00A9" + " hopf-it.de", 10, kantenLaenge / 10, paint);
    }

    private void drawDiceBorder(Canvas canvas, final int kantenLaenge, int offset) {
        int linkesEck = (metrics.widthPixels - kantenLaenge) / 2;
        int oberesEck = (metrics.heightPixels - kantenLaenge) / 2 + offset;

        canvas.drawLine(linkesEck, oberesEck, linkesEck + kantenLaenge, oberesEck, paint);
        canvas.drawLine(linkesEck, oberesEck, linkesEck, oberesEck + kantenLaenge, paint);
        canvas.drawLine(linkesEck + kantenLaenge, oberesEck, linkesEck + kantenLaenge, oberesEck + kantenLaenge,
                paint);
        canvas.drawLine(linkesEck, oberesEck + kantenLaenge, linkesEck + kantenLaenge, oberesEck + kantenLaenge,
                paint);
    }

    private void drawHitMessage(Canvas canvas, final int kantenLaenge) {
        int linkesEck = (metrics.widthPixels - kantenLaenge) / 2;
        int oberesEck = (metrics.heightPixels - kantenLaenge) / 2;
        
        String hit_text = this.getContext().getString(hitMsgKey);
        paint.setTextAlign(Align.CENTER);
        
        if(diceAmountType == DiceAmountType.ONE){
            canvas.drawText(hit_text, linkesEck + (kantenLaenge / 2), oberesEck + kantenLaenge + (kantenLaenge / 10),
                    paint);        
        }
        else if (diceAmountType == DiceAmountType.TWO){
            canvas.drawText(hit_text, linkesEck + (kantenLaenge / 2), oberesEck + kantenLaenge + (kantenLaenge / 2),
                paint);
        }
        else{
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

    private void drawDiceNumbers(int number, Canvas canvas, final int kantenLaenge, List<Point> points) {
        switch (diceType) {
        case DICE_NORMAL:
            drawDiceNormal(canvas, kantenLaenge);
//            calculateNumber();
            drawDiceNormal2(canvas, kantenLaenge);
            break;
        case DICE_COLOR:
            drawDiceColor(number, canvas, kantenLaenge, points);
            break;
        case DICE_DOUBLING:
            drawDiceDoubling(number, canvas, kantenLaenge, points);
            break;
        default:
            throw new IllegalArgumentException("Unbekannter Enum DiceType!");
        }
    }

    private void drawSpeakerBitmap(Canvas canvas) {
        if (soundOn) {
            soundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode_off);
        } else {
            soundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode);
        }
        canvas.drawBitmap(soundBitmap, getLeftBitmapPos(metrics.widthPixels), getTopBitmapPos(metrics.heightPixels), paint);
    }

    private void drawDiceNormal(Canvas canvas, final int kantenLaenge) {
        List<Point> points = null;

        switch (((Data) this.getContext()).getNumber().intValue()) {
        case 0:
            points = pointsDiceOne.get(0);
            break;
        case 1:
            points = pointsDiceOne.get(1);
            break;
        case 2:
            points = pointsDiceOne.get(2);
            break;
        case 3:
            points = pointsDiceOne.get(3);
            break;
        case 4:
            points = pointsDiceOne.get(4);
            break;
        case 5:
            points = pointsDiceOne.get(5);
            break;
        default:
        }
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, kantenLaenge / 10, paint);
        }
    }

    private void drawDiceNormal2(Canvas canvas, final int kantenLaenge) {
        List<Point> points = null;

        switch (((Data) this.getContext()).getNumber2().intValue()) {
        case 0:
            points = pointsDiceTwo.get(0);
            break;
        case 1:
            points = pointsDiceTwo.get(1);
            break;
        case 2:
            points = pointsDiceTwo.get(2);
            break;
        case 3:
            points = pointsDiceTwo.get(3);
            break;
        case 4:
            points = pointsDiceTwo.get(4);
            break;
        case 5:
            points = pointsDiceTwo.get(5);
            break;
        default:
        }
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, kantenLaenge / 10, paint);
        }
    }

    private void drawDiceColor(int number, Canvas canvas, final int kantenLaenge, List<Point> points) {        
        switch (number) {
        case 0:
            paint.setColor(Color.CYAN);
            break;
        case 1:
            paint.setColor(Color.BLUE);
            break;
        case 2:
            paint.setColor(Color.RED);
            break;
        case 3:
            paint.setColor(Color.GREEN);
            break;
        case 4:
            paint.setColor(Color.WHITE);
            break;
        case 5:
            paint.setColor(Color.YELLOW);
            break;
        default:
        }

        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, kantenLaenge / 3, paint);
        }
        paint.setColor(Color.WHITE);
    }

    private void drawDiceDoubling(int number, Canvas canvas, final int kantenLaenge, List<Point> points) {        
        paint.setColor(Color.WHITE);
        String text = "";
        switch (number) {
        case 0:
            text = "2";
            break;
        case 1:
            text = "4";
            break;
        case 2:
            text = "8";
            break;
        case 3:
            text = "16";
            break;
        case 4:
            text = "32";
            break;
        case 5:
            text = "64";
            break;
        default:
            throw new IllegalArgumentException("Nummer nicht gültig!");
        }

        paint.setTextSize(kantenLaenge / 2);
        for (Point point : points) {
            canvas.drawText(text, point.x, point.y + (paint.getTextSize() / 3), paint);
        }
        paint.setTextSize(kantenLaenge / 10);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        // Toggle sound
        if(isSoundOnBitmapTouched(event)){
            soundOn = !soundOn;
            ((Data) this.getContext()).setRolled(Boolean.FALSE);
            ((Data) this.getContext()).setInterrupted(Boolean.TRUE);
            invalidate();
            return true;
        }

        ((Data) this.getContext()).setRolled(Boolean.TRUE);
        ((Data) this.getContext()).setInterrupted(Boolean.FALSE);
        invalidate();

        return true;
    }

    private boolean isSoundOnBitmapTouched(MotionEvent event) {
        // if this is true, you've started your click inside your bitmap
        if (event.getRawX() >= getLeftBitmapPos(metrics.widthPixels)
                && event.getRawX() < (getLeftBitmapPos(metrics.widthPixels) + soundBitmap.getWidth())
                && event.getRawY() >= getTopBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopBitmapPos(metrics.heightPixels) + soundBitmap.getHeight())) {
            return true;
        }
        return false;
    }

    private void addPoint(int x, int y, List<Point> points) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        points.add(point);
    }    
    
    private void initDice(List<List<Point>> pointsDice) {
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
    }   
    
    private int getTopBitmapPos(int height) {
        return height - soundBitmap.getHeight() * 2/10*8;
    }

    private int getLeftBitmapPos(int width) {
        return width / 2 - soundBitmap.getWidth() / 2;
    }    
}