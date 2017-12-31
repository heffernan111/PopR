package com.artesseum.popr;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by apoca on 30/12/2017.
 */

public class GameView extends View {
    Bitmap background;
    static int dWidth, dHeight;
    int bubbleHeight;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;
    ArrayList<Bubble> bubbles, redBubbles;

    public GameView(Context context) {
        super(context);
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        bubbles = new ArrayList<>();
        redBubbles = new ArrayList<>();

        for (int i = 0; i<2;i++){
            Bubble bubble = new Bubble(context);
            bubbles.add(bubble);
            RedBubble redBubble = new RedBubble(context);
            redBubbles.add(redBubble);

        }


     //   background = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);



        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     //   canvas.drawBitmap(background,0,0,null);
        for(int i=0; i<bubbles.size(); i++){
            //// blue bubbles
            canvas.drawBitmap(bubbles.get(i).getBitmap(), bubbles.get(i).bubbleX,bubbles.get(i).bubbleY,null);
            bubbles.get(i).bubbleFrame++;
            if (bubbles.get(i).bubbleFrame > 3){
                bubbles.get(i).bubbleFrame = 0;
            }
            bubbles.get(i).bubbleX -= bubbles.get(i).velocity;
            if (bubbles.get(i).bubbleX< - bubbles.get(i).getWidth()){
                bubbles.get(i).resetPosition();
            }
            // red bubbles
            canvas.drawBitmap(redBubbles.get(i).getBitmap(),redBubbles.get(i).bubbleX,redBubbles.get(i).bubbleY,null);
            redBubbles.get(i).bubbleFrame++;
            if (redBubbles.get(i).bubbleFrame>3){
                redBubbles.get(i).bubbleFrame = 0;
            }
            redBubbles.get(i).bubbleX +=bubbles.get(i).velocity;
            if (redBubbles.get(i).bubbleX > (dWidth+redBubbles.get(i).getWidth())){
                redBubbles.get(i).resetPosition();
            }

        }
        handler.postDelayed(runnable,UPDATE_MILLIS);

    }
}
