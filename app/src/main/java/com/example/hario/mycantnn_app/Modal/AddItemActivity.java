package com.example.hario.mycantnn_app.Modal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.R;
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

/**
 * Created by Hemant Kumar on 1/31/2018.
 */

public class AddItemActivity extends AppCompatActivity {
    private static int GALLARY_REQUEST = 1;
    String ImageDecode, Choice = "";
    private Spinner AddItemSpinner;
    private ImageView itemImage;
    private EditText itemName, itemPrice;
    private TextView itemCount, itemTotalPrice;
    private Button AddItemToCategory;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private Uri ImageUrl;
    public AddItemActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item2);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        AddItemSpinner = findViewById(R.id.CanteenItem_Spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CanteenItem_arrays,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AddItemSpinner.setAdapter(adapter);

        AddItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Choice = AddItemSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Choice = "Snacks";
            }
        });


        itemImage = findViewById(R.id.AddImage);
        itemName = findViewById(R.id.AddItemTitle);
        itemPrice = findViewById(R.id.AddItemPrice);
        itemCount = findViewById(R.id.AddItemCount);
        itemTotalPrice = findViewById(R.id.AddItemTotalPrice);
        AddItemToCategory = findViewById(R.id.AddItemToCategory);

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery =
                        new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLARY_REQUEST);
            }
        });

        AddItemToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImageFileToFirebaseStorage(Choice);
               // Toast.makeText(getApplicationContext(), "Items Will Be Uploaded Shortly", Toast.LENGTH_LONG).show();


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ImageUrl = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(ImageUrl);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            itemImage.setImageBitmap(selectedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        public String GetFileExtension(Uri uri) {

            ContentResolver contentResolver = getContentResolver();

            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

            // Returning the file Extension.
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

        }
    */
    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage(final String Choice) {

        // Checking whether FilePathUri Is empty or not.
        final String Data = itemName.getText().toString().trim();
        final String Cost = itemPrice.getText().toString();

        String TotalCost = itemTotalPrice.getText().toString();
        final int totalcost = Integer.parseInt(TotalCost);
        String Count = itemCount.getText().toString();
        final int count = Integer.parseInt(Count);

        if (ImageUrl != null && !TextUtils.isEmpty(Cost) && !TextUtils.isEmpty(Data) && !TextUtils.isEmpty(Choice)) {

            // Setting progressDialog Title.
            // progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            //progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child("Image").child(ImageUrl.getLastPathSegment());

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(ImageUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //progressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), "Item Uploaded Successfully To Your Category ", Toast.LENGTH_LONG).show();
                            final int cost = Integer.parseInt(Cost);
                            @SuppressWarnings("VisibleForTests")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(Data, count, cost, totalcost, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            String uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            databaseReference.child("HostUser").child("item").child(uuid).child(Choice).child(ImageUploadId).setValue(imageUploadInfo);
                            Toast.makeText(getApplicationContext(), "Items Will Be Uploaded Shortly", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(AddItemActivity.this, HostActivityMain.class));
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            //  progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(AddItemActivity.this,"Something is going wrong", Toast.LENGTH_LONG).show();
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

            Toast.makeText(AddItemActivity.this, "Please Select All Fields Again From Selecting Category", Toast.LENGTH_LONG).show();

        }
    }


}
