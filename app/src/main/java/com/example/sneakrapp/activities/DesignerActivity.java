package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;
import java.util.Map;

public class DesignerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MultiCategoryProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        recyclerView = findViewById(R.id.products_listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = DataProvider.getProducts("Designer");
        adapter = new MultiCategoryProductAdapter(this, products);
        recyclerView.setAdapter(adapter);

//        List<Product> product = DataProvider.getProducts("Designer");
//
//        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_designer, product);
//        ListView listView = findViewById(R.id.products_listview);
//        listView.setAdapter(productAdaptor);
    }

}