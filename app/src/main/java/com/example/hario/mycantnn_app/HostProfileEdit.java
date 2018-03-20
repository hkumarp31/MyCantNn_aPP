package com.example.hario.mycantnn_app;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;

public class HostProfileEdit extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText contact;
    private Button save;
    private ImageView img;
    private Uri imageUrl;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private EditText canteenName;
    private FirebaseAuth auth;
    private ProgressDialog progressBar;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_profile_edit);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("HostUser");

        canteenName = findViewById(R.id.hostprofile_textView);
        name = findViewById(R.id.hostProfileEdittext);
        email = findViewById(R.id.hostProfileEdittext1);
        contact = findViewById(R.id.hostProfileEdittext2);
        save = findViewById(R.id.hostprofile_edit_button);
        img = findViewById(R.id.hostprofileimageView);
        auth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);
        user = auth.getCurrentUser();


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
                ProfileUploding();
                Toast.makeText(getApplicationContext(), "Your Details Updated Successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });

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


    private void ProfileUploding() {

        if (user != null) {

            final String Name = name.getText().toString().trim();
            final String Email = email.getText().toString();
            final String Contact = contact.getText().toString();
            final String canteen = canteenName.getText().toString();
            final String uid = user.getUid();


            if (imageUrl != null && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Contact)) {

                progressBar.setMessage("Your Details Updating...");
                // Showing progressDialog.
                progressBar.show();

                // Creating second StorageReference.
                StorageReference storageReference2nd = storageReference.child("Image").child(imageUrl.getLastPathSegment());

                // Adding addOnSuccessListener to second StorageReference.
                storageReference2nd.putFile(imageUrl)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                //progressDialog.dismiss();

                               // Toast.makeText(getApplicationContext(), "Item Uploaded Successfully To Your Category ", Toast.LENGTH_LONG).show();

                                @SuppressWarnings("VisibleForTests")
                                UploadUserData imageUploadInfo = new UploadUserData(Name, Email, Contact, taskSnapshot.getDownloadUrl().toString(), uid, canteen);

                                // Getting image upload ID.
                                // String ImageUploadId = databaseReference.push().getKey();

                                // Adding image upload id s child element into databaseReference.
                                databaseReference.child("UserProfile").child(auth.getCurrentUser().getUid()).setValue(imageUploadInfo);


                                startActivity(new Intent(HostProfileEdit.this, HostProfileDetails.class));
                            }
                        })
                        // If something goes wrong .
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                // Hiding the progressDialog.
                                //  progressDialog.dismiss();

                                // Showing exception erro message.
                                Toast.makeText(HostProfileEdit.this, exception.getMessage(), Toast.LENGTH_LONG).show();
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

                Toast.makeText(HostProfileEdit.this, "Please Fill All Details ", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(HostProfileEdit.this, "Please LOGIN First", Toast.LENGTH_LONG).show();


        }

    }


}
