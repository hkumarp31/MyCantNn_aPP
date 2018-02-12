package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hostClick(View view) {
        //Intent host = new Intent(this,Host_login.class);
        // startActivity(host);
        startActivity(new Intent(MainActivity.this, Host_login.class));
        finish();

    }

    public void main2(View view) {

        startActivity(new Intent(this, client.class));
        finish();
    }
}
