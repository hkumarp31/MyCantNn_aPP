package com.example.hario.mycantnn_app.Modal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hario.mycantnn_app.R;

public class host_profile_details extends AppCompatActivity {
//    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_profile_details);

        // auth = FirebaseAuth.getInstance();
    }

  /*  @Override
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

            return true;
        } else if (id == R.id.cart)

        {
            return true;
        } else if (id == R.id.order) {
            return true;
        } else if (id == R.id.logout) {
            Toast.makeText(host_profile_details.this, "LOGOUT Succefully", Toast.LENGTH_SHORT).show();


            auth.signOut();

// this listener will be called when there is change in firebase user session
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(host_profile_details.this, MainActivity.class));
                        finish();
                    }
                }
            };

            startActivity(new Intent(host_profile_details.this, MainActivity.class));
            return true;
        }

        return false;

    }*/











}


