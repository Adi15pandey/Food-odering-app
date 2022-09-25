package com.example.android.foododeringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.android.foododeringapp.Adapters.MainAdapter;
import com.example.android.foododeringapp.Models.MainModel;
import com.example.android.foododeringapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list=new ArrayList<>();
        list.add(new MainModel(R.drawable.pizza,"pizza","5","pizza with chilli sauce"));
        list.add(new MainModel(R.drawable.logo,"burger","10","burger with chicken "));
        list.add(new MainModel(R.drawable.burger,"burger meal","17","burger meal "));
        list.add(new MainModel(R.drawable.friedchicken,"KFC chicken","18"," fried chicken "));
        list.add(new MainModel(R.drawable.bibimbap,"healthy food","19"," carrots with some veggies "));

        list.add(new MainModel(R.drawable.pizza,"pizza","5","pizza with chilli sauce"));
        list.add(new MainModel(R.drawable.logo,"burger","10","burger with chicken "));
        list.add(new MainModel(R.drawable.burger,"burger meal","17","burger meal "));
        list.add(new MainModel(R.drawable.friedchicken,"KFC chicken","18"," fried chicken "));
        list.add(new MainModel(R.drawable.bibimbap,"healthy food","19"," carrots with some veggies "));
        MainAdapter adapter= new MainAdapter(list,this);
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}