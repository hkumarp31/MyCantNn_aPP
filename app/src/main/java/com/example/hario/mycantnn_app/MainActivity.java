package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static int flag;
    private int no;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference firebaseDatabase;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        flag = -1;


        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
    /*    Log.e("my name","hariom");

        if(user==null)
        { findViewById(R.id.button2).setVisibility(View.VISIBLE);
            findViewById(R.id.button1).setVisibility(View.VISIBLE);
        }

        else
        {
            Log.e("my data","what my name");
            final DatabaseReference myDatabase = firebaseDatabase.child("FlagFile");

            myDatabase.addValueEventListener(new ValueEventListener() {
                @Override


                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e("my data","what r y uy");
                  value=(String)dataSnapshot.child(user.getUid()).getValue();

                    Log.e("my data","what my name 123" );
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            if(value.equals("0"))
            {  findViewById(R.id.button2).setVisibility(View.GONE);
            }
            else {
                findViewById(R.id.button1).setVisibility(View.GONE);

            }
        }*/

    }

    public void hostClick(View view) {
        //Intent host = new Intent(this,Host_login.class);
        // startActivity(host);


        flag = 0;
        if (user == null) {
            startActivity(new Intent(MainActivity.this, Host_login.class));
            finish();
        } else {

            startActivity(new Intent(MainActivity.this, HostActivityMain.class));
            // finish();
        }
    }

    public void main2(View view) {
        flag = 1;

        if (user == null) {
//            findViewById(R.id.button1).setVisibility(View.GONE);

            startActivity(new Intent(MainActivity.this, Host_login.class));
            finish();
        }
        else {


            startActivity(new Intent(this, client.class));
            //  finish();
        }

    }
}
