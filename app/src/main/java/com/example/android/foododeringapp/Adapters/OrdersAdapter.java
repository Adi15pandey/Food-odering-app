package com.example.android.foododeringapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.foododeringapp.DBHelper;
import com.example.android.foododeringapp.DetailActivity;
import com.example.android.foododeringapp.Models.OrdersModel;
import com.example.android.foododeringapp.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    ArrayList<OrdersModel>list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrdersModel model= list.get(position);
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());

        holder.orderView.setImageResource(model.getOrderImage());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id",model.getOrderNumber());
                intent.putExtra("type",2);

                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper helper = new DBHelper(context);
                                if (helper.deleteOrder(model.getOrderNumber()) > 0) {
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }

                        }).show();
                return  false;
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView orderView;
        TextView soldItemName,orderNumber,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderView=itemView.findViewById(R.id.orderimage);
            soldItemName=itemView.findViewById(R.id.orderItemName);
            orderNumber=itemView.findViewById(R.id.orderNumber);
             price=itemView.findViewById(R.id.orderPriceo);
        }
    }
}
