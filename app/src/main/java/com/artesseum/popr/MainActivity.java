package com.artesseum.popr;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{


    Boolean gamePlay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        final ArrayList<Object> bubblesArray = new ArrayList<>();
        bubblesArray.add(new RedBubbles());
        bubblesArray.add(new BlueBubbles());*/




        gamePlay = true;



    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            while(gamePlay = true){

                addBubbles();


            }

        }

        private void addBubbles() {




        }
    };

    Thread myThread = new Thread(myRunnable);
    myThread.start();


    }



}
