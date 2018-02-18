package com.example.hario.mycantnn_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class client_login extends AppCompatActivity {
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private EditText inputEmail;
    private EditText PassWord;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button button;
    private CheckBox check1;
    private TextView signup;
    private TextView forgetPw;
    private SignInButton Googlebtn;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);

        button = findViewById(R.id.clientButton);
        inputEmail = findViewById(R.id.clientEMail);
        Googlebtn = findViewById(R.id.clientGoggleSign);
        check1 = findViewById(R.id.clientLogin_check);
        PassWord = findViewById(R.id.clientPwd);
        signup = findViewById(R.id.clientSignup);
        forgetPw = findViewById(R.id.clientForget);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        // Listening to register new account link
        forgetPw.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), forgetpwd.class);
                startActivity(i);

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


                //finish();
            }
        });


    }
}
