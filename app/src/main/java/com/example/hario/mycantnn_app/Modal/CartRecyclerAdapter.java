package com.example.hario.mycantnn_app.Modal;

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
 * Created by Hemant Kumar on 1/4/2018.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private ArrayList<CartInfo> arrayList;
    private TextView tv;

    public CartRecyclerAdapter(ArrayList<CartInfo> arrayList, TextView textView) {
        this.arrayList = arrayList;
        this.tv = textView;
    }

    @Override
    public CartRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlayout, parent, false);
        final ViewHolder rootview = new ViewHolder(view);
        return rootview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemImageCart.getContext()).load(arrayList.get(position).getImage()).into(holder.itemImageCart);
        //holder.itemImageCart.setImageResource(arrayList.get(position).getImage());
        holder.itemNameCart.setText(arrayList.get(position).getName());
        holder.itemTotalCart.setText("" + arrayList.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageCart;
        TextView itemNameCart, itemTotalCart;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImageCart = itemView.findViewById(R.id.ItemImageCartID);
            itemNameCart = itemView.findViewById(R.id.ItemNameCartID);
            itemTotalCart = itemView.findViewById(R.id.ItemTotalPriceCartID);

        }
    }
}
