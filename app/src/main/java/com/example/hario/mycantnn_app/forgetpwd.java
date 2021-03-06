package com.example.hario.mycantnn_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpwd extends Activity {
    private EditText inputEmail;
    //   private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button btnReset;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);
        auth = FirebaseAuth.getInstance();
        //  progressBar = findViewById(R.id.ForgetprogressBar);
        btnReset = findViewById(R.id.SubmitButton);
        inputEmail = findViewById(R.id.forgetEmail);
        progressDialog = new ProgressDialog(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                // progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgetpwd.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(forgetpwd.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }
                                progressDialog.hide();
                                //progressBar.setVisibility(View.GONE);

                                startActivity(new Intent(forgetpwd.this, MainActivity.class));
                                finish();
                            }
                        });
            }
        });


    }


}

