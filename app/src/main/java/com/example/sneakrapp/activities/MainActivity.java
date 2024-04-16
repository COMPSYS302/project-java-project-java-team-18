package com.example.sneakrapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.sneakrapp.R;
import com.example.sneakrapp.activities.DesignerActivity;
import com.example.sneakrapp.activities.ActiveActivity;
import com.example.sneakrapp.activities.NewestActivity;

import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.activities.WishlistActivity;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private List<Product> productList;
    private ViewHolder vh;
    private DataProvider dataProvider = new DataProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();
        productList = new ArrayList<>();

        productList.addAll(DataProvider.getProducts("Designer"));
        productList.addAll(DataProvider.getProducts("Active-Wear"));
        productList.addAll(DataProvider.getProducts("Newest-Collections"));

        setupSearch();

        Log.d("MainActivity", "Product List Size: " + productList.size());

        vh.designerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);
                startActivity(designerActivity);
            }

        });
        vh.activeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activeActivity = new Intent(MainActivity.this, ActiveActivity.class);
                startActivity(activeActivity);
            }

        });

        vh.newestCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newestActivity = new Intent(MainActivity.this, NewestActivity.class);
                startActivity(newestActivity);
            }

        });
        if (vh.wishlistButton != null) {
            vh.wishlistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent loadWishlistActivity = new Intent(MainActivity.this, WishlistActivity.class);
                    startActivity(loadWishlistActivity);
                    Log.e("MainActivity", "Works");
                }
            });
        } else {
            Log.e("MainActivity", "Wishlist button is null");
        }
    }

    private void setupSearch() {
        vh.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    vh.searchViewer.setVisibility(View.GONE);
                } else {
                    vh.searchViewer.setVisibility(View.VISIBLE);
                    searchingData(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method can be left empty
            }
        });
    }

    private void searchingData(String query) {
        Map<Integer, Map<String, Object>> products = DataProvider.generateShoeProducts();
        ArrayList<String> filteredProductNames = new ArrayList<>();

        for (Map.Entry<Integer, Map<String, Object>> productEntry : products.entrySet()) {
            Map<String, Object> productDetails = productEntry.getValue();
            String productName = (String) productDetails.get("name");

            // Perform case-insensitive search
            if (productName.toLowerCase().contains(query.toLowerCase())) {
                filteredProductNames.add(productName);
            }
        }

        Log.d("MainActivity", "Filtered Product Names: " + filteredProductNames);

        vh.searchAdapter.clear();
        vh.searchAdapter.addAll(filteredProductNames);
        vh.searchAdapter.notifyDataSetChanged();

        vh.searchViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProduct = vh.searchAdapter.getItem(position);
                Log.d("MainActivity", "Selected product: " + selectedProduct);

                // Find the corresponding Product object in your data list based on the selected product name
                for (Product product : productList) {
                    if (product.getName().equals(selectedProduct)) {
                        Log.d("MainActivity", "Matching product found: " + product.getName());

                        // Create and start the intent for the DetailsActivity
                        Intent detailsActivity = new Intent(MainActivity.this, DetailsActivity.class);

                        // Serialize the Product object using Gson and put it as an extra to the intent
                        Gson gson = new Gson();
                        String productJson = gson.toJson(product);
                        detailsActivity.putExtra("product_details", productJson);

                        startActivity(detailsActivity);
                        return; // Break the loop once the product is found
                    }
                }

                // If no matching product is found
                Log.e("MainActivity", "No matching product found for: " + selectedProduct);
            }
        });
    }


    private class ViewHolder {
        CardView designerCategory;
        CardView activeCategory;
        CardView newestCategory;


        ImageView wishlistButton;
        ListView searchViewer;
        ArrayAdapter<String> searchAdapter;
        EditText searchBar;

        public ViewHolder() {
            designerCategory = findViewById(R.id.designerCategory);
            activeCategory = findViewById(R.id.activeCategory);
            newestCategory = findViewById(R.id.newestCategory);

            wishlistButton = findViewById(R.id.wishlistButton);
            searchBar = findViewById(R.id.SearchButton);
            searchViewer = findViewById(R.id.searchListView);
            searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
            searchViewer.setAdapter(searchAdapter);
        }
    }
}
