package com.example.android.foododeringapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.foododeringapp.DetailActivity;
import com.example.android.foododeringapp.Models.MainModel;
import com.example.android.foododeringapp.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder>  {

    ArrayList<MainModel>list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_mainfood,parent,false);
        return new viewholder (view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final MainModel model=list.get(position);
        holder.foodimage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "touched", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("desc",model.getDescription());

                intent.putExtra("name",model.getName());
                intent.putExtra("type",1);

                context.startActivity(intent);
            }
        });
;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder {
        ImageView foodimage;
        TextView mainName,price,description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            foodimage=itemView.findViewById(R.id.imageView);
            price=itemView.findViewById(R.id.orderPrice);
            mainName=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);


        }
    }
}
