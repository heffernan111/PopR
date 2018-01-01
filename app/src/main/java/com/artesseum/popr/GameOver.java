package com.artesseum.popr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

public class GameOver extends AppCompatActivity {

    TextView playerScore, peronalBest;
    private DatabaseReference userData;
    Context context;
    String uid;
    String displayname;
    int currentHighScore;
    FirebaseRecyclerAdapter adapter;
    private RecyclerView leaderboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        startQuery();
        leaderboard = findViewById(R.id.leaderBoardView);
        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        final int score = getIntent().getExtras().getInt("score");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userData = FirebaseDatabase.getInstance().getReference();
        ///// get user score
        userData.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HighScore highScore = new HighScore();
                String displayName = dataSnapshot.child(uid).getValue(HighScore.class).getDisplayname();
                final int currentHighScore = dataSnapshot.child(uid).getValue(HighScore.class).getCurrentHighScore();
                peronalBest.setText(""+currentHighScore);

                if(currentHighScore < score){
                    userData.child("users").child(uid).child("currentHighScore").setValue(score);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        playerScore = findViewById(R.id.player_score);
        peronalBest = findViewById(R.id.playerbest_score);
        playerScore.setText(""+score);





    }
    /// get leaderboard
    private void startQuery() {
        Log.i("query", "start");

        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("currentHighScore");


        FirebaseRecyclerOptions<HighScore> options =
                new FirebaseRecyclerOptions.Builder<HighScore>().setQuery(query,HighScore.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<HighScore, scoreHolder>(options) {
            @Override
            public scoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.score_board,parent,false);

                return new scoreHolder(view);

            }
            @Override
            protected void onBindViewHolder(scoreHolder holder, final int position, HighScore model) {

                holder.setDisplayname(model.getDisplayname());
                holder.setCurrentHighScore(model.getCurrentHighScore());


            }


        };

        leaderboard = (RecyclerView) findViewById(R.id.leaderBoardView);
        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        leaderboard.setAdapter(adapter);
        adapter.startListening();



    }
    public class scoreHolder extends RecyclerView.ViewHolder{
        TextView displayname, score;


        public scoreHolder(View itemView){
            super(itemView);
        }


        public void  setDisplayname(String displayname){
            TextView boardName = (TextView) itemView.findViewById(R.id.boardName);
            boardName.setText(displayname);
        }

        public void setCurrentHighScore(int currentHighScore){
            TextView boardScore = itemView.findViewById(R.id.boardScore);
            boardScore.setText(String.valueOf(currentHighScore));
        }


    }


    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        adapter.stopListening();
        finish();
    }


    @Override
    public void onBackPressed() {

    }
}
