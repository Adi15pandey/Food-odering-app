package com.example.android.foododeringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.foododeringapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    ImageView increment;
    ImageView decrement;
    TextView display;
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        increment = findViewById(R.id.add);
        decrement = findViewById(R.id.subtract);
        display = findViewById(R.id.quantity);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c++;
                display.setText("" + c);
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c <= 0) c = 0;
                else
                    c--;
                display.setText("" + c);
            }
        });
        final DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", price));
            binding.namelbl.setText(name);
            binding.detailDesciption.setText(description);

            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Data success", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Intent intent = getIntent();
            String id = getIntent().getStringExtra("id");
            Cursor cursor = helper.getOrderById(Integer.parseInt(id));
            int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", cursor.getInt(3)));
            binding.namelbl.setText(cursor.getString(6));
            binding.detailDesciption.setText(cursor.getString(5));
            binding.namebox.setText(cursor.getString(1));
            binding.phonebox.setText(cursor.getString(2));
            binding.insertButton.setText("Update now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOrder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.pricelbl.getText().toString()),
                            image,
                            binding.detailDesciption.getText().toString(),
                            binding.namelbl.getText().toString(),
                            1,
                            Integer.parseInt(id)
                    );
                    if (isUpdated){
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
