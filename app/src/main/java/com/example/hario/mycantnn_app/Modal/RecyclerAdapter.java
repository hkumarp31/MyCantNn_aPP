package com.example.hario.mycantnn_app.Modal;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.imageEnlargeView;

import java.util.ArrayList;

/**
 * Created by Hemant Kumar on 1/31/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<RecyclerItemInfo> arrayList;

    public RecyclerAdapter(ArrayList<RecyclerItemInfo> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hostcontentmain2, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(holder.Pimage.getContext()).load(arrayList.get(position).getImage()).into(holder.Pimage);
        holder.Ptitle.setText(arrayList.get(position).getData());
        holder.Pprice.setText("" + arrayList.get(position).getCost());
        holder.PCount.setText("" + arrayList.get(position).getCount());
        holder.Ptotal.setText("" + arrayList.get(position).getTotal());
        holder.Pimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ImageUrl = arrayList.get(position).getImage();
                Intent intent = new Intent(view.getContext(), imageEnlargeView.class);
                intent.putExtra("imageurl", ImageUrl);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Pimage;
        TextView Ptitle, Pprice, PCount, Ptotal;

        public ViewHolder(View itemView) {
            super(itemView);
            Pimage = itemView.findViewById(R.id.ProductImage);
            Ptitle = itemView.findViewById(R.id.ProductTitle);
            Pprice = itemView.findViewById(R.id.ProductPrice);
            PCount = itemView.findViewById(R.id.ProductCount);
            Ptotal = itemView.findViewById(R.id.ProductTotalPrice);
        }
    }
}
