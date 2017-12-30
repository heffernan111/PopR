package com.artesseum.popr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by apoca on 30/12/2017.
 */

public class RedBubble extends Bubble {

    Bitmap[] bubble = new Bitmap[4];
    public RedBubble(Context context) {
        super(context);
        bubble[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.output32);
        bubble[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.output33);
        bubble[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.output34);
        bubble[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.output35);
        resetPosition();
    }

    @Override
    public Bitmap getBitmap() {
        return bubbles[bubbleFrame];    }

    @Override
    public int getHeight() {
        return bubbles[0].getHeight();
    }

    @Override
    public int getWidth() {
        return bubbles[0].getWidth();
    }

    @Override
    public void resetPosition() {
        bubbleY = -(200+random.nextInt(1500));
        bubbleX = random.nextInt(400);
        velocity = 8 + random.nextInt(21);
    }
}
