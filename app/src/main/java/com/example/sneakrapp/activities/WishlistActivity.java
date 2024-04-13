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
    //private ProductAdaptor adapter;
    private List<Product> products;
    private RecyclerView recyclerView;
    private MultiCategoryProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        String category = "desiredCategory";  // Replace "desiredCategory" with the actual category name

        List<Product> wishlist = WishlistManager.getInstance().getWishlistItems();
        RecyclerView recyclerView = findViewById(R.id.wishlist_recyclerview);
        if (recyclerView == null) {
            throw new RuntimeException("RecyclerView not found. Check your layout file.");
        }

            // Now set up your RecyclerView or ListView to display these products
            MultiCategoryProductAdapter adapter = new MultiCategoryProductAdapter(this, wishlist);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

//
//        adapter = new ProductAdaptor(this, R.layout.product_listview_item, products);
//        listView.setAdapter(adapter);
//        List<Product> wishlist = adapter.getWishlist();
//        for (Product product : wishlist) {
//            vh.name.setText(product.getName());
//
//            int imageResourceId = getResources().getIdentifier(product.getIcon(), "drawable", getPackageName());
//
//            // Set the image resource for the ImageView
//            ImageView productImage = findViewById(R.id.product_listview_item_icon);
//            vh.image.setImageResource(imageResourceId);
//
//
//        }







//        List<Product> product = DataProvider.getProducts(category);
//        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_item, product);
//        ListView listView = findViewById(R.id.products_listview);
//        listView.setAdapter(productAdaptor);
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
