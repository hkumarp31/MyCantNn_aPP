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

        // if (!FirebaseApp.getApps(this).isEmpty()) {
        //      FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //  }
        rv = findViewById(R.id.recyid);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        aL = new ArrayList<>();

        ArrayList<CartInfo> ci = CartActivity.selectedItem;

        for (int i = 0; i < ci.size(); i++) {
            CartInfo h = ci.get(i);

            String s = generateId();
            statusinfo si = new statusinfo("" + h.getImage(), "" + h.getName(), "" + s, h.getTotal(), client.mailAddedItems.get(i).getCount(), h.getPrice());

            aL.add(si);


        }

        addtofirebase(aL);

        // new changes

            /*      final DatabaseReference Mred = FirebaseDatabase.getInstance().getReference().child("hemantobjects");

            aL = new ArrayList<>();

            Mred.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int i=0;
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                        CartInfo h = singleSnapshot.getValue(CartInfo.class);
                        DatabaseReference Mredchild = FirebaseDatabase.getInstance().getReference().child("Orderids");
                        String s = generateId();
                        Mred.child(i+"").setValue(s);
                        statusinfo si = new statusinfo(h.getImage(),h.getName(),s);
                        aL.add(si) ;
                        i++;

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Log.e("TAG", "onCancelled", databaseError.toException());


                }
            }); */



        /*
            data receiving end
         */

        myadapter = new statusadapter(aL, this);
        rv.setAdapter(myadapter);


    }



    public void addtofirebase(ArrayList<statusinfo> selecteditems) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mref = database.getReference().child("Customer");

        for (int i = 0; i < selecteditems.size(); i++) {
            statusinfo c = selecteditems.get(i);
            mref.child("Orders").push().setValue(c);
        }


    }



    public String generateId() {
        String id = UUID.randomUUID().toString();
        String s = id.substring(0, 8);

        return s;

    }



}
