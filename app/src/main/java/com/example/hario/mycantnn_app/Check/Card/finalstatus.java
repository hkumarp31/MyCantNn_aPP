package com.example.hario.mycantnn_app.Check.Card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hario.mycantnn_app.Modal.CartInfo;
import com.example.hario.mycantnn_app.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class finalstatus extends AppCompatActivity {

    private RecyclerView rv;
    private statusadapter myadapter;
    private ArrayList<statusinfo> aL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!FirebaseApp.getApps(this).isEmpty())
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalstatus);
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        rv = findViewById(R.id.recyid);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        final DatabaseReference Mred = FirebaseDatabase.getInstance().getReference().child("hemantobjects");

        aL = new ArrayList<>();

        Mred.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    CartInfo h = singleSnapshot.getValue(CartInfo.class);
                    DatabaseReference Mredchild = FirebaseDatabase.getInstance().getReference().child("Orderids");
                    String s = generateId();
                    Mred.child(i + "").setValue(s);
                    statusinfo si = new statusinfo(h.getImage(), h.getName(), s);
                    aL.add(si);
                    i++;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e("TAG", "onCancelled", databaseError.toException());


            }
        });



        /*
            data receiving end
         */

        myadapter = new statusadapter(aL, this);
        rv.setAdapter(myadapter);


    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        String s = id.substring(0, 8);

        return s;

    }


}
