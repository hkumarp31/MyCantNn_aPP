package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Modal.UploadUserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;

public class profile_edit_page extends AppCompatActivity {


    private EditText name;
    private EditText email;
    private EditText contact;
    private Button save;
    private ImageView img;
    private Uri imageUrl;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth auth;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_page);

        storageReference = FirebaseStorage.getInstance().getReference().child("ClientUser");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClientUser");


        name = findViewById(R.id.ProfileEdittext);
        email = findViewById(R.id.ProfileEdittext1);
        contact = findViewById(R.id.ProfileEdittext2);
        save = findViewById(R.id.profile_edit_button);
        img = findViewById(R.id.ProfileimageView);
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.profile_textView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery =
                        new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, 1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadImageFileToFirebaseStorage();
               // Toast.makeText(getApplicationContext(), "Your details have been Uploaded Successfully", Toast.LENGTH_LONG).show();

            }
        });

        username.setText(auth.getCurrentUser().getUid());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            imageUrl = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUrl);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(selectedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void UploadImageFileToFirebaseStorage() {


        final String Name = name.getText().toString().trim();
        final String Email = email.getText().toString();
        final String Contact = contact.getText().toString();
        final String UserName = username.getText().toString().trim();

/*
        String TotalCost = itemTotalPrice.getText().toString();
        final   int totalcost= Integer.parseInt(TotalCost);
        String Count = itemCount.getText().toString();
        final   int count = Integer.parseInt(Count);
*/
        if (imageUrl != null && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Contact)) {

            // Setting progressDialog Title.
            // progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            //progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child("Image").child(imageUrl.getLastPathSegment());

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(imageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //progressDialog.dismiss();


                            @SuppressWarnings("VisibleForTests")
                            UploadUserData imageUploadInfo = new UploadUserData(Name, Email, Contact, taskSnapshot.getDownloadUrl().toString(), UserName);

                            // Getting image upload ID.
                            // String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child("UserProfile").child(auth.getCurrentUser().getUid()).setValue(imageUploadInfo);


                            startActivity(new Intent(profile_edit_page.this, profile.class));
                            Toast.makeText(getApplicationContext(), "Profile Details Uploaded Successfully", Toast.LENGTH_LONG).show();

                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            //  progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(profile_edit_page.this, "Error In Uploading Details. Try Again Later!", Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            //  progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        } else {

            Toast.makeText(profile_edit_page.this, "Please Fill All Details", Toast.LENGTH_LONG).show();

        }


    }


}
