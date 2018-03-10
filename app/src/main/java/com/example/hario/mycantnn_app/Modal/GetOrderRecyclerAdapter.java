package com.example.hario.mycantnn_app.Modal;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.R;

import java.util.ArrayList;

public class GetOrderRecyclerAdapter extends RecyclerView.Adapter<GetOrderRecyclerAdapter.ViewHolder> {

    ArrayList<getOrderItemClass> arrayList;

    public GetOrderRecyclerAdapter(ArrayList<getOrderItemClass> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_customer_order_main_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.imageView.getContext()).load(arrayList.get(position).getImage()).into(holder.imageView);
        holder.OrderName.setText(arrayList.get(position).getData());
        holder.OrderQuantity.setText("" +arrayList.get(position).getCount());
        holder.OrderPrice.setText(""+arrayList.get(position).getPrice());
        holder.OrderTotal.setText(""+arrayList.get(position).getTotalCost());
        holder.OrderID.setText(arrayList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView OrderName, OrderQuantity, OrderPrice, OrderTotal, OrderID;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            OrderName = itemView.findViewById(R.id.getProductName_TV);
            OrderQuantity = itemView.findViewById(R.id.getProductQuantity_TV);
            OrderPrice = itemView.findViewById(R.id.getProductSinglePrice_TV);
            OrderTotal = itemView.findViewById(R.id.getProductTotalPrice_TV);
            OrderID = itemView.findViewById(R.id.getProductOrderId_TV);
            imageView=itemView.findViewById(R.id.CustomerOrderProductimage);

        }
    }
}
