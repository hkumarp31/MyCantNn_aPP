package com.example.hario.mycantnn_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.Modal.CartActivity;
import com.example.hario.mycantnn_app.Modal.ClientNotificationActivity;
import com.example.hario.mycantnn_app.Modal.CustomerMyOrderActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profile extends AppCompatActivity {

    private Button button1;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView username;
    private TextView Email;
    private TextView Contact;
    private TextView Name;
    private FirebaseUser user;
    private String UiidId = "";
    private ImageView img;

    private ProgressDialog progressBar;

    //  private FirebaseDatabase mdatabase;
    private DatabaseReference currentuserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        Intent intent = new Intent(profile.this, client.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_OrderStatus12:
                        Intent intent1 = new Intent(profile.this, CustomerMyOrderActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_cart:

                        Intent intent2 = new Intent(profile.this, CartActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_profile:
                        break;

                    case R.id.ClientNotificationBottomNavigation:
                        startActivity(new Intent(profile.this, ClientNotificationActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


        button1 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(profile.this, profile_edit_page.class));
            }
        });


        // Auth Work


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

        user = mAuth.getCurrentUser();
        if (user != null) {
            UiidId = user.getUid();
        }

        username = findViewById(R.id.profile_textView);
        Email = findViewById(R.id.ProfileCardName4);
        Contact = findViewById(R.id.ProfileCardName6);
        Name = findViewById(R.id.ProfileCardName2);
        progressBar = new ProgressDialog(this);
        img = findViewById(R.id.imageView2);

        currentuserDatabase = FirebaseDatabase.getInstance().getReference().child("ClientUser").child("UserProfile");
        currentuserDatabase.keepSynced(true);
        if (currentuserDatabase.child(mAuth.getCurrentUser().getUid()) == null) {

        } else {
            final DatabaseReference myDatabase = currentuserDatabase.child(UiidId);


            progressBar.setMessage("Wait...");
            // Showing progressDialog.
            progressBar.show();
            myDatabase.addValueEventListener(new ValueEventListener() {
                @Override


                public void onDataChange(DataSnapshot dataSnapshot) {
                    String CANTTEN = (String) dataSnapshot.child("user").getValue();
                    String EMAIL = (String) dataSnapshot.child("email").getValue();
                    String CONTACT = (String) dataSnapshot.child("contact").getValue();
                    String NAME = (String) dataSnapshot.child("name").getValue();
                    String url = (String) dataSnapshot.child("image").getValue();
                    Glide.with(getApplicationContext()).load(url).into(img);

                    username.setText(CANTTEN);
                    Email.setText(EMAIL);
                    Contact.setText(CONTACT);
                    Name.setText(NAME);

                    progressBar.hide();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Database Error : OnCancelled", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();

                }
            });


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.LogoutProfile) {
            mAuth.signOut();
            startActivity(new Intent(profile.this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }








}
