package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakrapp.CartManager;
import com.example.sneakrapp.ImagePagerAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.WishlistManager;
import com.example.sneakrapp.models.Product;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    MaterialButton shop, home, wishlist;

    private static final String TAG = "DetailsActivity";
    private static final String WISHLIST_PREFS = "WishlistPrefs";

    private ImageView toggleWishlistButton;
    private ViewPager2 viewPagerProductImages;
    private TextView name, description;
    private Spinner size, quantity;
    private Button addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        addToCartButton = findViewById(R.id.addToCart);
        addToCartButton.setOnClickListener(v -> addToCart());

        shop = findViewById(R.id.shop);
        wishlist = findViewById(R.id.wishlist);
        home = findViewById(R.id.home);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cartActivity = new Intent(getBaseContext(), CartActivity.class);
                startActivity(cartActivity);
            }

        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainActivity);
            }

        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent wishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                startActivity(wishlistActivity);
            }

        });
        initializeViews();

        Product product = getProductFromIntent();
        if (product != null) {
            setupUIWithProduct(product);
        } else {
            Log.e(TAG, "Received null product data");
            finish();
        }
    }

    private void initializeViews() {
        name = findViewById(R.id.product_name);
        description = findViewById(R.id.product_description);
        addToCartButton = findViewById(R.id.addToCart);
        size = findViewById(R.id.size_spinner);
        quantity = findViewById(R.id.quantity_spinner);
        viewPagerProductImages = findViewById(R.id.viewPagerProductImages);
        toggleWishlistButton = findViewById(R.id.toggleWishlistButton);
    }

    private Product getProductFromIntent() {
        String productJson = getIntent().getStringExtra("product_details");
        return new Gson().fromJson(productJson, Product.class);
    }

    private void setupUIWithProduct(Product product) {
        List<String> imageUrls = product.getImageUrls();
        viewPagerProductImages.setAdapter(new ImagePagerAdapter(this, imageUrls));

        name.setText(product.getName());
        description.setText(product.getDescription());

        setupWishlistToggleButton(product);
    }

    private void setupWishlistToggleButton(Product product) {
        SharedPreferences sharedPreferences = getSharedPreferences(WISHLIST_PREFS, MODE_PRIVATE);
        boolean[] isInWishlist = {sharedPreferences.getBoolean(product.getName(), false)};
        updateWishlistButton(isInWishlist[0]);

        toggleWishlistButton.setOnClickListener(v -> {
            isInWishlist[0] = !isInWishlist[0];  // Toggle the state
            updateWishlistButton(isInWishlist[0]);

            // Update the WishlistManager and SharedPreferences
            if (isInWishlist[0]) {
                WishlistManager.getInstance().addProduct(product);
            } else {
                WishlistManager.getInstance().removeProduct(product);
            }

            // Save the wishlist state in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(product.getName(), isInWishlist[0]);
            editor.apply();
        });
    }



    private void updateWishlistButton(boolean isInWishlist) {
        toggleWishlistButton.setImageResource(isInWishlist ? R.drawable.filled_heart : R.drawable.empty_heart);
    }

    private void addToCart() {
        Product product = getProductFromIntent();
        if (product != null) {
            // Pass 'this' as the context to getInstance()
            CartManager.getInstance(this).addProduct(product);
            // Optionally, provide feedback to the user (e.g., toast message)
            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
        }
    }



}