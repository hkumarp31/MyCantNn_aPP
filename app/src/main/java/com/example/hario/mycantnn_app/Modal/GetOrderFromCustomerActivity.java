package com.example.hario.mycantnn_app.Modal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hario.mycantnn_app.R;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getorder);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");

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
                    Log.e(TAG, "Cost/Price: " + item.getCost());
                    Log.e(TAG, "ID: " + item.getOrderID());
                    Log.e(TAG, "TotalCost: " + item.getTotal());
                    Log.e(TAG, "ImageURL: " + item.getImage());
                }
                getOrderRecyclerAdapter = new GetOrderRecyclerAdapter(arrayList);
                recyclerView.setAdapter(getOrderRecyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
/*
    private ArrayList<getOrderItemClass> fillProjectDetail2() {
        ArrayList<getOrderItemClass> projectinfoArrayList = new ArrayList<>();
        getOrderItemClass p = new getOrderItemClass("Hemant", 5, 10, 50, "123456789");
        projectinfoArrayList.add(p);
        projectinfoArrayList.add(new getOrderItemClass("PIZZa", 6, 10, 600, "2345654"));
        projectinfoArrayList.add(new getOrderItemClass("DAbba Dabba", 5, 6, 30, "987654321"));
        return projectinfoArrayList;
    }
    */
}
