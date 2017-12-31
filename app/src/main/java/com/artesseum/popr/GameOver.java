package com.artesseum.popr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by apoca on 31/12/2017.
 */

public class GameOver extends Activity {

    TextView playerScore, peronalBest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int score = getIntent().getExtras().getInt("score");
        SharedPreferences pref = getSharedPreferences("MyPref",0);
        int scoreSP = pref.getInt("scoreSP",0);
        SharedPreferences.Editor editor = pref.edit();
        //onBackPressed();





        if(score > scoreSP){
            scoreSP = score;
            editor.putInt("ScoreSP", scoreSP);
            editor.commit();
        }
        playerScore = findViewById(R.id.player_score);
        peronalBest = findViewById(R.id.playerbest_score);
        playerScore.setText(""+score);
        peronalBest.setText(""+scoreSP);

    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
