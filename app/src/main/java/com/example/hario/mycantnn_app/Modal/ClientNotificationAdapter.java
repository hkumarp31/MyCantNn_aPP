package com.example.hario.mycantnn_app.Modal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hario.mycantnn_app.R;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 2/13/2018.
 */

public class ClientNotificationAdapter extends RecyclerView.Adapter<ClientNotificationAdapter.ViewHolder>{
    ArrayList<ClientNotificationInfo> arrayList;

    public ClientNotificationAdapter(ArrayList<ClientNotificationInfo> arrayList) {
        this.arrayList = arrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientnotificationmain_layout,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ClientNotiImg.setImageResource(arrayList.get(position).getClientNotiImage());
        holder.ClientNotiTtl.setText(arrayList.get(position).getClientNotiTitle());
        holder.ClientNotiStts.setText(arrayList.get(position).getClientNotiStatus());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ClientNotiImg;
        TextView ClientNotiTtl,ClientNotiStts;

        public ViewHolder(View itemView) {
            super(itemView);
            ClientNotiImg=itemView.findViewById(R.id.ClientNotification_Image);
            ClientNotiTtl = itemView.findViewById(R.id.ClientNotification_Title);
            ClientNotiStts = itemView.findViewById(R.id.ClientNotification_StatusReport);

        }
    }
}
