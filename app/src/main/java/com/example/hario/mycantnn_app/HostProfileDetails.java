package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.hario.mycantnn_app.Modal.HostActivityMain;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class HostProfileDetails extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btn;
    private TextView username;
    private TextView Email;
    private TextView Contact;
    private TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_profile_details2);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        btn = findViewById(R.id.hostbutton4);
        username = findViewById(R.id.Host_textView);
        Email = findViewById(R.id.hostProfileCardName4);
        Contact = findViewById(R.id.hostProfileCardName6);
        Name = findViewById(R.id.hostProfileCardName2);


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
            signOut();

            // Toast.makeText(HostProfileDetails.this, "LOGOUT Succefully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));
            // finish();
            return true;
        } else
            return false;

    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        //  findViewById(R.id.HostGoggleSign).setVisibility(View.VISIBLE);
        // Google sign out
      /*  mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });*/
    }

}


