package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

        // Fetch wishlist items
        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();
        Log.d(TAG, "Fetched wishlist items: " + wishlist.size());

        // Show or hide empty wishlist message based on wishlist size
        TextView emptyWishlistMessage = findViewById(R.id.empty_wishlist_message);
        if (emptyWishlistMessage == null) {
            throw new RuntimeException("Empty wishlist message not found. Check your layout file.");
        }
        emptyWishlistMessage.setVisibility(wishlist.isEmpty() ? View.VISIBLE : View.GONE);

        // Initialize RecyclerView
        adapter = new MultiCategoryProductAdapter(this, wishlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Fetch wishlist items again
        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();

        // Update adapter with new data
        adapter.updateData(wishlist);

        Log.d(TAG, "onResume: Wishlist updated. Size: " + wishlist.size());
    }
}
