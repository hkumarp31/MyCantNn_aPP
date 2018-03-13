package com.example.hario.mycantnn_app.Modal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    TextView text;
    Context context;
    String [] values = {" PROCESS "," COMPLETED "};
    AlertDialog alertDialog1;

    public GetOrderRecyclerAdapter(ArrayList<getOrderItemClass> arrayList,TextView textView, Context context) {
        this.arrayList = arrayList;
        this.text=textView;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_customer_order_main_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(holder.imageView.getContext()).load(arrayList.get(position).getImage()).into(holder.imageView);
        holder.OrderName.setText(arrayList.get(position).getData());
        holder.OrderQuantity.setText("" +arrayList.get(position).getCount());
        holder.OrderPrice.setText(""+arrayList.get(position).getPrice());
        holder.OrderTotal.setText(""+arrayList.get(position).getTotalCost());
        holder.OrderID.setText(arrayList.get(position).getId());
        holder.OrderTakeAction.setText(arrayList.get(position).getStatus());
        holder.OrderTakeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Select Your Choice");
                //builder.setCancelable(true);

                builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {

                        switch(item)
                        {
                            case 0:
                                holder.OrderTakeAction.setText(""+arrayList);
                                arrayList.get(position).setStatus(values[0]);
                                notifyDataSetChanged();
                                alertDialog1.dismiss();
                                break;
                            case 1:
                                holder.OrderTakeAction.setText("" + arrayList);
                                arrayList.get(position).setStatus(values[1]);
                                notifyDataSetChanged();
                                alertDialog1.dismiss();
                                break;

                        }
                        alertDialog1.dismiss();
                    }
                });

                alertDialog1 = builder.create();
                alertDialog1.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView OrderName, OrderQuantity, OrderPrice, OrderTotal, OrderID, OrderTakeAction;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            OrderName = itemView.findViewById(R.id.getProductName_TV);
            OrderQuantity = itemView.findViewById(R.id.getProductQuantity_TV);
            OrderPrice = itemView.findViewById(R.id.getProductSinglePrice_TV);
            OrderTotal = itemView.findViewById(R.id.getProductTotalPrice_TV);
            OrderID = itemView.findViewById(R.id.getProductOrderId_TV);
            imageView=itemView.findViewById(R.id.CustomerOrderProductimage);
            OrderTakeAction=itemView.findViewById(R.id.getProductTAKEACTION_ID);

        }
    }
}
