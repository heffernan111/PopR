package com.artesseum.popr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by apoca on 30/12/2017.
 */

public class GameView extends View {
    Bitmap background, hand;
    static int dWidth, dHeight;
    int bubbleHeight;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;
    static int handWidth, handHeight;
    ArrayList<Bubble> bubbles, redBubbles;
    ArrayList<Stone> stones;
    ArrayList<Pop> pops;
    Context context;
    int count = 0;
    Paint scorePaint;
    final int TEXT_SIZE = 60;

    public GameView(Context context) {
        super(context);
        this.context = context;
        hand = BitmapFactory.decodeResource(getResources(),R.drawable.hand11);
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        handHeight = hand.getHeight();
        handWidth = hand.getWidth();
        bubbles = new ArrayList<>();
        redBubbles = new ArrayList<>();
        stones = new ArrayList<>();
        pops = new ArrayList<>();




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
        scorePaint = new Paint();
        scorePaint.setColor(Color.CYAN);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.CENTER);
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
        for(int i = 0; i<stones.size(); i++){
            if(stones.get(i).y > - stones.get(i).getStoneHeight()){
                stones.get(i).y -= stones.get(i).mVelocity;
                canvas.drawBitmap(stones.get(i).electron,stones.get(i).x, stones.get(i).y,null);
                if (stones.get(i).x >= bubbles.get(0).bubbleX && (stones.get(i).x + stones.get(i).getStoneWidth())
                        <= (bubbles.get(0).bubbleX + bubbles.get(0).getWidth()) &&
                        stones.get(i).y >= bubbles.get(0).bubbleY && stones.get(i).y <= (bubbles.get(0).bubbleY + bubbles.get(0).getHeight())){
                    Pop pop = new Pop(context);
                    pop.popX = bubbles.get(0).bubbleX + bubbles.get(0).getWidth()/2 - pop.getPopWidth()/2;
                    pop.popY = bubbles.get(0).bubbleY + bubbles.get(0).getHeight()/2 - pop.getPopHeight()/2;
                    pops.add(pop);
                    bubbles.get(0).resetPosition();
                    count++;
                    stones.remove(i);

                }else if (stones.get(i).x >= bubbles.get(1).bubbleX && (stones.get(i).x + stones.get(i).getStoneWidth())
                        <= (bubbles.get(1).bubbleX + bubbles.get(1).getWidth()) &&
                        stones.get(i).y>= bubbles.get(1).bubbleY && stones.get(i).y <= (bubbles.get(1).bubbleY + bubbles.get(1).getHeight())){
                    Pop pop = new Pop(context);
                    pop.popX = bubbles.get(1).bubbleX + bubbles.get(1).getWidth()/2 - pop.getPopWidth()/2;
                    pop.popY = bubbles.get(1).bubbleY + bubbles.get(1).getHeight()/2 - pop.getPopHeight()/2;
                    pops.add(pop);
                    bubbles.get(1).resetPosition();
                    count++;
                    stones.remove(i);

                }else if (stones.get(i).x >= redBubbles.get(0).bubbleX && (stones.get(i).x + stones.get(i).getStoneWidth())
                        <= (redBubbles.get(0).bubbleX + bubbles.get(0).getWidth()) &&
                        stones.get(i).y>= redBubbles.get(0).bubbleY && stones.get(i).y <= (redBubbles.get(0).bubbleY + redBubbles.get(0).getHeight())){
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("score",count);
                    context.startActivity(intent);

                }else if (stones.get(i).x >= redBubbles.get(1).bubbleX && (stones.get(i).x + stones.get(i).getStoneWidth())
                        <= (redBubbles.get(1).bubbleX + redBubbles.get(1).getWidth()) &&
                        stones.get(i).y>= redBubbles.get(1).bubbleY && stones.get(i).y <= (redBubbles.get(1).bubbleY + redBubbles.get(1).getHeight())){
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("score",count);
                    context.startActivity(intent);

                }

            }else{
                stones.remove(i);
            }
        }
        for(int j = 0; j<pops.size(); j++){
            canvas.drawBitmap(pops.get(j).getPop(pops.get(j).popFrame),pops.get(j).popX,
                    pops.get(j).popY, null);
            pops.get(j).popFrame++;
            if(pops.get(j).popFrame>4){
                pops.remove(j);
            }
        }
        canvas.drawBitmap(hand,(dWidth/2 - handWidth/2), dHeight-handHeight/2, null );
        canvas.drawText("Score: " + (count),0,TEXT_SIZE, scorePaint);
        handler.postDelayed(runnable,UPDATE_MILLIS);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            if(touchX >= (dWidth/2 - handWidth/2) && touchX <= (dWidth/2 + handWidth/2) && touchY >= (dHeight - handHeight ))
                Log.i("hand", "touched");
            if(stones.size() < 3){
                Stone m = new Stone(context);
                stones.add(m);
            }

        }


        return true;
    }
}
