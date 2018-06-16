package com.artesseum.popr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by apoca on 31/12/2017.
 */

public class Pop {
    Bitmap pop[] = new Bitmap[5];
    int popFrame=0;
    int popX, popY;



    public Pop(Context context){
        pop[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.neutron);
        pop[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.neutron);
        pop[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.neutron);
        pop[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.neutron);
        pop[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.neutron);


    }
    public Bitmap getPop(int popFrame){
        return pop[popFrame];
    }
    public int getPopWidth(){
        return pop[0].getWidth();
    }
    public int getPopHeight(){
        return pop[0].getHeight();
    }

}
