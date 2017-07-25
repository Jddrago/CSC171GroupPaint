package com.example.jason.paint;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jason on 7/20/2017.
 */

public class CustomCanvas extends View{
    Paint myPaint;
    Path myPath;
    boolean pipetteClicked = false;

    public CustomCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init(){
        myPaint = new Paint();
        myPaint.setColor(0xff337722); // aarrggbb alpha is first
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(10);
    }

    public void clearCanvas() {
        myPath.reset();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        if(pipetteClicked) {
            this.buildDrawingCache();
            int color = this.getDrawingCache().getPixel((int) x, (int) y);
            myPaint.setColor(color);
        }
        pipetteClicked = false;
        return true;
    }

    public boolean isPipetteClicked() {
        return pipetteClicked;
    }

    public void setPipetteClicked(boolean pipetteClicked) {
        this.pipetteClicked = pipetteClicked;
    }
}
