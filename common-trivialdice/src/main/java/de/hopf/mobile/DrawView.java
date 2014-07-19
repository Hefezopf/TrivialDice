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
import android.graphics.RectF;
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
    
    private DiceAmountType diceAmountType = DiceAmountType.ONE;
    private Bitmap amountDiceBitmap;

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
                
        if (paint == null) {
            paint = new Paint();
        }
        this.wm = wm;

        if (metrics == null) {
            metrics = new DisplayMetrics();
        }
        this.wm.getDefaultDisplay().getMetrics(metrics);
        
        final int kantenLaengeWuerfel = metrics.widthPixels / 2;
        int linkerWuerfelRand = (metrics.widthPixels - kantenLaengeWuerfel) / 2;
        int obererWürfelRand = (metrics.heightPixels - kantenLaengeWuerfel) / 2;

        paint.setColor(Color.WHITE);
        paint.setTextSize(kantenLaengeWuerfel / 10);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.LEFT);

        setupDice(diceType, kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);
    }

    private void setupDice(DiceType diceType, final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand) {
        initDice(pointsDiceOne);
        initDice(pointsDiceTwo);

        switch (diceType) {
        case DICE_NORMAL:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceNormal(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, 0, pointsDiceOne);
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceNormal(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, -kantenLaengeWuerfel/20*13, pointsDiceOne);
                setupDiceNormal(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, kantenLaengeWuerfel/20*8, pointsDiceTwo);
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        case DICE_COLOR:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceColor(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, 0, pointsDiceOne.get(0));
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceColor(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, -kantenLaengeWuerfel/20*13, pointsDiceOne.get(0));
                setupDiceColor(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, kantenLaengeWuerfel/20*8, pointsDiceTwo.get(0));
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        case DICE_DOUBLING:
            if(diceAmountType == DiceAmountType.ONE){
                setupDiceDoubling(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, 0, pointsDiceOne.get(0));
            }
            else if(diceAmountType == DiceAmountType.TWO){
                setupDiceDoubling(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, -kantenLaengeWuerfel/20*13, pointsDiceOne.get(0));
                setupDiceDoubling(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand, kantenLaengeWuerfel/20*8, pointsDiceTwo.get(0));
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }            
            break;
        default:
            throw new IllegalArgumentException("Unbekannter Enum DiceType!");
        }
    }

    private void setupDiceNormal(final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand, int offset, List<List<Point>> pointsDice ) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand  + offset + (kantenLaengeWuerfel / 2), pointsDice.get(0));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(1));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(1));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand  + offset + (kantenLaengeWuerfel / 2), pointsDice.get(2));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(2));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(3));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(3));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(4));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand  + offset + (kantenLaengeWuerfel / 2), pointsDice.get(4));

        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel * 3 / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 4), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 2), pointsDice.get(5));
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel * 3 / 4), obererWürfelRand  + offset + (kantenLaengeWuerfel / 2), pointsDice.get(5));
    }

    private void setupDiceColor(final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand, int offset, List<Point> points) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset  + (kantenLaengeWuerfel / 2), points);
    }

    private void setupDiceDoubling(final int kantenLaengeWuerfel, int linkerWuerfelRand, int obererWürfelRand, int offset, List<Point> points) {
        addPoint(linkerWuerfelRand + (kantenLaengeWuerfel / 2), obererWürfelRand + offset + (kantenLaengeWuerfel / 2), points);
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

        int number2 = calculateNumber2(); // TODO NPE wenn nicht vorher ausgeführt!
        if(diceAmountType == DiceAmountType.ONE){
            drawCompleteDice(calculateNumber(), canvas, kantenLaengeWuerfel, 0, pointsDiceOne);
        }
        else if(diceAmountType == DiceAmountType.TWO){
            drawCompleteDice(calculateNumber(), canvas, kantenLaengeWuerfel, -kantenLaengeWuerfel/20*13, pointsDiceOne);
            drawCompleteDice(number2, canvas, kantenLaengeWuerfel, kantenLaengeWuerfel/20*8, pointsDiceTwo);
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

    private void drawCompleteDice(int number, Canvas canvas, final int kantenLaengeWuerfel, int offset, List<List<Point>> points) {
        drawDiceBorder(canvas, kantenLaengeWuerfel, offset);
        drawDiceNumbers(number, canvas, kantenLaengeWuerfel, points);
    }

    private void drawCopyright(Canvas canvas, final int kantenLaengeWuerfel) {
        paint.setTextAlign(Align.LEFT);
        canvas.drawText("\u00A9" + " hopf-it.de", 10, kantenLaengeWuerfel / 10, paint);
    }

    private void drawDiceBorder(Canvas canvas, final int kantenLaengeWuerfel, int offset) {
        int linkesEck = (metrics.widthPixels ) / 2;
        int oberesEck = (metrics.heightPixels ) / 2 + offset;

//        int linkesEck = (metrics.widthPixels - kantenLaengeWuerfel) / 2;
//        int oberesEck = (metrics.heightPixels - kantenLaengeWuerfel) / 2 + offset;
//
//        canvas.drawLine(linkesEck, oberesEck, linkesEck + kantenLaengeWuerfel, oberesEck, paint);
//        canvas.drawLine(linkesEck, oberesEck, linkesEck, oberesEck + kantenLaengeWuerfel, paint);
//        canvas.drawLine(linkesEck + kantenLaengeWuerfel, oberesEck, linkesEck + kantenLaengeWuerfel, oberesEck + kantenLaengeWuerfel,
//                paint);
//        canvas.drawLine(linkesEck, oberesEck + kantenLaengeWuerfel, linkesEck + kantenLaengeWuerfel, oberesEck + kantenLaengeWuerfel,
//                paint);       
        
        float width = getWidth();
        float height = getHeight();
        float radius;

        if (width > height){
            radius = height/4;
        }else{
            radius = width/4;
        }
        paint.setStrokeWidth(2);    
        paint.setStyle(Paint.Style.STROKE);
        
        final RectF rect = new RectF();
        rect.set(linkesEck - radius, 
                oberesEck - radius, 
                linkesEck + radius, 
                oberesEck + radius);

        canvas.drawRoundRect(rect, 10, 10, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawHitMessage(Canvas canvas, final int kantenLaengeWuerfel) {
        int linkesEck = (metrics.widthPixels - kantenLaengeWuerfel) / 2;
        int oberesEck = (metrics.heightPixels - kantenLaengeWuerfel) / 2;
        
        String hit_text = this.getContext().getString(hitMsgKey);
        paint.setTextAlign(Align.CENTER);
        
        if(diceAmountType == DiceAmountType.ONE){
            canvas.drawText(hit_text, linkesEck + (kantenLaengeWuerfel / 2), oberesEck + kantenLaengeWuerfel + (kantenLaengeWuerfel / 10),
                    paint);        
        }
        else if (diceAmountType == DiceAmountType.TWO){
            canvas.drawText(hit_text, linkesEck + (kantenLaengeWuerfel / 2), oberesEck + kantenLaengeWuerfel + (kantenLaengeWuerfel / 2),
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

    private void drawDiceNumbers(int number, Canvas canvas, final int kantenLaengeWuerfel, List<List<Point>> points) {
        switch (diceType) {
        case DICE_NORMAL:
            drawDiceNormal(number, canvas, kantenLaengeWuerfel, points);            
            break;
        case DICE_COLOR:
            drawDiceColor(number, canvas, kantenLaengeWuerfel, points.get(0));
            break;
        case DICE_DOUBLING:
            drawDiceDoubling(number, canvas, kantenLaengeWuerfel, points.get(0));
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
        canvas.drawBitmap(soundBitmap, getLeftSoundBitmapPos(metrics.widthPixels), getTopSoundBitmapPos(metrics.heightPixels), paint);
    }

    private void drawAmountDiceBitmap(Canvas canvas) {
        if(diceAmountType == DiceAmountType.ONE){
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_star_big_off);
        }
        else if(diceAmountType == DiceAmountType.TWO){
            amountDiceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_star_big_on);
        }
        else{
            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
        }            
        canvas.drawBitmap(amountDiceBitmap, getLeftAmountDiceBitmapPos(metrics.widthPixels), getTopAmountDiceBitmapPos(metrics.heightPixels), paint);
    }

    private void drawDiceNormal(int number, Canvas canvas, final int kantenLaengeWuerfel, List<List<Point>> pointsList) {
        List<Point> points = null;

        switch (number) {
        case 0:
            points = pointsList.get(0);
            break;
        case 1:
            points = pointsList.get(1);
            break;
        case 2:
            points = pointsList.get(2);
            break;
        case 3:
            points = pointsList.get(3);
            break;
        case 4:
            points = pointsList.get(4);
            break;
        case 5:
            points = pointsList.get(5);
            break;
        default:
        }
        drawPoints(canvas, kantenLaengeWuerfel, points, kantenLaengeWuerfel / 10);
    }

    private void drawPoints(Canvas canvas, final int kantenLaengeWuerfel, List<Point> points, int radius) {
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, radius, paint);
        }
    }

    private void drawDiceColor(int number, Canvas canvas, final int kantenLaengeWuerfel, List<Point> points) {        
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

        drawPoints(canvas, kantenLaengeWuerfel, points, kantenLaengeWuerfel / 3);        
        paint.setColor(Color.WHITE);
    }

    private void drawDiceDoubling(int number, Canvas canvas, final int kantenLaengeWuerfel, List<Point> points) {        
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

        paint.setTextSize(kantenLaengeWuerfel / 2);
        for (Point point : points) {
            canvas.drawText(text, point.x, point.y + (paint.getTextSize() / 3), paint);
        }
        paint.setTextSize(kantenLaengeWuerfel / 10);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        // Toggle Sound
        if(isSoundOnBitmapTouched(event)){
            soundOn = !soundOn;
            ((Data) this.getContext()).setRolled(Boolean.FALSE);
            ((Data) this.getContext()).setInterrupted(Boolean.TRUE);
            invalidate();
            return true;
        }

        // Toggle Ansicht
        if(isAmountDiceBitmapTouched(event)){
            if(diceAmountType == DiceAmountType.ONE){
                diceAmountType = DiceAmountType.TWO;
            }
            else if(diceAmountType == DiceAmountType.TWO){
                    diceAmountType = DiceAmountType.ONE;
            }
            else{
                throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
            }      
            
            final int kantenLaengeWuerfel = metrics.widthPixels / 2;
            int linkerWuerfelRand = (metrics.widthPixels - kantenLaengeWuerfel) / 2;
            int obererWürfelRand = (metrics.heightPixels - kantenLaengeWuerfel) / 2;

            setupDice(diceType, kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);
            
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
                && event.getRawX() < (getLeftSoundBitmapPos(metrics.widthPixels) + soundBitmap.getWidth())
                && event.getRawY() >= getTopSoundBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopSoundBitmapPos(metrics.heightPixels) + soundBitmap.getHeight())) {
            return true;
        }
        return false;
    }

    private boolean isAmountDiceBitmapTouched(MotionEvent event) {
        if (event.getRawX() >= getLeftAmountDiceBitmapPos(metrics.widthPixels)
                && event.getRawX() < (getLeftAmountDiceBitmapPos(metrics.widthPixels) + amountDiceBitmap.getWidth())
                && event.getRawY() >= getTopAmountDiceBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopAmountDiceBitmapPos(metrics.heightPixels) + amountDiceBitmap.getHeight())) {
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
        pointsDice.clear();
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
        pointsDice.add(new ArrayList<Point>());
    }   
    
    private int getTopSoundBitmapPos(int height) {
        return height - soundBitmap.getHeight() * 2/10*8;
    }

    private int getLeftSoundBitmapPos(int width) {
        return width / 2 - soundBitmap.getWidth() / 2;
    }   
    
    private int getTopAmountDiceBitmapPos(int height) {
        return height - amountDiceBitmap.getHeight() * 2/10*8;
    }

    private int getLeftAmountDiceBitmapPos(int width) {
        return width / 2 - amountDiceBitmap.getWidth() / 2 + /* Offset */amountDiceBitmap.getWidth()*3;
    }    
}