package com.example.hario.mycantnn_app.Check.Card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hario.mycantnn_app.Modal.CartActivity;
import com.example.hario.mycantnn_app.Modal.CartInfo;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class finalstatus extends AppCompatActivity {

    private RecyclerView rv;
    private statusadapter myadapter;
    private ArrayList<statusinfo> aL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalstatus);

        rv = findViewById(R.id.recyid);
        rv.setHasFixedSize(true);

        aL = new ArrayList<>();

        for (int i = 0; i < CartActivity.selectedItem.size(); i++) {
             String s = generateId();
             aL.add(new statusinfo(CartActivity.selectedItem.get(i).getImage(),
                     CartActivity.selectedItem.get(i).getName(),
                     s, CartActivity.selectedItem.get(i).getTotal(),
                     CartActivity.selectedItem.get(i).getCount(),
                     CartActivity.selectedItem.get(i).getPrice()));
        }

        addtofirebase(aL);

        myadapter = new statusadapter(aL, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myadapter);

    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        String s = id.substring(0, 8);
        return s;

    }

    public void addtofirebase(ArrayList<statusinfo> selecteditems) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference();

        for (int i = 0; i < selecteditems.size(); i++) {
            statusinfo c = selecteditems.get(i);
            mref.child("Customer").child("Orders").push().setValue(c);
        }

    }

}
