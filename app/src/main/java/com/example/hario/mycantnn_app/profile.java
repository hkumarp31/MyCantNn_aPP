package com.example.hario.mycantnn_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hario.mycantnn_app.Modal.CartActivity;
import com.example.hario.mycantnn_app.Modal.OrderStatus;

public class profile extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        Intent intent = new Intent(profile.this, client.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_OrderStatus12:
                        Intent intent1 = new Intent(profile.this, OrderStatus.class);

                        startActivity(intent1);
                        break;

                    case R.id.navigation_cart:

                        Intent intent2 = new Intent(profile.this, CartActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_profile:
                        break;
                }
                return false;
            }
        });


        button1 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(profile.this, profile_edit_page.class));
            }
        });


    }


}
