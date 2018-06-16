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
    Bitmap electron;


    public Stone(Context context){
        electron = BitmapFactory.decodeResource(context.getResources(), R.drawable.electron);
        x = GameView.dWidth/2 - getStoneWidth()/2;
        y = GameView.dHeight - GameView.handHeight - getStoneHeight()/2;
        mVelocity = 50;
    }

    public int getStoneWidth(){
        return electron.getWidth();
    }
    public int getStoneHeight(){
        return electron.getHeight();
    }


}
