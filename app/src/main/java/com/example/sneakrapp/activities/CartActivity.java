package com.example.sneakrapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sneakrapp.CartManager;
import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.models.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    private MultiCategoryProductAdapter adapter;
    private RecyclerView recyclerView;
    private TextView emptyCartMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Log.d(TAG, "onCreate: CartActivity created");
        recyclerView = findViewById(R.id.cart_recyclerview);
        emptyCartMessage = findViewById(R.id.empty_cart_message);
        if (recyclerView == null || emptyCartMessage == null) {
            throw new RuntimeException("RecyclerView or Empty Cart Message not found. Check your layout file.");
        }

        // Fetch cart items
        List<Product> cart = CartManager.getInstance().getCartItems();
        Log.d(TAG, "Fetched cart items: " + cart.size());

        // Show or hide empty cart message based on cart size
        emptyCartMessage.setVisibility(cart.isEmpty() ? View.VISIBLE : View.GONE);

        // Initialize RecyclerView
        adapter = new MultiCategoryProductAdapter(this, cart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void removeFromCart(View view) {
        View parentView = (View) view.getParent();
        RecyclerView recyclerView = findViewById(R.id.cart_recyclerview);
        int position = recyclerView.getChildLayoutPosition(parentView);

        List<Product> cartItems = CartManager.getInstance().getCartItems();
        if (position >= 0 && position < cartItems.size()) {
            Product product = cartItems.get(position);
            CartManager.getInstance().removeProduct(product);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, cartItems.size());
            updateEmptyCartMessage();
        }
    }

    private void updateEmptyCartMessage() {
        emptyCartMessage.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
