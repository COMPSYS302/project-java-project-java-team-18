package com.example.sneakrapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sneakrapp.ProductAdaptor;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    private ProductAdaptor adapter;
    private ListView listView;
    private List<Product> products;
    private class ViewHolder {
        ImageView image;

        TextView name;


        public ViewHolder() {
            name = findViewById(R.id.product_listview_textview_icon);
            image = findViewById(R.id.product_listview_item_icon);
        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        String category = "desiredCategory";  // Replace "desiredCategory" with the actual category name
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
}
