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

import com.example.sneakrapp.CartAdapter;
import com.example.sneakrapp.CartManager;
import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.models.Product;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = "CartActivity";
    private CartAdapter adapter;
    private RecyclerView recyclerView;
    private TextView emptyCartMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recyclerview);
        emptyCartMessage = findViewById(R.id.empty_cart_message);

        // Updated to include 'this' as the context parameter
        List<Product> cart = CartManager.getInstance(this).getCartItems();
        emptyCartMessage.setVisibility(cart.isEmpty() ? View.VISIBLE : View.GONE);

        // Make sure to pass context to CartAdapter as well
        adapter = new CartAdapter(this, cart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Assume you're calling removeFromCart somewhere in your activity
    public void removeFromCart(View view) {
        RecyclerView.ViewHolder viewHolder = recyclerView.findContainingViewHolder(view);
        if (viewHolder != null) {
            int position = viewHolder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                List<Product> cartItems = CartManager.getInstance(this).getCartItems();
                Product product = cartItems.get(position);
                CartManager.getInstance(this).removeProduct(product);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, cartItems.size());
                updateEmptyCartMessage();
            }
        }
    }

    private void updateEmptyCartMessage() {
        emptyCartMessage.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}