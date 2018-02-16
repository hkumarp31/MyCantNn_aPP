package com.example.hario.mycantnn_app.Modal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hario.mycantnn_app.MainActivity;
import com.example.hario.mycantnn_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HostActivityMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String Choice = "";
    private Spinner spinner, spinnr;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<RecyclerItemInfo> arrayList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private Context context;
    private Button ClickHere;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostactivitymain2);

        spinner = findViewById(R.id.CanteenItem_Spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CanteenItem_arrays,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        ClickHere = findViewById(R.id.Click);
firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");
        recyclerView = findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
     /*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postsnap : dataSnapshot.getChildren()) {
                    RecyclerItemInfo item = postsnap.getValue(RecyclerItemInfo.class);
                    arrayList.add(item);
                    Log.e(TAG, "onDataChange: "+item.getData() );
                    Log.e(TAG, "onDataChange: "+item.getImage());
                    Log.e(TAG, "onDataChange: "+item.getCost() );
                    Log.e(TAG, "onDataChange: "+item.getTotal() );
                    Log.e(TAG, "onDataChange: "+item.getCount() );
                }
                recyclerAdapter = new RecyclerAdapter(arrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HostActivityMain.this,"Database Error",Toast.LENGTH_SHORT).show();

            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.AddItemButton_main_menu) {
            startActivity(new Intent(this, AddItemActivity.class));
        }

        if(id==R.id.logout)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Choice = adapterView.getItemAtPosition(i).toString();
        Log.e(TAG, "onItemSelected: " + Choice);
        updateUI(Choice);
    }

    private void updateUI(String choice) {

        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer");

        Log.e(TAG, "updateUI: " + choice);
        databaseReference.child(choice).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnap : dataSnapshot.getChildren()) {
                    RecyclerItemInfo item = postsnap.getValue(RecyclerItemInfo.class);
                    arrayList.add(item);

                    Log.e(TAG, "onDataChange: " + item.getData());
                    Log.e(TAG, "onDataChange: " + item.getImage());
                    Log.e(TAG, "onDataChange: " + item.getCost());
                    Log.e(TAG, "onDataChange: " + item.getTotal());
                    Log.e(TAG, "onDataChange: " + item.getCount());
                }
                recyclerAdapter = new RecyclerAdapter(arrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HostActivityMain.this, "LogOut Successful", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void Clicking(View view) {
        startActivity(new Intent(this, GetOrderFromCustomerActivity.class));

    }
}
