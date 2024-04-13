package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sneakrapp.R;

public class CartActivity extends AppCompatActivity {

    private class ViewHolder {
        Spinner sizeSpinner, quantitySpinner;
        TextView wishlist;

        public ViewHolder() {

             sizeSpinner = findViewById(R.id.selected_size_spinner);
             quantitySpinner = findViewById(R.id.selected_quantity_spinner);
             wishlist = findViewById(R.id.moveToWishlist);

        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        vh = new ViewHolder();


        Intent intent = getIntent();
        String selectedSize = intent.getStringExtra("selectedSize");
        int selectedQuantity = intent.getIntExtra("selectedQuantity", 1); // Default to 1 if no value is passed



        // Set up the size spinner
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.sizes_array, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vh.sizeSpinner.setAdapter(sizeAdapter);
        if (selectedSize != null) {
            int sizePosition = sizeAdapter.getPosition(selectedSize);
            vh.sizeSpinner.setSelection(sizePosition);
        }

        // Set up the quantity spinner
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this,
                R.array.quantities_array, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vh.quantitySpinner.setAdapter(quantityAdapter);
        int quantityPosition = quantityAdapter.getPosition(String.valueOf(selectedQuantity));
        vh.quantitySpinner.setSelection(quantityPosition);

        vh.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}