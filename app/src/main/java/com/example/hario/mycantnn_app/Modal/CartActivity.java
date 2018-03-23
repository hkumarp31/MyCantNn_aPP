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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.Check.Card.payment;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.example.hario.mycantnn_app.profile;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {

    public static ArrayList<CartInfo> selectedItem;
    private RecyclerView recyclerView;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private CartInfo cartInfo;
    private ArrayList<RecyclerInfo> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private TextView textView, PriceIs, ReadyGoCart, YourOrderText, YourTotalPriceArray;
    private Button SubmitOrdr;
    private String itemName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_recycler_layout);
        // toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.CartToolbar);
        //  setSupportActionBar(toolbar);
        selectedItem = new ArrayList<>();
        SubmitOrdr = findViewById(R.id.SubmitOrder);
        PriceIs = findViewById(R.id.TotalPriceisID);
        ReadyGoCart = findViewById(R.id.YourCartIsReadyToGoID);
        YourOrderText = findViewById(R.id.YourOrderPriceID);
        YourTotalPriceArray = findViewById(R.id.TotalOrderPriceID);
        //   YourTotalPriceArray.setText(""+TotalCost());
        //  Bundle bundle = this.getIntent().getExtras();
        //  arrayList = bundle.getParcelableArrayList("Name");
        int itemTotalPrice = 0;
        for (int i = 0; i < client.mailAddedItems.size(); i++) {
            if (client.mailAddedItems.get(i).getCount() > 0) {
                selectedItem.add(new CartInfo(client.mailAddedItems.get(i).getImage(),
                        client.mailAddedItems.get(i).getData(),
                        client.mailAddedItems.get(i).getCount(),
                        client.mailAddedItems.get(i).getCost(),
                        client.mailAddedItems.get(i).getTotalCost()));
            }
            itemTotalPrice += client.mailAddedItems.get(i).getTotalCost();
        }

        YourTotalPriceArray.setText("Rs. " + itemTotalPrice);
        recyclerView = findViewById(R.id.CartRecyclerLayoutID);
        cartRecyclerAdapter = new CartRecyclerAdapter(selectedItem, textView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartRecyclerAdapter);

        final int finalItemTotalPrice = itemTotalPrice;
        SubmitOrdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalItemTotalPrice ==0)
                {
                    Toast.makeText(CartActivity.this,"Add Item to Your Cart First",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Intent k = new Intent(CartActivity.this, payment.class);

                        startActivity(k);
                        finish();
                    }
            }
        });


        BottomNavigationView navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shop:

                        Intent intent2 = new Intent(CartActivity.this, client.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_OrderStatus12:

                        Intent intent1 = new Intent(CartActivity.this, CustomerMyOrderActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.navigation_cart:

                        break;

                    case R.id.navigation_profile:
                        Intent intent4 = new Intent(CartActivity.this, profile.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.ClientNotificationBottomNavigation:
                        startActivity(new Intent(CartActivity.this, ClientNotificationActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
    /*   private ArrayList<CartInfo> Cartdetails(){
           ArrayList<CartInfo> arrayList = new ArrayList<>();

           CartInfo cart = new CartInfo(android.R.drawable.btn_star_big_off,itemName,0);
           arrayList.add(cart);
           arrayList.add(new CartInfo(android.R.drawable.btn_dropdown,"bufallo",0));
           return arrayList;

       }
       */

}
