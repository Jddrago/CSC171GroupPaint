package com.example.jason.paint;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
/**
 * Created by Daniel on 7/24/2017.
 */

public class DrawingView extends View {
    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    boolean pipetteClicked = false;

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
    }
    private void setupDrawing(){
//get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);



    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);

    }
    @Override
    protected void onDraw(Canvas canvas) {
//draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

    }
    public boolean onTouchEvent(MotionEvent event) {
//detect user touch
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(pipetteClicked) {
                    this.buildDrawingCache();
                    int color = this.getDrawingCache().getPixel((int) touchX, (int) touchY);
                    drawPaint.setColor(color);
                    pipetteClicked = false;
                }
                else {
                    drawPath.moveTo(touchX, touchY);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    public void setColor(String newColor){
//set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);


    }

    public boolean isPipetteClicked() {
        return pipetteClicked;
    }

    public void setPipetteClicked(boolean pipetteClicked) {
        this.pipetteClicked = pipetteClicked;
    }

    public void clearCanvas() {
        drawCanvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public Canvas getDrawCanvas() {
        return drawCanvas;
    }

    public void setDrawCanvas(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
    }

    public Paint getDrawPaint() {
        return drawPaint;
    }

    public void setDrawPaint(Paint drawPaint) {
        this.drawPaint = drawPaint;
    }

    public Bitmap getCanvasBitmap() {
        return canvasBitmap;
    }

    public void setCanvasBitmap(Bitmap canvasBitmap) {
        this.canvasBitmap = canvasBitmap;
    }
}

