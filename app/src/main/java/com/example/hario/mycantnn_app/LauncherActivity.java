package com.example.hario.mycantnn_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.hario.mycantnn_app.Check.Utils.CreditCardEditText.value;

public class LauncherActivity extends Activity {
    public static int myno = 0;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference mydata;
    private TextView mytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mytext = findViewById(R.id.launcerTextView);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (user != null) {
                    mydata = FirebaseDatabase.getInstance().getReference().child("flagfile").child(user.getUid());
                    mydata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.e("Array LIst", dataSnapshot + "");
                            value = (String) dataSnapshot.child("flag").getValue();

                            Log.e("value user", value + "");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                Intent i = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}