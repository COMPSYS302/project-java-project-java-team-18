package com.example.sneakrapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;

public class NewestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MultiCategoryProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_newestcollections);

        recyclerView = findViewById(R.id.products_listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = DataProvider.getProducts("Newest-Collection");
        adapter = new MultiCategoryProductAdapter(this, products);
        recyclerView.setAdapter(adapter);

    }
}