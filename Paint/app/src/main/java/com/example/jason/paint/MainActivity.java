package com.example.jason.paint;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private CustomCanvas customCanvas;
    private int imageId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CustomCanvas)findViewById(R.id.customCanvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    public void save(View view){
        saveToInternalStorage(customCanvas.getDrawingCache());
    }

    public void resize(View view){
        Bitmap bitmap = customCanvas.getDrawingCache();
        Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)bitmap.getWidth()*2,(int)bitmap.getHeight()*2,true);
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
        return directory.getAbsolutePath();
    }

    public void pipette(View view){
        customCanvas.setPipetteClicked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.arrow:
                break;
            case R.id.circle:
                break;
            case R.id.heart:
                break;
            case R.id.hexagon:
                break;
            case R.id.square:
                break;
            case R.id.star:
                break;
            default: return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
