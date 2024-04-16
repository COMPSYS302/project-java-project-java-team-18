package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sneakrapp.R;
import com.example.sneakrapp.WishlistManager;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity"; // Define a tag for your logs

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

        String productJson = getIntent().getStringExtra("product details");
        Gson gson = new Gson();
        Product product = gson.fromJson(productJson, Product.class);

        if (product != null) {
            vh.name.setText(product.getName());
            int imageResourceId = getResources().getIdentifier(product.getIcon(), "drawable", getPackageName());
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
                    WishlistManager.getInstance().addProduct(product);
                    Intent intent = new Intent(getBaseContext(), CartActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e(TAG, "Received null product data");
            finish(); // Close this activity if no product data is available
        }
    }
}
