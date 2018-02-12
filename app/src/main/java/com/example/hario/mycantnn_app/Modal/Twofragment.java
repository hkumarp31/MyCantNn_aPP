package com.example.hario.mycantnn_app.Modal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Twofragment extends Fragment {
    public ArrayList<RecyclerInfo> arrayList = new ArrayList<>();
    private View view;
    private CartActivity cartActivity;
    private RecyclerView recyclerView;
    private RecyclerAdapterr recyclerAdapterr;
    private LinearLayoutManager linearLayoutManager;
    private TextView textView, ItemName, ItemRate, ItemQuantity, ItemTotalPrice;
    private Button buttonAddtoCart, buttonGotoCart;
    private String itemName, itemTotalPrice;
    private RecyclerInfo recyclerInfo;
    private TabLayout tabLayout;
    private Context context;
    private String title;
    private DatabaseReference databaseReference;

    public Twofragment() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclermainlayout, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");

        recyclerView = view.findViewById(R.id.recyclerID);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference.child("Cakes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnap : dataSnapshot.getChildren()) {

                    RecyclerInfo item = postsnap.getValue(RecyclerInfo.class);
                    arrayList.add(item);
                    Log.e(TAG, "onDataChange: " + item.getCount());
                    Log.e(TAG, "onDataChange: " + item.getData());
                    Log.e(TAG, "onDataChange: " + item.getCost());
                    Log.e(TAG, "onDataChange: " + item.getTotalCost());
                    Log.e(TAG, "onDataChange: " + item.getImage());
                }
                recyclerAdapterr = new RecyclerAdapterr(arrayList, textView);
                recyclerView.setAdapter(recyclerAdapterr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // arrayList=null;
        for (int i = 0; i < client.mailAddedItems.size(); i++) {
            Log.e(TAG, "onCreateView: " + client.mailAddedItems.get(i).getCount());
            for (int j = 0; j < arrayList.size(); j++) {
                if (client.mailAddedItems.get(i).getData().equals(arrayList.get(j).getData())) {
                    Log.e(TAG, "onCreateView: " + "Success");
                    arrayList.set(j, client.mailAddedItems.get(i));
                }

            }
        }
        ItemName = view.findViewById(R.id.SListName_textView);
        ItemRate = view.findViewById(R.id.SnaksRateCost_textView);
        ItemQuantity = view.findViewById(R.id.Snaks_Quantity_textView);
        ItemTotalPrice = view.findViewById(R.id.S_TotalPrice_textView);

        buttonAddtoCart = view.findViewById(R.id.AddToCartID);
        buttonGotoCart = view.findViewById(R.id.GoToCartID);
        buttonGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.GoToCartID:
                        // RecyclerInfo recyclerInfo = new RecyclerInfo();
                        itemName = ItemName.getText().toString();
                        itemTotalPrice = ItemTotalPrice.getText().toString();
                        Intent intent = new Intent(getActivity(), CartActivity.class);

                        //  Bundle bundle = new Bundle();
                        //  bundle.putParcelableArrayList("Name",arrayList);
                        //  intent.putExtras(bundle);
                        //intent.putExtra("TotalP",itemTotalPrice);
                        startActivity(intent);//Edited here
                        break;
                }
            }
        });
        buttonAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getCount() > 0) {
                        if (client.mailAddedItems.contains(arrayList.get(i))) {
                            int index = client.mailAddedItems.indexOf(arrayList.get(i));
                            client.mailAddedItems.set(index, arrayList.get(i));
                        } else
                            client.mailAddedItems.add(arrayList.get(i));
                    }
                }
                Toast.makeText(getContext(), "Items Added to Cart Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView = view.findViewById(R.id.recyclerID);
        //if (arrayList.size()==0)

        recyclerAdapterr = new RecyclerAdapterr(arrayList, textView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapterr);
        return view;
    }
}
