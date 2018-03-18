package com.example.hario.mycantnn_app.Check.Card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.hario.mycantnn_app.Modal.CartActivity;
import com.example.hario.mycantnn_app.Modal.CartInfo;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class finalstatus extends AppCompatActivity {

    private RecyclerView rv;
    private statusadapter myadapter;
    private ArrayList<statusinfo> aL;
    private Button BACK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalstatus);

        BACK=(Button)findViewById(R.id.BACKtoHOME);
        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(finalstatus.this,client.class));
                finish();
            }
        });
        rv = findViewById(R.id.recyid);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference().child("HostUser");

        aL = new ArrayList<>();

        ArrayList<CartInfo> ci = CartActivity.selectedItem;
        List<String> key = new ArrayList<>();
        for (int i = 0; i < ci.size(); i++) {
            CartInfo h = ci.get(i);
            key.add(mref.push().getKey());
            String s = generateId();
            String status="TAKE ACTION";

            statusinfo si = new statusinfo("" + h.getImage(), "" + h.getName(), "" + s, h.getTotal(),
                    client.mailAddedItems.get(i).getCount(), h.getPrice(),
                    status,FirebaseAuth.getInstance().getCurrentUser().getUid(),key.get(i));

            aL.add(si);
        }

        addtofirebase(aL,key);
        myadapter = new statusadapter(aL, this);
        rv.setAdapter(myadapter);

    }
    public void addtofirebase(ArrayList<statusinfo> selecteditems, List<String> key) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference().child("HostUser");

        for (int i = 0; i < selecteditems.size(); i++) {
            statusinfo c = selecteditems.get(i);
            mref.child("Orders").child(key.get(i)).setValue(c);
        }

    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        String s = id.substring(0, 8);

        return s;

    }



}
