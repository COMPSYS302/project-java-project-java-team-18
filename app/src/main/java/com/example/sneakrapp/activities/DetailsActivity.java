package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sneakrapp.ImagePagerAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.models.Product;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private class ViewHolder {
        ImageView image;
        ViewPager2 viewPagerProductImages;
        TextView name, description;
        Spinner size, quantity;
        Button addToCartButton;
        TabLayout tabLayout;

        public ViewHolder() {
            name = findViewById(R.id.product_name);
            //image = findViewById(R.id.product_image);
            description = findViewById(R.id.product_description);
            addToCartButton = findViewById(R.id.addToCart);
            size = findViewById(R.id.size_spinner);
            quantity = findViewById(R.id.quantity_spinner);
            viewPagerProductImages = findViewById(R.id.viewPagerProductImages);
            tabLayout = findViewById(R.id.tabLayout);
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

        List<String> imageUrls = product.getImageUrls(); // Ensure Product has this method
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        vh.viewPagerProductImages.setAdapter(adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(vh.tabLayout, vh.viewPagerProductImages,
                (tab, position) -> {});  // No need to customize the tab, it's just a dot indicator.
        tabLayoutMediator.attach();

        vh.name.setText(product.getName());
        // Get the resource ID for the product icon
//        int imageResourceId = getResources().getIdentifier(product.getIcon(), "drawable", getPackageName());

        // Set the image resource for the ImageView
//        ImageView productImage = findViewById(R.id.product_image);
//        vh.image.setImageResource(imageResourceId);

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