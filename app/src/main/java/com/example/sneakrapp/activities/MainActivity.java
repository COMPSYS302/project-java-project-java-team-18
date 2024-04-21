package com.example.sneakrapp.activities;


import static com.example.sneakrapp.helpers.DataProvider.clearPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.sneakrapp.CartFragment;
import com.example.sneakrapp.HomeFragment;
import com.example.sneakrapp.ImagePagerAdapter;
import com.example.sneakrapp.MainSlideshowAdapter;
import com.example.sneakrapp.R;
import com.example.sneakrapp.WishlistFragment;
import com.example.sneakrapp.databinding.ActivityMainBinding;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Collections;
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
    ActivityMainBinding binding;
    ViewPager2 viewPagerProductImages;

    private class ViewHolder {
        CardView designerCategory, shopAll;
        ImageView wishlistButton, imageView;
        ViewPager2 viewPagerProductImages;
        private ListView SearchViewer;
        private ArrayAdapter<String> SearchAdapter;
        private EditText searchBar;

        CardView activeCategory;
        CardView newestCategory;

        ListView searchViewer;
        ArrayAdapter<String> searchAdapter;



        public ViewHolder() {
            designerCategory = findViewById(R.id.designerCategory);
            wishlistButton = findViewById(R.id.wishlistButton);
            shopAll = findViewById(R.id.shopAllCategory);
            viewPagerProductImages = findViewById(R.id.viewPagerImageSlider1);

            activeCategory = findViewById(R.id.activeCategory);
            newestCategory = findViewById(R.id.newestCategory);

            searchBar = findViewById(R.id.SearchButton);
            searchViewer = findViewById(R.id.searchListView);
            searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
            searchViewer.setAdapter(searchAdapter);
            imageView = findViewById(R.id.imageView);

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
        clearPreferences();

//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            Intent intent = null;
//            if (item.getItemId() == R.id.home1) {
//                intent = new Intent(MainActivity.this, MainActivity.class);
//                //replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.shop1){
//                intent = new Intent(MainActivity.this, CartActivity.class);
//
//                //replaceFragment(new CartFragment());
//        } else if (item.getItemId() == R.id.wishlistButton1){
//                intent = new Intent(MainActivity.this, WishlistActivity.class);
//
//                //replaceFragment(new WishlistFragment());
//
//            }
//            startActivity(intent);
//            return true;
//
//                });

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
        viewPagerProductImages = findViewById(R.id.viewPagerImageSlider1);
//        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, firstImageUrls, true);
//        vh.viewPagerProductImages.setAdapter(adapter);

        String preferredCategory = DataProvider.getPreferredCategory();
        List<Product> preferredProducts = DataProvider.getProducts(preferredCategory);

        Log.e("Main", "its "+preferredProducts);
        MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, preferredProducts);

        List<Product> allProducts = DataProvider.getAllProducts(); // This method should return all products
        //List<Product> products1 = DataProvider.getProducts("Designer");
        //MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, allProducts, firstImageUrls, true);
        viewPagerProductImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
                startActivity(activeActivity);
            }

        });

        vh.newestCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newestActivity = new Intent(getBaseContext(), DesignerActivity.class);
                newestActivity.putExtra("category", "Newest-Collections");
                startActivity(newestActivity);
            }

        });


        vh.designerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                designerActivity.putExtra("category", "Designer");

                startActivity(designerActivity);
            }

        });

        vh.shopAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                designerActivity.putExtra("category", "Shop-All");

                startActivity(designerActivity);
            }

        });

//        vh.activeCategory.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);
//
//                Intent activewearActivity = new Intent(getBaseContext(), DesignerActivity.class);
//                activewearActivity.putExtra("category", "Active-Wear");
//                startActivity(activewearActivity);
//            }
//        });

//        vh.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent d = new Intent(MainActivity.this, WishlistActivity.class);
//                startActivity(d);
//
//            }
//        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        return true;
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
        setListViewHeightBasedOnChildren(vh.searchViewer); // Call a method to adjust the height

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
        updateUI(preferredProducts);  // Implement this method to update the UI with new products
    }

    private void updateUI(List<Product> products) {
        // Assuming you have a ViewPager2 and an adapter set up already
//        if (viewPagerProductImages.getAdapter() != null) {
//
//            //MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, preferredProducts, firstImageUrls, true);
//
//            List<Product> allProducts = DataProvider.getAllProducts(); // This method should return all products
//            //List<Product> products1 = DataProvider.getProducts("Designer");
//            //MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, allProducts, firstImageUrls, true);
//
//            //MainSlideshowAdapter adapter = (MainSlideshowAdapter) viewPagerProductImages.getAdapter();
//            MainSlideshowAdapter adapter = new MainSlideshowAdapter(this, products);
//
//            viewPagerProductImages.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//        } else {
//            // If the adapter has not been initialized yet, set it up
//            MainSlideshowAdapter newAdapter = new MainSlideshowAdapter(this, products);
//            viewPagerProductImages.setAdapter(newAdapter);
//        }

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

//    public void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame, fragment);
//        fragmentTransaction.commit();
//    }


}
