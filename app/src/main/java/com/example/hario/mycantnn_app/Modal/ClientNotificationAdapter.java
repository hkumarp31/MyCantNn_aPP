package com.example.hario.mycantnn_app.Modal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 2/13/2018.
 */

public class ClientNotificationAdapter extends RecyclerView.Adapter<ClientNotificationAdapter.ViewHolder>{
    ArrayList<getOrderItemClass> arrayList;
    private DatabaseReference databaseReference;
    private Context context;

    public ClientNotificationAdapter(ArrayList<getOrderItemClass> arrayList) {
        this.arrayList = arrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientnotificationmain_layout,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(holder.ClientNotiImg.getContext()).load(arrayList.get(position).getImage()).into(holder.ClientNotiImg);
        holder.ClientNotiTtl.setText(arrayList.get(position).getData());
        holder.ClientNotiStts.setText(arrayList.get(position).getStatus());
        holder.ClientDateAndTime.setText(arrayList.get(position).getDatetime());
        if(arrayList.get(position).getStatus().equals(" PROCESS "))
            holder.ClientNotiStts.setTextColor(Color.RED);
        else
            holder.ClientNotiStts.setTextColor(Color.GREEN);

        holder.ClientNotiRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("ClientUser");
                databaseReference.child("OrderStatusNotify").child(FirebaseAuth.getInstance().getUid()).child(arrayList.get(position).getNotifykey()).removeValue();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ClientNotiImg,ClientNotiRemove;
        TextView ClientNotiTtl,ClientNotiStts,ClientDateAndTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ClientNotiRemove=itemView.findViewById(R.id.ClientNotification_RemoveButton);
            ClientNotiImg=itemView.findViewById(R.id.ClientNotification_Image);
            ClientNotiTtl = itemView.findViewById(R.id.ClientNotification_Title);
            ClientNotiStts = itemView.findViewById(R.id.ClientNotification_StatusReport);
            ClientDateAndTime=itemView.findViewById(R.id.ClientNotification_DateAndTime);

        }
    }
}
