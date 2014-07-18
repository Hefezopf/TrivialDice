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
    private final List<Point> pointsONE = new ArrayList<Point>();
    private final List<Point> pointsTWO = new ArrayList<Point>();
    private final List<Point> pointsTHREE = new ArrayList<Point>();
    private final List<Point> pointsFOUR = new ArrayList<Point>();
    private final List<Point> pointsFIVE = new ArrayList<Point>();
    private final List<Point> pointsSIX = new ArrayList<Point>();
    private final WindowManager wm;
    private Paint paint;
    private MediaPlayer mp;
    private DisplayMetrics metrics;

    private final int diceSoundKey;
    private final int hitMsgKey;
    private final DiceType diceType;

    private boolean soundOn = true;
    private Bitmap soundbitmap;

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
            setupDiceNormal(kantenLaenge, linkerRand, obererRand);
            break;
        case DICE_COLOR:
            setupDiceColor(kantenLaenge, linkerRand, obererRand);
            break;
        case DICE_DOUBLING:
            setupDiceDoubling(kantenLaenge, linkerRand, obererRand);
            break;
        default:
            throw new IllegalArgumentException("Unbekannter Enum DiceType!");
        }
    }

    private void setupDiceNormal(final int kantenLaenge, int linkerRand, int obererRand) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + (kantenLaenge / 2), pointsONE);

        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 4), pointsTWO);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge * 3 / 4), pointsTWO);

        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 4), pointsTHREE);
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + (kantenLaenge / 2), pointsTHREE);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge * 3 / 4), pointsTHREE);

        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 4), pointsFOUR);
        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge * 3 / 4), pointsFOUR);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge / 4), pointsFOUR);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge * 3 / 4), pointsFOUR);

        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 4), pointsFIVE);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge * 3 / 4), pointsFIVE);
        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge * 3 / 4), pointsFIVE);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge / 4), pointsFIVE);
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + (kantenLaenge / 2), pointsFIVE);

        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge * 3 / 4), pointsSIX);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge / 4), pointsSIX);
        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge * 3 / 4), pointsSIX);
        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 4), pointsSIX);
        addPoint(linkerRand + (kantenLaenge / 4), obererRand + (kantenLaenge / 2), pointsSIX);
        addPoint(linkerRand + (kantenLaenge * 3 / 4), obererRand + (kantenLaenge / 2), pointsSIX);
    }

    private void setupDiceColor(final int kantenLaenge, int linkerRand, int obererRand) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + (kantenLaenge / 2), pointsONE);
    }

    private void setupDiceDoubling(final int kantenLaenge, int linkerRand, int obererRand) {
        addPoint(linkerRand + (kantenLaenge / 2), obererRand + (kantenLaenge / 2), pointsONE);
    }

    private void addPoint(int x, int y, List<Point> points) {
        Point point = new Point();
        point.x = x;
        point.y = y;
        points.add(point);
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
        drawDiceBorder(canvas, kantenLaenge);
        drawSpeakerBitmap(canvas);
        playSound();

        // Rechnen!
        if (!((Data) this.getContext()).hasInterrupted().booleanValue()) {
            ((Data) this.getContext()).setNumber(new Integer((int) (Math.random() * 6)));
        }

        List<Point> points = null;

        // NPE nach dem Start, manchmal beim drehen!
        if (((Data) this.getContext()).getNumber() == null) {
            ((Data) this.getContext()).setNumber(0);
        }

        drawDice(canvas, kantenLaenge, points);
    }

    private void drawCopyright(Canvas canvas, final int kantenLaenge) {
        paint.setTextAlign(Align.LEFT);
        canvas.drawText("\u00A9" + " hopf-it.de", 10, kantenLaenge / 10, paint);
    }

    private void drawDiceBorder(Canvas canvas, final int kantenLaenge) {
        int linkesEck = (metrics.widthPixels - kantenLaenge) / 2;
        int oberesEck = (metrics.heightPixels - kantenLaenge) / 2;
        
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
        canvas.drawText(hit_text, linkesEck + (kantenLaenge / 2), oberesEck + kantenLaenge + (kantenLaenge / 10),
                paint);
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

    private void drawDice(Canvas canvas, final int kantenLaenge, List<Point> points) {
        switch (diceType) {
        case DICE_NORMAL:
            drawDiceNormal(canvas, kantenLaenge, points);
            break;
        case DICE_COLOR:
            drawDiceColor(canvas, kantenLaenge, points);
            break;
        case DICE_DOUBLING:
            drawDiceDoubling(canvas, kantenLaenge, points);
            break;
        default:
            throw new IllegalArgumentException("Unbekannter Enum DiceType!");
        }
    }

    private void drawSpeakerBitmap(Canvas canvas) {
        if (soundOn) {
            soundbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode_off);
        } else {
            soundbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lock_silent_mode);
        }
        canvas.drawBitmap(soundbitmap, getLeftBitmapPos(metrics.widthPixels), getTopBitmapPos(metrics.heightPixels), paint);
    }

    private int getTopBitmapPos(int height) {
        return height - soundbitmap.getHeight() * 2;
    }

    private int getLeftBitmapPos(int width) {
        return width / 2 - soundbitmap.getWidth() / 2;
    }

    private void drawDiceNormal(Canvas canvas, final int kantenLaenge, List<Point> points) {
        switch (((Data) this.getContext()).getNumber().intValue()) {
        case 0:
            points = pointsONE;
            break;
        case 1:
            points = pointsTWO;
            break;
        case 2:
            points = pointsTHREE;
            break;
        case 3:
            points = pointsFOUR;
            break;
        case 4:
            points = pointsFIVE;
            break;
        case 5:
            points = pointsSIX;
            break;
        default:
        }
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, kantenLaenge / 10, paint);
        }
    }

    private void drawDiceColor(Canvas canvas, final int kantenLaenge, List<Point> points) {
        points = pointsONE;
        switch (((Data) this.getContext()).getNumber().intValue()) {
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

    private void drawDiceDoubling(Canvas canvas, final int kantenLaenge, List<Point> points) {
        points = pointsONE;
        paint.setColor(Color.WHITE);
        String text = "";
        switch (((Data) this.getContext()).getNumber().intValue()) {
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
                && event.getRawX() < (getLeftBitmapPos(metrics.widthPixels) + soundbitmap.getWidth())
                && event.getRawY() >= getTopBitmapPos(metrics.heightPixels)
                && event.getRawY() < (getTopBitmapPos(metrics.heightPixels) + soundbitmap.getHeight())) {
            return true;
        }
        return false;
    }

//    public void setSoundOn(boolean soundOn) {
//        this.soundOn = soundOn;
//    }
}