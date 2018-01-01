package com.artesseum.popr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    public  static  final int RC_SIGN_IN = 123;
    private FirebaseAuth auth;
    private DatabaseReference userData;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String displayname = user.getDisplayName();
                String uid = user.getUid();

                userData = FirebaseDatabase.getInstance().getReference();
                userData.child("users").child(uid).child("displayname").setValue(displayname);

                mainActivity();



                // ...
            } else {
                            // Sign in failed, check response for error code
                            // ...
                Log.i("log in", "failed");
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test");

        myRef.setValue("Hello, World!");



        if (auth.getCurrentUser() != null){



            mainActivity();

        }else{

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(), RC_SIGN_IN);





        }
    }

    private void mainActivity() {

        ImageButton startGame = findViewById(R.id.startGame);
        ImageButton logOut = findViewById(R.id.logout);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartGame.class);
                startActivity(intent);
                finish();
            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent restart = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext()
                        .getPackageName());
                restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(MainActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
                startActivity(restart);
                finish();

            }
        });





    }


    public void startGame(View v){
        Log.i("Image Button","clicked");
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();


    }


    public void logOut(View v){
        Log.i("Logout","clicked");
        FirebaseAuth.getInstance().signOut();
        Intent restart = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext()
                .getPackageName());
        restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(MainActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
        startActivity(restart);
        finish();


    }



}

