package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
    }

    public void hostClick(View view) {
        //Intent host = new Intent(this,Host_login.class);
        // startActivity(host);


        if (user == null) {
            startActivity(new Intent(MainActivity.this, Host_login.class));

        } else
            startActivity(new Intent(MainActivity.this, HostActivityMain.class));
    }

    public void main2(View view) {

        startActivity(new Intent(this, client.class));
        finish();
    }
}
