package com.example.hario.mycantnn_app.Check.Card;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.hario.mycantnn_app.R;

import java.util.UUID;

public class OrderActivity extends AppCompatActivity {

    private EditText ab;
    private String bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__order);

        //android.support.v7.widget.Toolbar tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        // setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //startActivity(new Intent(this,finalstatus.class));

    }




    public void tofinalstatus(View view) {
        Intent j = new Intent(this, finalstatus.class);
        startActivity(j);
        finish();


    }


}
