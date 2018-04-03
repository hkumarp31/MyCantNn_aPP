package com.example.hario.mycantnn_app.Check.Card;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.R;

import java.util.ArrayList;

/**
 * Created by hario on 1/30/2018.
 */

public class statusadapter extends RecyclerView.Adapter<statusadapter.ViewHolder> {
    private ArrayList<statusinfo> arrayList;
    private Context context;

    public statusadapter(ArrayList<statusinfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public statusadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_layout, parent, false);
        final ViewHolder rootview = new ViewHolder(view);
        return rootview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(holder.itemimg.getContext()).load(arrayList.get(position).getImage()).into(holder.itemimg);
        holder.itemname.setText(arrayList.get(position).getData());
        holder.itemid.setText("Order Id : "+arrayList.get(position).getId());
        holder.itemprice.setText("Total Cost Rs. "+arrayList.get(position).getTotalCost());
        holder.itemquantity.setText("Qty : "+arrayList.get(position).getCount());
        holder.itemCount.setText(""+arrayList.get(position).getPrice());
        holder.itemDateAndTime.setText(arrayList.get(position).getDateandtime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemimg;
        TextView itemname,itemid,itemprice, itemquantity,itemCount,itemDateAndTime;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimg = itemView.findViewById(R.id.myimg);
            itemname = itemView.findViewById(R.id.utxt);
            itemid = itemView.findViewById(R.id.mtxt);
            itemprice = itemView.findViewById(R.id.btxtr);
            itemquantity = itemView.findViewById(R.id.btxtl);
            itemCount = itemView.findViewById(R.id.Count);
            itemDateAndTime=itemView.findViewById(R.id.orderDate);

        }
    }


}
