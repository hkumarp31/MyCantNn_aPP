package com.example.hario.mycantnn_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.hario.mycantnn_app.Check.Utils.CreditCardEditText.value;

public class MainActivity extends AppCompatActivity {
    public static int flag;
    private int no;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference mydata;

    //private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
//progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            //   progressDialog.setMessage("Please Wait..");
            //  progressDialog.show();
         /*  mydata = FirebaseDatabase.getInstance().getReference().child("flagfile").child(user.getUid());
            mydata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   Log.e("Array LIst",dataSnapshot+"");
                    value = (String)dataSnapshot.child("flag").getValue();

                    Log.e("value user", value+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/

         /*   if (value.equals("0")) {
                findViewById(R.id.button1).setVisibility(View.VISIBLE);
                findViewById(R.id.button2).setVisibility(View.GONE);

            } else if(value.equals("1")) {
                findViewById(R.id.button2).setVisibility(View.VISIBLE);
                findViewById(R.id.button1).setVisibility(View.GONE);

            }
*/

            //       progressDialog.hide();
        }




    }

    public void hostClick(View view) {


        flag = 0;
        if (user == null) {
            startActivity(new Intent(MainActivity.this, Host_login.class));
            finish();
        } else {
            if (value.equals("1")) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Set the Alert Dialog Message
                builder.setMessage("you Already Login With Client")
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // Restart the Activity

                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                startActivity(new Intent(MainActivity.this, HostActivityMain.class));
                finish();
            }
        }
    }
    public void main2(View view) {
        flag = 1;

        if (user == null) {

            startActivity(new Intent(MainActivity.this, Host_login.class));
            finish();
        }
        else {
            if (value.equals("0")) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Set the Alert Dialog Message
                builder.setMessage("you Already Login With Host")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // Restart the Activity

                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            startActivity(new Intent(this, client.class));
            finish();
        }

    }
}
