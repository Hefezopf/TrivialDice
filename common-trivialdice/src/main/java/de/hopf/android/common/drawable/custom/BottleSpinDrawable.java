package de.hopf.android.common.drawable.custom;

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
import de.hopf.android.common.DrawViewBase;
import de.hopf.android.common.ItemAmountType;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.base.BaseDrawable;

public class BottleSpinDrawable extends BaseDrawable {

    private int spinCounter = 0;
    private final int HIGH_SPEED = 4;
    private int speed = HIGH_SPEED;
    private int angle = 0;
    private final Bitmap bitmap;
    
    public BottleSpinDrawable(Resources resources, int bottleId, int kantenLaenge, int linkerWuerfelRand, int obererWürfelRand) {
        super(kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        bitmap = BitmapFactory.decodeResource(resources, bottleId);
     }

    @Override
    public void drawShape(DrawViewBase drawView, Paint paint, Canvas canvas, DisplayMetrics metrics, int offsetX, int offsetY) {
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
    public List<List<List<Point>>> initDrawableList(ItemAmountType diceAmountType) {        
        initDice(diceAmountType);        
        return pointsDices;
    }

    @Override
    public void drawContent(List<Integer> numberList, Paint paint, Canvas canvas, int kantenLaenge) {
    }
}
