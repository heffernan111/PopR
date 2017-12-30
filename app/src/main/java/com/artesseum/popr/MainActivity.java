package com.artesseum.popr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startGame(View v){
        Log.i("Image Button","clicked");
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();


    }

}

