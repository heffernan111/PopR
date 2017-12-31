package com.artesseum.popr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by apoca on 31/12/2017.
 */

public class Stone {
    int x,y;
    int mVelocity;
    Bitmap stone;


    public Stone(Context context){
        stone = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet11);
        x = GameView.dWidth/2 - getStoneWidth()/2;
        y = GameView.dHeight - GameView.handHeight - getStoneHeight()/2;
        mVelocity = 50;
    }

    public int getStoneWidth(){
        return stone.getWidth();
    }
    public int getStoneHeight(){
        return stone.getHeight();
    }


}
