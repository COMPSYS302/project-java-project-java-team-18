package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sneakrapp.R;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    private class ViewHolder {
        ImageView image;

        TextView name, description;
        Spinner size, quantity;
        Button addToCartButton;

        public ViewHolder() {
            name = findViewById(R.id.product_name);
            image = findViewById(R.id.product_image);
            description = findViewById(R.id.product_description);
            addToCartButton = findViewById(R.id.addToCart);
            size = findViewById(R.id.size_spinner);
            quantity = findViewById(R.id.quantity_spinner);

        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        vh = new ViewHolder();

        String productJson = getIntent().getStringExtra("product_details");
        Gson gson = new Gson();
        Product product = gson.fromJson(productJson, Product.class);

        vh.name.setText(product.getName());
        // Get the resource ID for the product icon
        int imageResourceId = getResources().getIdentifier(product.getIcon(), "drawable", getPackageName());

        // Set the image resource for the ImageView
        ImageView productImage = findViewById(R.id.product_image);
        vh.image.setImageResource(imageResourceId);

        vh.description.setText(product.getDescription());

        vh.size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                product.setSize(selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        vh.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedQuantity = Integer.parseInt(parent.getItemAtPosition(position).toString());
                product.setQuantity(selectedQuantity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        vh.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                intent.putExtra("selectedSize", product.getSize());
                intent.putExtra("selectedQuantity", product.getQuantity());
                startActivity(intent);
            }
        });


    }
}