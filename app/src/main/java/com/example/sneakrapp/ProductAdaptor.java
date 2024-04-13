package com.example.sneakrapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.activities.WishlistActivity;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductAdaptor extends ArrayAdapter<Product> {

    private class ViewHolder {
        ImageView iconImageView, heartImageView, productDetails;
        TextView productTextView, descriptionTextView, priceTextView, wishlist;

        public ViewHolder(View currentListViewItem) {
            iconImageView = currentListViewItem.findViewById(R.id.product_listview_item_icon);
            productTextView = currentListViewItem.findViewById(R.id.product_listview_textview_icon);
            //heartImageView = currentListViewItem.findViewById(R.id.product_listview_item_heart);
            descriptionTextView = currentListViewItem.findViewById(R.id.product_listview_textview_description);
            priceTextView = currentListViewItem.findViewById(R.id.product_listview_textview_price);
            productDetails = currentListViewItem.findViewById(R.id.product_listview_item_arrow);
            wishlist = currentListViewItem.findViewById(R.id.moveToWishlist);


            if (wishlist != null) {
                wishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detailsActivity = new Intent(getContext(), WishlistActivity.class);
                        getContext().startActivity(detailsActivity);
                        Log.d("ProductAdapter", "Wishlist clicked!");
                    }
                });
            } else {
                Log.e("ProductAdapter", "Wishlist TextView is null!");
            }
        }
    }

    Context mContext;
    int mLayout;
    List<Product> products;
    public List<Product> wishlist_array = new ArrayList<>();


    public ProductAdaptor(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayout = resource;
        products = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//Get a reference to the current ListView item
        View currentListViewItem = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
        }
        ViewHolder vh = new ViewHolder(currentListViewItem);
        //Get the Number object for the current position
        Product currentProduct = products.get(position);

        Gson gson = new Gson();
        String productJson = gson.toJson(currentProduct);
        vh.productDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and start the intent for the DetailsActivity
                Intent detailsActivity = new Intent(getContext(), DetailsActivity.class);
                detailsActivity.putExtra("product details", productJson);
                getContext().startActivity(detailsActivity);
            }
        });


//        vh.wishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent detailsActivity = new Intent(mContext, WishlistActivity.class);
//                mContext.startActivity(detailsActivity);
//
//                                if (!wishlist_array.contains(currentProduct)) {
//                    wishlist_array.add(currentProduct);
//                    Toast.makeText(mContext, "Added to wishlist!", Toast.LENGTH_SHORT).show();
//                    // Notify the activity or update UI as needed
//                }
//            }
//        });


        //Set the attributed of list_view_number_item views
        int i = mContext.getResources().getIdentifier(
                currentProduct.getIcon(), "drawable",
                mContext.getPackageName());

//        int j = mContext.getResources().getIdentifier(
//                currentProduct.getHeart(), "drawable",
//                mContext.getPackageName());

        //Setting the icon
        vh.iconImageView.setImageResource(i);

        //vh.heartImageView.setImageResource(j);
        vh.productTextView.setText(currentProduct.getName());
        vh.priceTextView.setText(currentProduct.getPrice());
        vh.descriptionTextView.setText(currentProduct.getDescription());

        //vh.priceTextView.setText(Double.toString(currentProduct.getPrice()));


        return currentListViewItem;
    }
}