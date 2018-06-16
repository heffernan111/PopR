package com.artesseum.popr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.util.Random;

/**
 * Created by apoca on 30/12/2017.
 */

public class Bubble {

    Bitmap bubbles[] = new Bitmap[4]; // how many images...
    int bubbleX, bubbleY, velocity, bubbleFrame;
    Random random;




    public Bubble(Context context) {

        bubbles[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.positron);
        bubbles[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.positron);
        bubbles[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.positron);
        bubbles[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.positron);


        random = new Random();
        resetPosition();



    }

    public Bitmap getBitmap(){
        return bubbles[bubbleFrame];
    }

    public int getHeight(){
        return bubbles[0].getHeight();
    }

    public int getWidth(){
        return bubbles[0].getWidth();
    }
    public void resetPosition(){

        bubbleX = GameView.dWidth+ random.nextInt(300);
        bubbleY = random.nextInt(300);
        velocity = 8 + random.nextInt(13);
        bubbleFrame = 0;



    }


}
