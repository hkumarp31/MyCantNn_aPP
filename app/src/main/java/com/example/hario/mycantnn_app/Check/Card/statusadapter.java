package com.example.hario.mycantnn_app.Check.Card;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statuslayout, parent, false);
        final ViewHolder rootview = new ViewHolder(view);
        return rootview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemimg.getContext()).load(arrayList.get(position).getImage()).into(holder.itemimg);
        // holder.itemimg.setImageResource(arrayList.get(position).getImage());
        holder.itemname.setText(arrayList.get(position).getName());
        holder.itemid.setText(arrayList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemimg;
        TextView itemname;
        EditText itemid;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimg = itemView.findViewById(R.id.myimg);
            itemname = itemView.findViewById(R.id.mytxt);
            itemid = itemView.findViewById(R.id.edtxt);


        }
    }


}
