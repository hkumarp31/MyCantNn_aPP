package com.example.hario.mycantnn_app.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.example.hario.mycantnn_app.profile;

public class OrderStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:

                        Intent intent = new Intent(OrderStatus.this, client.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_OrderStatus12:

                        break;

                    case R.id.navigation_cart:

                        Intent intent1 = new Intent(OrderStatus.this, CartActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_profile:

                        Intent intent2 = new Intent(OrderStatus.this, profile.class);
                        finish();
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.ClientNotificationBottomNavigation:
                        startActivity(new Intent(OrderStatus.this, ClientNotificationActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                }
                return false;
            }
        });


    }
}
