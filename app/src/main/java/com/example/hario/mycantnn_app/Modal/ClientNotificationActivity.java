package com.example.hario.mycantnn_app.Modal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hario.mycantnn_app.MainActivity;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.example.hario.mycantnn_app.forgetpwd;
import com.example.hario.mycantnn_app.profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Hemant Kumar on 2/13/2018.
 */

public class ClientNotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ClientNotificationAdapter clientNotificationAdapter;
    private ArrayList<getOrderItemClass> arrayList=new ArrayList<>();
    private DatabaseReference databaseReference;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientnotification_layout);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClientUser");

        recyclerView = findViewById(R.id.ClientNotification_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference.child("OrderStatusNotify").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
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
                clientNotificationAdapter = new ClientNotificationAdapter(arrayList);
                recyclerView.setAdapter(clientNotificationAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // recyclerView.setAdapter(clientNotificationAdapter);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        Intent intent = new Intent(ClientNotificationActivity.this, client.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_OrderStatus12:
                        Intent intent1 = new Intent(ClientNotificationActivity.this, CustomerMyOrderActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_cart:

                        Intent intent2 = new Intent(ClientNotificationActivity.this, CartActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_profile:
                        startActivity(new Intent(ClientNotificationActivity.this, profile.class));
                        finish();
                        overridePendingTransition(0, 0);

                        break;

                    case R.id.ClientNotificationBottomNavigation:
                        break;
                }
                return false;
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
            startActivity(new Intent(getApplicationContext(),ClientNotificationActivity.class));
            finish();
            overridePendingTransition(0, 0);

            return true;
        }
        else
            return false;

    }

}
