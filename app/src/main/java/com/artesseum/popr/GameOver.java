package com.artesseum.popr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by apoca on 31/12/2017.
 */

public class GameOver extends Activity {

    TextView playerScore, peronalBest;
    private DatabaseReference userData;
    Context context;
    String uid;
    String displayname;
    int currentHighScore;


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        final int score = getIntent().getExtras().getInt("score");
        //onBackPressed();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();



        userData = FirebaseDatabase.getInstance().getReference();
        userData.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HighScore highScore = new HighScore();
                String displayName = dataSnapshot.child(uid).getValue(HighScore.class).getDisplayname();
                int currentHighScore = dataSnapshot.child(uid).getValue(HighScore.class).getCurrentHighScore();

                if(currentHighScore < score){
                    userData.child("users").child(uid).child("currentHighScore").setValue(score);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       // userData.child("users").child(uid).child("currentHighScore").setValue(score);

        playerScore = findViewById(R.id.player_score);
        peronalBest = findViewById(R.id.playerbest_score);
        playerScore.setText(""+score);

    }


    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
