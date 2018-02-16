package com.example.hario.mycantnn_app.Check.Card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Check.CheckOutActivity;
import com.example.hario.mycantnn_app.R;

public class payment extends AppCompatActivity {
    RadioGroup rg;

    public payment() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        rg = findViewById(R.id.radioGroup);

    }

    public void onclick(View view) {
        if (rg.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            Toast.makeText(getBaseContext(), "Select one option", Toast.LENGTH_LONG).show();
        } else {
            // one of the radio buttons is checked
            if (rg.getCheckedRadioButtonId() == R.id.radioButton1 || rg.getCheckedRadioButtonId() == R.id.radioButton2) {

                Intent host = new Intent(this, CheckOutActivity.class);
                startActivity(host);

            } else if (rg.getCheckedRadioButtonId() == R.id.radioButton6) {

                Intent h = new Intent(this, OrderActivity.class);
                startActivity(h);

            }

        }


    }


}
