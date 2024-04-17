package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.WishlistManager;
import com.example.sneakrapp.models.Product;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private static final String TAG = "WishlistActivity";
    private MultiCategoryProductAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        Log.d(TAG, "onCreate: WishlistActivity created");
        recyclerView = findViewById(R.id.wishlist_recyclerview);
        if (recyclerView == null) {
            throw new RuntimeException("RecyclerView not found. Check your layout file.");
        }



        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();
        Log.d(TAG, "Fetched wishlist items: " + wishlist.size());
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
        } else {
            adapter = new MultiCategoryProductAdapter(this, wishlist);
            recyclerView.setAdapter(adapter);
        }
        Log.d(TAG, "onResume: Wishlist updated. Size: " + wishlist.size());
    }

}
