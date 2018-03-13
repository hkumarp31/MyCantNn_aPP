package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    public static int flag;
    private int no;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = -1;


        // firebaseDatabase = FirebaseDatabase.getInstance().getReference("flagField");
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
    }

    public void hostClick(View view) {
        //Intent host = new Intent(this,Host_login.class);
        // startActivity(host);

        flag = 0;
        if (user == null) {

            startActivity(new Intent(MainActivity.this, Host_login.class));


        } else {
            startActivity(new Intent(MainActivity.this, HostActivityMain.class));
            finish();
        }
    }

    public void main2(View view) {
        flag = 1;

        if (user == null) {

            startActivity(new Intent(MainActivity.this, Host_login.class));

        }
        else {


            startActivity(new Intent(this, client.class));
            finish();
        }

    }
}
