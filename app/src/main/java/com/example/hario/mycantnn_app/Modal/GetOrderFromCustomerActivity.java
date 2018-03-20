package com.example.hario.mycantnn_app.Modal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hario.mycantnn_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Hemant Kumar on 2/8/2018.
 */

public class GetOrderFromCustomerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<getOrderItemClass> arrayList=new ArrayList<>();
    private GetOrderRecyclerAdapter getOrderRecyclerAdapter;
    private DatabaseReference databaseReference;
    private TextView textView;
    private Context context;
    private Button Refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getorder);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("HostUser");

        recyclerView = findViewById(R.id.getOrderRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnap : dataSnapshot.getChildren()) {

                    getOrderItemClass item = postsnap.getValue(getOrderItemClass.class);
                    arrayList.add(item);
                    Log.e(TAG, "Count: " + item.getCount());
                    Log.e(TAG, "Title/DAta: " + item.getData());
                    Log.e(TAG, "Cost/Price: " + item.getPrice());
                    Log.e(TAG, "ID: " + item.getId());
                    Log.e(TAG, "TotalCost: " + item.getTotalCost());
                    Log.e(TAG, "ImageURL: " + item.getImage());
                }
                getOrderRecyclerAdapter = new GetOrderRecyclerAdapter(arrayList,textView,context);
                recyclerView.setAdapter(getOrderRecyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu manu) {
        getMenuInflater().inflate(R.menu.refresh_button, manu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refreshButton) {
            startActivity(new Intent(getApplicationContext(),GetOrderFromCustomerActivity.class));
            finish();
            overridePendingTransition(0, 0);

            return true;
        }
        else
            return false;

    }

}
