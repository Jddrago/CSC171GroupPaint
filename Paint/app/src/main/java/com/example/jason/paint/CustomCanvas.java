package com.example.jason.paint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jason on 7/20/2017.
 */

public class CustomCanvas extends View{
    Paint myRedPaintFill;
    Paint myGreenPaintStroke;
    Path myPath;

    public CustomCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init(){
        myRedPaintFill = new Paint();
        myRedPaintFill.setColor(Color.RED);
        myRedPaintFill.setStyle(Paint.Style.FILL);

        myGreenPaintStroke = new Paint();
        myGreenPaintStroke.setColor(0xff337722); // aarrggbb alpha is first
        myGreenPaintStroke.setStyle(Paint.Style.STROKE);
        myGreenPaintStroke.setStrokeWidth(10);
    }
}
