package com.example.hario.mycantnn_app.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.example.hario.mycantnn_app.profile;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 2/13/2018.
 */

public class ClientNotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ClientNotificationAdapter clientNotificationAdapter;
    private ArrayList<ClientNotificationInfo> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientnotification_layout);
        recyclerView = findViewById(R.id.ClientNotification_RecyclerView);

        arrayList = fillProjectDetail();
        clientNotificationAdapter = new ClientNotificationAdapter(arrayList);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clientNotificationAdapter);

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
    private ArrayList<ClientNotificationInfo> fillProjectDetail(){
        ArrayList<ClientNotificationInfo> pappuinfoArrayList = new ArrayList<>();
        ClientNotificationInfo p=new ClientNotificationInfo(android.R.drawable.ic_menu_report_image,"Pappu","Clear");
        pappuinfoArrayList.add(p);
        pappuinfoArrayList.add(new ClientNotificationInfo(android.R.drawable.arrow_down_float,"ghonchu","Not Clear"));

        return pappuinfoArrayList;
    }
}
