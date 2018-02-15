package com.example.hario.mycantnn_app.Modal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hario.mycantnn_app.R;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 2/13/2018.
 */

public class ClientNotificationActivity extends Activity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ClientNotificationAdapter clientNotificationAdapter;
    private ArrayList<ClientNotificationInfo> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientnotification_layout);
        recyclerView= (RecyclerView) findViewById(R.id.ClientNotification_RecyclerView);

        arrayList = fillProjectDetail();
        clientNotificationAdapter = new ClientNotificationAdapter(arrayList);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clientNotificationAdapter);
    }
    private ArrayList<ClientNotificationInfo> fillProjectDetail(){
        ArrayList<ClientNotificationInfo> pappuinfoArrayList = new ArrayList<>();
        ClientNotificationInfo p=new ClientNotificationInfo(android.R.drawable.ic_menu_report_image,"Pappu","Clear");
        pappuinfoArrayList.add(p);
        pappuinfoArrayList.add(new ClientNotificationInfo(android.R.drawable.arrow_down_float,"ghonchu","Not Clear"));

        return pappuinfoArrayList;
    }
}
