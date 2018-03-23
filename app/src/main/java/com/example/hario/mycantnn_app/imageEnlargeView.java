package com.example.hario.mycantnn_app;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class imageEnlargeView extends AppCompatActivity {
    private ImageView enlargeImageview;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_view);
        enlargeImageview=(ImageView)findViewById(R.id.item_image_enlarge_view);
        savedInstanceState = getIntent().getExtras();
        String ImageUrL = savedInstanceState.getString("imageurl");
        Glide.with(this).load(ImageUrL).into(enlargeImageview);

    }
}
