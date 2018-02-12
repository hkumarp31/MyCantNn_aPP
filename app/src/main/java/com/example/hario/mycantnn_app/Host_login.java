package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.example.hario.mycantnn_app.Modal.host_profile_details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Host_login extends AppCompatActivity {
    private EditText inputEmail;
    private EditText PassWord;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button button;
    private CheckBox check1;
    private TextView signup;
    private TextView forgetPw;

    // private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //    FacebookSdk.sdkInitialize(getApplicationContext());
        //  AppEventsLogger.activateApp(this);
        // This MUST be placed after the above two lines.


        setContentView(R.layout.host_login);

        auth = FirebaseAuth.getInstance();

        button = findViewById(R.id.HostButton);
        inputEmail = findViewById(R.id.HostEMail);
        progressBar = findViewById(R.id.HostprogressBar);

        check1 = findViewById(R.id.HostLogin_check);
        PassWord = findViewById(R.id.HostPwd);
        signup = findViewById(R.id.HostSignup);
        forgetPw = findViewById(R.id.HostForget);




/*        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                       // boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                    startActivity(new Intent(Host_login.this,host_profile_details.class));
                    finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(Host_login.this,"Authification failed", Toast.LENGTH_LONG).show();
                        // App code
                    }
                });




*/


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Host_login.this, Host_login.class));
            finish();
        }


        // Listening to register new account link
        forgetPw.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), forgetpwd.class);
                startActivity(i);
                finish();
            }
        });

//Visible PassWord
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (isChecked) {
                    // show password
                    PassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);


                } else if (!isChecked) {
                    PassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }


        });


        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), Login_Form.class);
                startActivity(i);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = PassWord.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Host_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        PassWord.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Host_login.this, "Authification failed Check Email And PassWord", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Host_login.this, host_profile_details.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu manu) {
        getMenuInflater().inflate(R.menu.menu_main, manu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            startActivity(new Intent(this, forgetpwd.class));
            return true;
        } else if (id == R.id.AddItemButton_main_menu) {
            startActivity(new Intent(this, HostActivityMain.class));
            finish();
            return true;
        } else if (id == R.id.cart)

        {
            return true;
        } else if (id == R.id.order) {
            return true;
        } else if (id == R.id.logout) {
            Toast.makeText(Host_login.this, "LOGOUT Succefully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        } else
            return false;

    }


}





