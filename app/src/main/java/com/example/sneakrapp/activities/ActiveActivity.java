package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.sneakrapp.MultiCategoryProductAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActiveActivity extends AppCompatActivity {
    private List<Product> productList;
    private ViewHolder vh;

    private RecyclerView recyclerView;
    private MultiCategoryProductAdapter adapter;

    private ArrayAdapter<String> searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activewear);
        vh = new ViewHolder();
        productList = new ArrayList<>();
        productList.addAll(DataProvider.getProducts("Active-Wear"));
        productList.addAll(DataProvider.getProducts("Designer"));
        productList.addAll(DataProvider.getProducts("Newest-Collection"));


        recyclerView = findViewById(R.id.products_listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String category = getIntent().getStringExtra("category");
        onCategorySelected(category);

        List<Product> products1 = DataProvider.getProducts(category);
        adapter = new MultiCategoryProductAdapter(this, products1);
        recyclerView.setAdapter(adapter);


        setupSearch();
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

        searchAdapter.clear();
        searchAdapter.addAll(filteredProductNames);
        searchAdapter.notifyDataSetChanged();

        vh.searchViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProduct = searchAdapter.getItem(position);

                // Find the corresponding Product object in your data list based on the selected product name
                for (Product product : productList) {
                    if (product.getName().equals(selectedProduct)) {
                        // Create and start the intent for the DetailsActivity
                        Intent detailsActivity = new Intent(ActiveActivity.this, DetailsActivity.class);

                        // Serialize the Product object using Gson and put it as an extra to the intent
                        Gson gson = new Gson();
                        String productJson = gson.toJson(product);
                        detailsActivity.putExtra("product_details", productJson);

                        startActivity(detailsActivity);
                        return; // Break the loop once the product is found
                    }
                }
            }
        });
    }



    private class ViewHolder {
        EditText searchBar;
        ListView searchViewer;
        MaterialButton shop, home, wishlist;


        public ViewHolder() {
            shop = findViewById(R.id.shop);
            wishlist = findViewById(R.id.wishlist);
            home = findViewById(R.id.home);
            searchBar = findViewById(R.id.SearchButton);
            searchViewer = findViewById(R.id.searchListView);
            searchAdapter = new ArrayAdapter<>(ActiveActivity.this, android.R.layout.simple_list_item_1);
            searchViewer.setAdapter(searchAdapter);


            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent cartActivity = new Intent(getBaseContext(), CartActivity.class);
                    startActivity(cartActivity);
                }

            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(mainActivity);
                }

            });

            wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent wishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                    startActivity(wishlistActivity);
                }

            });
        }
    }

    public void onCategorySelected(String category) {
        DataProvider.updateCategoryCount(category);
        Log.d("CategoryActivity", "Updated count for " + category + ": " );
        // Optionally perform other actions such as updating the UI or fetching data
    }
}