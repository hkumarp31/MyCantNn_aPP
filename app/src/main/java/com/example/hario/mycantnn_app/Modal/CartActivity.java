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
import android.widget.Button;
import android.widget.TextView;

import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.example.hario.mycantnn_app.profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private CartInfo cartInfo;
    private ArrayList<RecyclerInfo> arrayList;
    private ArrayList<CartInfo> selectedItem;
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
                selectedItem.add(new CartInfo(client.mailAddedItems.get(i).getImage(), client.mailAddedItems.get(i).getData(), client.mailAddedItems.get(i).getTotalCost()));
            }
            itemTotalPrice += client.mailAddedItems.get(i).getTotalCost();
        }

        YourTotalPriceArray.setText("Rs. " + itemTotalPrice);
        recyclerView = findViewById(R.id.CartRecyclerLayoutID);
        cartRecyclerAdapter = new CartRecyclerAdapter(selectedItem, textView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartRecyclerAdapter);


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

                        break;

                    case R.id.navigation_OrderStatus12:

                        Intent intent1 = new Intent(CartActivity.this, OrderStatus.class);
                        startActivity(intent1);
                        break;

                    case R.id.navigation_cart:

                        break;

                    case R.id.navigation_profile:
                        Intent intent4 = new Intent(CartActivity.this, profile.class);
                        startActivity(intent4);
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
    public void addtofirebase(ArrayList<CartInfo> selecteditems) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference();

        for (int i = 0; i < selecteditems.size(); i++) {
            CartInfo c = selecteditems.get(i);
            String s = i + "";
            mref.child("hemantobjects").child(s).setValue(c);
        }


    }


}
