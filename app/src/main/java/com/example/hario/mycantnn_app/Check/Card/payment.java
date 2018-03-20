package com.example.hario.mycantnn_app.Check.Card;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Check.CheckOutActivity;
import com.example.hario.mycantnn_app.R;

public class payment extends AppCompatActivity {
    private RadioGroup rg;


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
                finish();

            } else if (rg.getCheckedRadioButtonId() == R.id.radioButton6) {

               confirm();

            }

        }


    }

    public void confirm() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        } else {

            builder = new AlertDialog.Builder(this);


        }
        builder.setTitle("CONFORMATION")
                .setMessage("Are you sure you want to place this order ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(payment.this,OrderActivity.class));

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}
