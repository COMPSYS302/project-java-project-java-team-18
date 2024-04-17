package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;

public class DesignerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MultiCategoryProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        recyclerView = findViewById(R.id.products_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String category = getIntent().getStringExtra("category");

        List<Product> products1 = DataProvider.getProducts(category);
        adapter = new MultiCategoryProductAdapter(this, products1);
        recyclerView.setAdapter(adapter);

//        List<Product> product = DataProvider.getProducts("Designer");
//
//        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_designer, product);
//        ListView listView = findViewById(R.id.products_listview);
//        listView.setAdapter(productAdaptor);

//        List<Product> products = DataProvider.getProducts("Designer");
//        if (products != null && !products.isEmpty()) {
//            adapter = new MultiCategoryProductAdapter(this, products);
//            recyclerView.setAdapter(adapter);
//        } else {
//            Log.e("DesignerActivity", "No products found for Designer category");
//            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show();
//        }
    }
}
