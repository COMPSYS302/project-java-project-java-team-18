package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class DesignerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MultiCategoryProductAdapter adapter;

    private class ViewHolder {

        MaterialButton shop, home, wishlist;


        public ViewHolder() {
            shop = findViewById(R.id.shop);
            wishlist = findViewById(R.id.wishlist);
            home = findViewById(R.id.home);
        }
    }
    ViewHolder vh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);
        vh = new ViewHolder();


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