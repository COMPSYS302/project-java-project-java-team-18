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
        onCategorySelected(category);

        List<Product> products1 = DataProvider.getProducts(category);
        adapter = new MultiCategoryProductAdapter(this, products1);
        recyclerView.setAdapter(adapter);

    }
    public void onCategorySelected(String category) {
        DataProvider.updateCategoryCount(category);
        Log.d("CategoryActivity", "Updated count for " + category + ": " );
        // Optionally perform other actions such as updating the UI or fetching data
    }
}
