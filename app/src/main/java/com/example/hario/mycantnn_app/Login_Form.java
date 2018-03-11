package com.example.hario.mycantnn_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_Form extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private TextView EMail;
    private TextView PassWord;
    private TextView Confirm_pwd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);


        firebaseAuth = FirebaseAuth.getInstance();
        EMail = findViewById(R.id.formTExt4);
        PassWord = findViewById(R.id.formTExt3);
        Confirm_pwd = findViewById(R.id.formTExt5);
        CheckBox check = findViewById(R.id.checkBox1);
        Button button = findViewById(R.id.button3);
        progressDialog = new ProgressDialog(this);
// show PassWord using checkBox
        check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (isChecked) {
                    // show password
                    PassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Confirm_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


                } else if (!isChecked) {
                    PassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Confirm_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }


        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EMail.getText().toString().trim();
                String password = PassWord.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Please Wait..");
                progressDialog.show();
                //create user
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Login_Form.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressDialog.hide();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Login_Form.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {


                                    startActivity(new Intent(Login_Form.this, Host_login.class));
                                    finish();

                                    Toast.makeText(Login_Form.this, "Register Successfully",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}

