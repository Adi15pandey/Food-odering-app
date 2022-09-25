package com.example.android.foododeringapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.android.foododeringapp.Adapters.OrdersAdapter;
import com.example.android.foododeringapp.Models.MainModel;
import com.example.android.foododeringapp.Models.OrdersModel;
import com.example.android.foododeringapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper helper=new DBHelper(this);
        ArrayList<OrdersModel>list= helper.getOrders();

        OrdersAdapter adapter=new OrdersAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearManager=new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(linearManager);
    }
}