package com.example.sneakrapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.WishlistManager;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    private MultiCategoryProductAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.wishlist_recyclerview);
        if (recyclerView == null) {
            throw new RuntimeException("RecyclerView not found. Check your layout file.");
        }
        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();
        adapter = new MultiCategoryProductAdapter(this, wishlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();
        if (adapter != null) {
            adapter.updateData(wishlist);
            adapter.notifyDataSetChanged();
        } else {
            adapter = new MultiCategoryProductAdapter(this, wishlist);
            recyclerView.setAdapter(adapter);
        }
    }
}
