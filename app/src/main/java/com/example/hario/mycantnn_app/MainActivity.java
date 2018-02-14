package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hario.mycantnn_app.Modal.host_profile_details;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();
    }

    public void hostClick(View view) {
        //Intent host = new Intent(this,Host_login.class);
        // startActivity(host);
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, host_profile_details.class));
            //finish();

        } else {
            startActivity(new Intent(MainActivity.this, Host_login.class));

        }


    }

    public void main2(View view) {

        startActivity(new Intent(this, client.class));
        // finish();
    }
}
