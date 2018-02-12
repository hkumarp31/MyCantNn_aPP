package com.example.hario.mycantnn_app.Check.Card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hario.mycantnn_app.R;

public class card_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.hide();
        Toolbar tb = findViewById(R.id.toolbark);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void oc(View v) {
        EditText Ed1 = findViewById(R.id.ed1);
        EditText Ed2 = findViewById(R.id.ed2);
        EditText Ed3 = findViewById(R.id.ed3);
        EditText Ed4 = findViewById(R.id.ed4);
        EditText Ed5 = findViewById(R.id.ed5);

        if (isEmpty(Ed1) || isEmpty(Ed2) || isEmpty(Ed3) || isEmpty(Ed4) || isEmpty(Ed5)) {
            Toast.makeText(this, "Fill All The Fields", Toast.LENGTH_SHORT).show();

        }


    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

}



