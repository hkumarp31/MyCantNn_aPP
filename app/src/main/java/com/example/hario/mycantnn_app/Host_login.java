package com.example.hario.mycantnn_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.example.hario.mycantnn_app.Modal.UploadUserData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.hario.mycantnn_app.Check.Utils.CreditCardEditText.flag;

public class Host_login extends Activity {
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private EditText inputEmail;
    private EditText PassWord;
    private FirebaseAuth mAuth;
    private ProgressDialog progressBar;
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
    private DatabaseReference databaseReference;
    private GoogleSignInAccount account;

    private RelativeLayout HostProfile;
    private DatabaseReference myper;
    private String mystr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_login);
     /*   Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));
        }*/
        // mAuth = FirebaseAuth.getInstance();

        button = findViewById(R.id.HostButton);
        inputEmail = findViewById(R.id.HostEMail);
        progressBar = new ProgressDialog(this);
        Googlebtn = findViewById(R.id.HostGoggleSign);
        HostProfile = findViewById(R.id.Host_ProfileLayour);
        check1 = findViewById(R.id.HostLogin_check);
        PassWord = findViewById(R.id.HostPwd);
        signup = findViewById(R.id.HostSignup);
        forgetPw = findViewById(R.id.HostForget);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {

            FirebaseUser name = mAuth.getCurrentUser();
            if (flag == 0) {
                if (databaseReference.child("HostUser").child("User").child(name.getUid()) == null) {
                    startActivity(new Intent(Host_login.this, HostProfileEdit.class));
                    finish();
                } else {
                    startActivity(new Intent(Host_login.this, HostActivityMain.class));
                    finish();

                }


            } else {
                if (databaseReference.child("ClientUser").child("UserProfile").child(name.getUid()) == null) {
                    startActivity(new Intent(Host_login.this, profile_edit_page.class));
                    finish();
                } else {
                    startActivity(new Intent(Host_login.this, client.class));
                    finish();
                }

            }
        }


        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]

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

                progressBar.setMessage("Login..");
                progressBar.show();

                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Host_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.hide();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        PassWord.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Host_login.this, "Authification failed Check Email And PassWord", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    FirebaseUser name = mAuth.getCurrentUser();
                                    if (flag == 0) {

                                        final DatabaseReference mydata = FirebaseDatabase.getInstance().getReference("flagfile").child(mAuth.getCurrentUser().getUid()).child("flag");

                                        mydata.setValue("0");
                                        if (databaseReference.child("HostUser").child("User").child(name.getUid()) == null) {
                                            startActivity(new Intent(Host_login.this, HostProfileEdit.class));
                                            finish();
                                        } else {
                                            startActivity(new Intent(Host_login.this, HostActivityMain.class));
                                            finish();

                                        }


                                    } else {
                                        final DatabaseReference mydata = FirebaseDatabase.getInstance().getReference("flagfile").child(mAuth.getCurrentUser().getUid()).child("flag");

                                        mydata.setValue("1");
                                        if (databaseReference.child("ClientUser").child("UserProfile").child(name.getUid()) == null) {
                                            startActivity(new Intent(Host_login.this, profile_edit_page.class));

                                            finish();
                                        } else {
                                            startActivity(new Intent(Host_login.this, client.class));
                                            finish();

                                        }

                                    }

                                }
                            }
                        });
            }
        });

        Googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });


    }


    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.hostLayout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //  mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            //mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.HostGoggleSign).setVisibility(View.GONE);
            //  startActivity(new Intent(Host_login.this, host_profile_details.class));
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.HostGoggleSign).setVisibility(View.VISIBLE);
            // findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }


    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            if (account != null) {
                String personName = account.getDisplayName();
                String personEmail = account.getEmail();
                Uri personPhoto = account.getPhotoUrl();


                final UploadUserData imageUploadInfo = new UploadUserData(personName, personEmail, "", personPhoto.toString(), "");

                // Getting image upload ID.
                // String ImageUploadId = databaseReference.push().getKey();

                // Adding image upload id s child element into databaseReference.

                if (flag == 0) {
                    myper = databaseReference.child("HostUser").child("UserProfile").child(mAuth.getCurrentUser().getUid());

                    myper.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("email").getValue() == null) {
                                databaseReference.child("HostUser").child("UserProfile").child(mAuth.getCurrentUser().getUid()).setValue(imageUploadInfo);
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    String user = mAuth.getCurrentUser().getUid();
                    //Firebase firebase = new Firebase(SyncStateContract.Constants.FIREBASE_URL_USER_TASKS).child(Utils.encodeEmail(unProcessedEmail));

                    // Query queryRef = databaseReference.orderByChild(user);


                    myper = databaseReference.child("ClientUser").child("UserProfile").child(user);
                    //   final Query queryRef = myper.orderByChild(user).equalTo(user);
                    myper.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child("email").getValue() == null) {
                                databaseReference.child("ClientUser").child("UserProfile").child(mAuth.getCurrentUser().getUid()).setValue(imageUploadInfo);
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

            }

                mProgressDialog.hide();
                FirebaseUser name = mAuth.getCurrentUser();
                if (flag == 0) {
                    if (databaseReference.child("HostUser").child("UserProfile").child(name.getUid()) == null) {
                        startActivity(new Intent(Host_login.this, HostProfileEdit.class));

                        finish();
                    } else {
                        startActivity(new Intent(Host_login.this, HostActivityMain.class));
                        databaseReference.child("flagfile").child(mAuth.getCurrentUser().getUid()).child("flag").setValue("0");

                        finish();
                    }
                    finish();


                } else {


                    if (databaseReference.child("ClientUser").child("UserProfile").child(name.getUid()) == null) {
                        startActivity(new Intent(Host_login.this, profile_edit_page.class));


                        finish();
                    } else {
                        startActivity(new Intent(Host_login.this, client.class));
                        databaseReference.child("flagfile").child(mAuth.getCurrentUser().getUid()).child("flag").setValue("1");

                        finish();
                    }

                    finish();
                }


            }
        }


    }

