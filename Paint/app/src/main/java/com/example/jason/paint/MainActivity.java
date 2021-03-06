package com.example.jason.paint;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawingView drawView;
    private int imageId = 1;
    private ImageButton currPaint;
    private ImageButton drawBtn;
    private float tinyBrush;
    private float smallBrush;
    private float mediumBrush;
    private float largeBrush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        tinyBrush = 5;
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        drawBtn = (ImageButton)findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);
        drawView.setBrushSize(mediumBrush);



    }
    public void paintClicked(View view){

        //use chosen color

        if(view!=currPaint){
//update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
//            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;

        }




    }

    public void clearCanvas(View v) {
        drawView.clearCanvas();
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.draw_btn){
            //draw button clicked
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            ImageButton tinyBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            tinyBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(tinyBrush);
                    drawView.setLastBrushSize(tinyBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton hugeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });

            brushDialog.show();

        }
    }


    public void save(View view){
        saveToInternalStorage(drawView.getCanvasBitmap());
    }

    public void eraser(View view){
        drawView.getDrawPaint().setColor(Color.WHITE);
    }

    public void resize(View view){
        Bitmap bitmap = drawView.getDrawingCache();
        Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)bitmap.getWidth()*2,(int)bitmap.getHeight()*2,true);
        drawView.getDrawCanvas().drawBitmap(resized,0,0,drawView.getDrawPaint());
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,"image" + imageId + ".jpg");
        imageId++;

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("Save:","successful");
        return directory.getAbsolutePath();
    }

    public void pipette(View view){
        drawView.setPipetteClicked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Drawable d;
        Bitmap b;
        switch(item.getItemId()){
            case R.id.arrow:
                d = getResources().getDrawable(R.drawable.arrow);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            case R.id.circle:
                d = getResources().getDrawable(R.drawable.circle);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            case R.id.heart:
                d = getResources().getDrawable(R.drawable.heart);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            case R.id.hexagon:
                d = getResources().getDrawable(R.drawable.hexagon);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            case R.id.square:
                d = getResources().getDrawable(R.drawable.square);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            case R.id.star:
                d = getResources().getDrawable(R.drawable.star);
                b = ((BitmapDrawable) d).getBitmap();
                drawView.getDrawCanvas().drawBitmap(b,0,0,drawView.getDrawPaint());
                break;
            default: return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
