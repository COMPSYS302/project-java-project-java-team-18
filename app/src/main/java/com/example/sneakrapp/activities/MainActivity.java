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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.sneakrapp.MainSlideshowAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.databinding.ActivityMainBinding;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewPager2 viewPagerProductImages;

    private class ViewHolder {
        CardView designerCategory, shopAll;
        MaterialButton shop, home, wishlist;
        ImageView wishlistButton;
        ViewPager2 viewPagerProductImages;
        private ListView SearchViewer;
        private ArrayAdapter<String> SearchAdapter;
        private EditText searchBar;

        CardView activeCategory;
        CardView newestCategory;

        ListView searchViewer;
        ArrayAdapter<String> searchAdapter;


        public ViewHolder() {
            shop = findViewById(R.id.shop);
            wishlist = findViewById(R.id.wishlist);
            home = findViewById(R.id.home);
            designerCategory = findViewById(R.id.designerCategory);
            //wishlistButton = findViewById(R.id.wishlistButton);
            shopAll = findViewById(R.id.shopAllCategory);
            viewPagerProductImages = findViewById(R.id.viewPagerImageSlider1);

            activeCategory = findViewById(R.id.activeCategory);
            newestCategory = findViewById(R.id.newestCategory);

            searchBar = findViewById(R.id.SearchButton);
            searchViewer = findViewById(R.id.searchListView);
            searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
            searchViewer.setAdapter(searchAdapter);
        }
    }
    ViewHolder vh;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        setContentView(R.layout.activity_main);
        DataProvider.init(this);

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


        viewPagerProductImages = findViewById(R.id.viewPagerImageSlider1);

        String preferredCategory = DataProvider.getPreferredCategory();
        List<Product> preferredProducts = DataProvider.getProducts(preferredCategory);

        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, preferredProducts);

        //List<Product> products1 = DataProvider.getProducts("Designer");
        //MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, allProducts, firstImageUrls, true);
        viewPagerProductImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();






        productList = new ArrayList<>();

        productList.addAll(DataProvider.getProducts("Designer"));
        productList.addAll(DataProvider.getProducts("Active-Wear"));
        productList.addAll(DataProvider.getProducts("Newest-Collections"));

        setupSearch();

        Log.d("MainActivity", "Product List Size: " + productList.size());

        vh.activeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activeActivity = new Intent(MainActivity.this, ActiveActivity.class);
                activeActivity.putExtra("category", "Active-Wear");
                startActivity(activeActivity);
            }

        });

        vh.newestCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newestActivity = new Intent(getBaseContext(), ActiveActivity.class);
                newestActivity.putExtra("category", "Newest-Collections");
                startActivity(newestActivity);
            }

        });

        vh.shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cartActivity = new Intent(getBaseContext(), CartActivity.class);
                startActivity(cartActivity);
            }

        });

        vh.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainActivity);
            }

        });

        vh.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent wishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                startActivity(wishlistActivity);
            }

        });


        vh.designerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent designerActivity = new Intent(getBaseContext(), ActiveActivity.class);
                designerActivity.putExtra("category", "Designer");

                startActivity(designerActivity);
            }

        });

        vh.shopAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), ActiveActivity.class);
                designerActivity.putExtra("category", "Shop-All");

                startActivity(designerActivity);
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
        setListViewHeightBasedOnChildren(vh.searchViewer);

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

    public void onLeftArrowClicked(View view) {
        int currentItem = viewPagerProductImages.getCurrentItem();
        if (currentItem > 0) {
            viewPagerProductImages.setCurrentItem(currentItem - 1);
        }
    }

    // Called when the right arrow ImageView is clicked
    public void onRightArrowClicked(View view) {
        int currentItem = viewPagerProductImages.getCurrentItem();
        if (currentItem < viewPagerProductImages.getAdapter().getItemCount() - 1) {
            viewPagerProductImages.setCurrentItem(currentItem + 1);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        // Refresh your data here
        String preferredCategory = DataProvider.getPreferredCategory();
        List<Product> preferredProducts = DataProvider.getProducts(preferredCategory);
        updateUI(preferredProducts);
    }

    private void updateUI(List<Product> products) {


        if (products != null && !products.isEmpty()) {
            MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, products);
            viewPagerProductImages.setAdapter(adapter);
        } else {
            Log.e("MainActivity", "No products found for the category");
        }
    }

    // Method to adjust ListView height based on its children
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
