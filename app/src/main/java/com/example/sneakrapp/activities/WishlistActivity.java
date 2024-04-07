package com.example.sneakrapp.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sneakrapp.ProductAdaptor;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        List<Product> product = DataProvider.getProducts();

        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_item,
                product);

        ListView listView = findViewById(R.id.products_listview);
        listView.setAdapter(productAdaptor);

    }
}
