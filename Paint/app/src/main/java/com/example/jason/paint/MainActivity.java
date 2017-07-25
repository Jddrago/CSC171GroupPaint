package com.example.jason.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
