package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakrapp.ProductAdaptor;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;
import java.util.Map;

public class DesignerActivity extends AppCompatActivity {
//    do the viewholder and all that

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        List<Product> product = DataProvider.getProducts();

        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_item,
                product);

        ListView listView = findViewById(R.id.products_listview);
        listView.setAdapter(productAdaptor);

    }
}