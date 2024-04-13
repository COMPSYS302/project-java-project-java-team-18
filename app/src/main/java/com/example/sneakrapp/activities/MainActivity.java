package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        CardView designerCategory;
        ImageView wishlistButton;


        private ListView SearchViewer;
        private ArrayAdapter<String> SearchAdapter;
        private EditText searchBar;

        public ViewHolder() {
            designerCategory = findViewById(R.id.designerCategory);
            wishlistButton = findViewById(R.id.wishlistButton);
        }
    }
    ViewHolder vh;

    DataProvider dataProvider = new DataProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();

        vh.designerCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                startActivity(designerActivity);
            }
        });

        vh.wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loadWishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                startActivity(loadWishlistActivity);
            }
        });

        vh.searchBar = findViewById(R.id.SearchButton);

        vh.SearchViewer = findViewById(R.id.searchListView);
        vh.SearchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        vh.SearchViewer.setAdapter(vh.SearchAdapter);

        vh.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    vh.SearchViewer.setVisibility(View.GONE);
                } else {
                    vh.SearchViewer.setVisibility(View.VISIBLE);
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

        // Iterate through each product and filter by name
        for (Map.Entry<Integer, Map<String, Object>> productEntry : products.entrySet()) {
            Map<String, Object> productDetails = productEntry.getValue();
            String productName = (String) productDetails.get("name");

            // Check if the product name contains the query string
            if (productName.toLowerCase().contains(query.toLowerCase())) {
                filteredProductNames.add(productName);
            }
        }

        // Assuming vh.SearchAdapter is an ArrayAdapter<String>
        // Update ArrayAdapter data
        vh.SearchAdapter.clear();
        vh.SearchAdapter.addAll(filteredProductNames);
        vh.SearchAdapter.notifyDataSetChanged();
    }


}

