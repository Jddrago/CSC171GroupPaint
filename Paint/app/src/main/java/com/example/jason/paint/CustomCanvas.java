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




}
