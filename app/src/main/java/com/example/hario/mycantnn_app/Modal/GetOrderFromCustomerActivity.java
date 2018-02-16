package com.example.hario.mycantnn_app.Modal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hario.mycantnn_app.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 2/8/2018.
 */

public class GetOrderFromCustomerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<getOrderItemClass> arrayList;
    private GetOrderRecyclerAdapter getOrderRecyclerAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getorder);
        recyclerView = findViewById(R.id.getOrderRecyclerView);

        arrayList = fillProjectDetail2();
        getOrderRecyclerAdapter = new GetOrderRecyclerAdapter(arrayList);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getOrderRecyclerAdapter);

    }

    private ArrayList<getOrderItemClass> fillProjectDetail2() {
        ArrayList<getOrderItemClass> projectinfoArrayList = new ArrayList<>();
        getOrderItemClass p = new getOrderItemClass("Hemant", 5, 10, 50, "123456789");
        projectinfoArrayList.add(p);
        projectinfoArrayList.add(new getOrderItemClass("PIZZa", 6, 10, 600, "2345654"));
        projectinfoArrayList.add(new getOrderItemClass("DAbba Dabba", 5, 6, 30, "987654321"));
        return projectinfoArrayList;
    }
}
