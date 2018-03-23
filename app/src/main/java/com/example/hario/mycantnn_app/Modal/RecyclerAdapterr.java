package com.example.hario.mycantnn_app.Modal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hario.mycantnn_app.R;
import com.example.hario.mycantnn_app.imageEnlargeView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerAdapterr extends RecyclerView.Adapter<RecyclerAdapterr.ViewHolder> {
    Context context;

    ArrayList<RecyclerInfo> arrayList;
    TextView tv;

    public RecyclerAdapterr(ArrayList<RecyclerInfo> arrayList, TextView textView) {
        this.arrayList = arrayList;
        tv = textView;
    }

    @Override
    public RecyclerAdapterr.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(holder.ItemImage.getContext()).load(arrayList.get(position).getImage()).into(holder.ItemImage);
        // holder.ItemImage.setImageResource(arrayList.get(position).getImage());
        holder.ItemName.setText(arrayList.get(position).getData());
        holder.ItemCount.setText("" + arrayList.get(position).getCount());
        holder.ItemCost.setText("" + arrayList.get(position).getCost());
        holder.ItemTotalPrice.setText("" + arrayList.get(position).getTotalCost());
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Count = arrayList.get(position).getCount();

                if (Count > 0)
                    Count -= 1;
                holder.ItemCount.setText("" + arrayList);
                arrayList.get(position).setCount(Count);
                int totalprice = Count * arrayList.get(position).getCost();
                holder.ItemTotalPrice.setText("" + arrayList);
                arrayList.get(position).setTotalCost(totalprice);
                notifyDataSetChanged();

            }
        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Count = arrayList.get(position).getCount();
                if (Count < 10)
                    Count += 1;
                holder.ItemCount.setText("" + arrayList);
                arrayList.get(position).setCount(Count);
                holder.ItemTotalPrice.setText("" + arrayList);
                arrayList.get(position).setTotalCost(Count * arrayList.get(position).getCost());
                notifyDataSetChanged();
            }
        });
        holder.ItemImage.setOnClickListener(new View.OnClickListener() {
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
        CardView cardView;
        TextView ItemName, ItemCount, ItemCost, ItemTotalPrice;
        Button button1, button2;
        ImageView ItemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewID);
            ItemImage = itemView.findViewById(R.id.Item_ImageView_ID);
            ItemName = itemView.findViewById(R.id.SnaksListName_textView);
            ItemCount = itemView.findViewById(R.id.SnaksList_Quantity_textView);
            ItemCost = itemView.findViewById(R.id.SnaksListCost_textView);
            ItemTotalPrice = itemView.findViewById(R.id.Snaks_TotalPrice_textView);
            button1 = itemView.findViewById(R.id.SnaksList_IncQuantity_Button);
            button2 = itemView.findViewById(R.id.SnaksList_DecQuantity_Button);

        }
    }

}
