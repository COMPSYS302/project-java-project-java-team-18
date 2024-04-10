package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sneakrapp.ProductAdaptor;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private class ViewHolder {
        ImageView image;

        TextView name, description;

        public ViewHolder() {
            name = findViewById(R.id.product_name);
            image = findViewById(R.id.product_image);
            description = findViewById(R.id.product_description);
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

         vh.name.setText(product.getName());
        // Get the resource ID for the product icon
        int imageResourceId = getResources().getIdentifier(product.getIcon(), "drawable", getPackageName());

        // Set the image resource for the ImageView
        ImageView productImage = findViewById(R.id.product_image);
        vh.image.setImageResource(imageResourceId);

        vh.description.setText(product.getDescription());

    }
}