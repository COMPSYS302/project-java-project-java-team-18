package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.sneakrapp.ImagePagerAdapter;
import com.example.sneakrapp.MainSlideshowAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        CardView designerCategory, activewearCategory, newestCollections, shopAll;
        ImageView wishlistButton;
        ViewPager2 viewPagerProductImages;


        private ListView SearchViewer;
        private ArrayAdapter<String> SearchAdapter;
        private EditText searchBar;

        public ViewHolder() {
            designerCategory = findViewById(R.id.designerCategory);
            wishlistButton = findViewById(R.id.wishlistButton);
            activewearCategory = findViewById(R.id.activewearCategory);
            newestCollections = findViewById(R.id.newestCollectionsCategory);
            shopAll = findViewById(R.id.shopAllCategory);
            viewPagerProductImages = findViewById(R.id.viewPagerImageSlider1);
        }
    }
    ViewHolder vh;

    DataProvider dataProvider = new DataProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();
//        Map<Integer, Map<String, Object>> productsData = DataProvider.generateShoeProducts();
        List<String> firstImageUrls = new ArrayList<>();

        Map<Integer, Map<String, Object>> products = DataProvider.generateShoeProducts();

        for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
            Map<String, Object> productDetails = entry.getValue();
            List<String> imageUrls = (List<String>) productDetails.get("images");
            if (imageUrls != null && !imageUrls.isEmpty()) {
                firstImageUrls.add(imageUrls.get(0));
            }
        }
//
//        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, firstImageUrls, true);
//        vh.viewPagerProductImages.setAdapter(adapter);
        List<Product> products1 = DataProvider.getProducts("Designer"); // Replace "Designer" with your specific category
        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, products1, firstImageUrls, true);
        vh.viewPagerProductImages.setAdapter(adapter);

//        List<Product> productsList = new ArrayList<>();
//        Map<Integer, Map<String, Object>> productsData = DataProvider.generateShoeProducts();
//
//        for (Map.Entry<Integer, Map<String, Object>> entry : productsData.entrySet()) {
//            Map<String, Object> productDetails = entry.getValue();
//            String name = (String) productDetails.get("name");
//            String description = (String) productDetails.get("description");
//            double price = (Double) productDetails.get("price");
//            List<String> imageUrls = (List<String>) productDetails.get("images");
//            productsList.add(new Product(name, description, price, imageUrls));
//        }
//
//        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, productsList, true);
//        vh.viewPagerProductImages.setAdapter(adapter);




//        for (Map.Entry<Integer, Map<String, Object>> entry : productsData.entrySet()) {
//            Map<String, Object> productDetails = entry.getValue();
//            int id = (Integer) productDetails.get("id");
//            String name = (String) productDetails.get("name");
//            String description = (String) productDetails.get("description");
//            String price = (String) productDetails.get("price");
//            List<String> imageUrls = (List<String>) productDetails.get("images");
//            Product productsList = new Product(id, name, description, price, imageUrls);
//            ImagePagerAdapter adapter = new ImagePagerAdapter(this, firstImageUrls, productsList, true);
//            vh.viewPagerProductImages.setAdapter(adapter);
//        }


//        List<String> firstImageUrls = new ArrayList<>();
//        Map<Integer, Map<String, Object>> products = DataProvider.generateShoeProducts();
//
//        for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
//            Map<String, Object> productDetails = entry.getValue();
//            List<String> imageUrls = (List<String>) productDetails.get("images");
//            if (imageUrls != null && !imageUrls.isEmpty()) {
//                firstImageUrls.add(imageUrls.get(0));
//            }
//        }
//
//
//
//        ImagePagerAdapter adapter = new ImagePagerAdapter(this, firstImageUrls, productDetails, true);
//        vh.viewPagerProductImages.setAdapter(adapter);

        vh.designerCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                designerActivity.putExtra("category", "Designer");
                startActivity(designerActivity);
            }
        });

        vh.activewearCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent activewearActivity = new Intent(getBaseContext(), DesignerActivity.class);
                activewearActivity.putExtra("category", "Active-Wear");
                startActivity(activewearActivity);
            }
        });

        vh.newestCollections.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent newestCollectionsActivity = new Intent(getBaseContext(), DesignerActivity.class);
                newestCollectionsActivity.putExtra("category", "Newest-Collections");
                startActivity(newestCollectionsActivity);
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

    public void onLeftArrowClicked(View view) {
        int currentItem = vh.viewPagerProductImages.getCurrentItem();
        if (currentItem > 0) {
            vh.viewPagerProductImages.setCurrentItem(currentItem - 1);
        }
    }

    // Called when the right arrow ImageView is clicked
    public void onRightArrowClicked(View view) {
        int currentItem = vh.viewPagerProductImages.getCurrentItem();
        if (currentItem < vh.viewPagerProductImages.getAdapter().getItemCount() - 1) {
            vh.viewPagerProductImages.setCurrentItem(currentItem + 1);
        }
    }



}

