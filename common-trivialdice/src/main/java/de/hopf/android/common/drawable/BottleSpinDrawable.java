package de.hopf.android.common.drawable;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import de.hopf.android.common.Data;
import de.hopf.android.common.DrawView;
import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;

public class BottleSpinDrawable extends BaseDrawable {

    private int spinCounter = 0;
    private final int HIGH_SPEED = 4;
    private int speed = HIGH_SPEED;
    private int angle = 0;
    private final Bitmap bitmap;
    
    public BottleSpinDrawable(Resources resources, int bottleId, int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        bitmap = BitmapFactory.decodeResource(resources, bottleId);
//        initDice(pointsDices, ItemAmountType.ONE);        
//        setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, ItemAmountType.ONE.getPointOne().getX(), ItemAmountType.ONE.getPointOne().getY(), pointsDices.get(0));
     }

    @Override
    public void drawShape(DrawView drawView, Paint paint, Canvas canvas, DisplayMetrics metrics, int offsetX, int offsetY) {
        // Nicht beim ersten mal
        if (((Data) drawView.getContext()).getCounter() > 1) {
            angle = angle + 8*speed;
           
            drawAndRotateBitmap(canvas);
            
            spinCounter++;
            if(spinCounter < 60){
                drawView.invalidate();
                if((spinCounter % 10) == 0){
                    if(speed > 1){
                        speed--;
                    }
                }
            }
            else{
                spinCounter = 0;
                speed = HIGH_SPEED;
                angle = angle % 360;
                angle = angle + (int)(Math.random()*40);
            }
        }
        else{
            drawAndRotateBitmap(canvas);
        }   
    }

    private void drawAndRotateBitmap(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.postTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
        matrix.postRotate(angle);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        matrix.postTranslate(rect.exactCenterX()+(kantenLaenge/2), rect.exactCenterY()+(kantenLaenge*10/14));        
        canvas.drawBitmap(bitmap, matrix, null);
    }
    
    @Override
    public List<List<List<Point>>> getDrawableList(ItemAmountType diceAmountType) {
//        initDice(pointsDices, diceAmountType);
//        if (diceAmountType == ItemAmountType.ONE) {
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, diceAmountType.getPointOne().getX(), diceAmountType.getPointOne().getY(), pointsDices.get(0));
//        } else if (diceAmountType == ItemAmountType.TWO) {
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getY(), pointsDices.get(0));
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getY(), pointsDices.get(1));
//        } else if (diceAmountType == ItemAmountType.THREE) {
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointOne().getY(), pointsDices.get(0));
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointTwo().getY(), pointsDices.get(1));
//            setupDice(kantenLaenge, linkerWuerfelRand, obererWürfelRand, kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointThree().getX(), kantenLaenge / ItemAmountType.getFaktor() * diceAmountType.getPointThree().getY(), pointsDices.get(2));
//        } else {
//            throw new IllegalArgumentException("Unbekannter DiceAmountType: " + diceAmountType);
//        }
//        return pointsDices;
        
        initDice(pointsDices, diceAmountType);        
        return pointsDices;
    }
    
    @SuppressWarnings("unused")
    private void setupDice(final int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand,
            int offsetX, int offsetY, List<List<Point>> pointsDice) {
//        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
//                pointsDice.get(0));
//
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
//                pointsDice.get(1));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(1));
//
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
//                pointsDice.get(2));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
//                pointsDice.get(2));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(2));
//
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
//                pointsDice.get(3));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(3));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge / 4), pointsDice.get(3));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(3));
//
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
//                pointsDice.get(4));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(4));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(4));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge / 4), pointsDice.get(4));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 2) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
//                pointsDice.get(4));
//
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(5));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge / 4), pointsDice.get(5));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge * 3 / 4), pointsDice.get(5));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 4),
//                pointsDice.get(5));
//        addPoint(linkerWuerfelRand + (kantenLaenge / 4) + offsetX, obererWürfelRand + offsetY + (kantenLaenge / 2),
//                pointsDice.get(5));
//        addPoint(linkerWuerfelRand + (kantenLaenge * 3 / 4) + offsetX, obererWürfelRand + offsetY
//                + (kantenLaenge / 2), pointsDice.get(5));
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaenge, List<List<List<Point>>> points) {
//        int index = 0;
//
//        for (List<List<Point>> pointList : points) {        
//            List<Point> pointsList = null;
//    
//            switch (numberList.get(index++)) {
//            case 0:
//                pointsList = pointList.get(0);
//                break;
//            case 1:
//                pointsList = pointList.get(1);
//                break;
//            case 2:
//                pointsList = pointList.get(2);
//                break;
//            case 3:
//                pointsList = pointList.get(3);
//                break;
//            case 4:
//                pointsList = pointList.get(4);
//                break;
//            case 5:
//                pointsList = pointList.get(5);
//                break;
//            default:
//            }
//            drawPoints(paint, canvas, kantenLaenge, pointsList, kantenLaenge / 10);
//        }
    }
}
