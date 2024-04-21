package com.example.sneakrapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.List;

public class MultiCategoryProductAdapter extends RecyclerView.Adapter<MultiCategoryProductAdapter.ViewHolder> {
    private static final String TAG = "ProductAdapter";

    private List<Product> productList;
    private Context context;

    public MultiCategoryProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconImageView, productDetails;
        public TextView nameTextView, descriptionTextView, priceTextView;
        public View layout;
        public ViewPager2 viewPagerProductImages;


        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.product_listview_item_icon);
            nameTextView = itemView.findViewById(R.id.product_listview_textview_icon);
            descriptionTextView = itemView.findViewById(R.id.product_listview_textview_description);
            priceTextView = itemView.findViewById(R.id.product_listview_textview_price);
            productDetails = itemView.findViewById(R.id.product_listview_item_arrow);
            layout = itemView;
            viewPagerProductImages = itemView.findViewById(R.id.viewPagerProductImages);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listview_designer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.priceTextView.setText(product.getPrice());
        //Glide.with(context).load(product.getImageUrls()).into(holder.iconImageView);
        Glide.with(context)
                .load(product.getImageUrls().get(0))
                .override(Target.SIZE_ORIGINAL)
                .fitCenter()
                .error(R.drawable.error_pic)
                .transition(DrawableTransitionOptions.withCrossFade()) // Optional fade effect
                .into(holder.iconImageView);


//            int imageResId = context.getResources().getIdentifier(product.getIcon(), "drawable", context.getPackageName());
//            holder.iconImageView.setImageResource(imageResId);

        holder.productDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and start the intent for the DetailsActivity
                Intent detailsActivity = new Intent(context, DetailsActivity.class);
                Gson gson = new Gson();
                String productJson = gson.toJson(product);
                detailsActivity.putExtra("product_details", productJson);
                context.startActivity(detailsActivity);
            }
        });

//        Product product1 = productList.get(position);
//        if (product1 != null) {
//            holder.nameTextView.setText(product1.getName());
//            holder.descriptionTextView.setText(product1.getDescription());
//            holder.priceTextView.setText(product1.getPrice());
//
//            int imageResId = context.getResources().getIdentifier(product1.getIcon(), "drawable", context.getPackageName());
//            holder.iconImageView.setImageResource(imageResId);
//        } else {
//            Log.d(TAG, "Product at position " + position + " is null.");
//        }


    }

//    public void updateData(List<Product> newProducts) {
//
//        holder.productDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Log the click event
//                Log.d(TAG, "Product details clicked");
//
//                // Create and start the intent for the DetailsActivity
//                Intent detailsActivity = new Intent(context, DetailsActivity.class);
//                Gson gson = new Gson();
//                String productJson = gson.toJson(product);
//                detailsActivity.putExtra("product details", productJson);
//                context.startActivity(detailsActivity);
//            }
//        });
//
//        // Log the product details
//        Log.d(TAG, "Product name: " + product.getName());
//        Log.d(TAG, "Product description: " + product.getDescription());
//        Log.d(TAG, "Product price: " + product.getPrice());
//    }
//        }

    @Override
    public int getItemCount() {
        int count = productList.size();
        Log.d(TAG, "Total items in adapter: " + count);
        return count;
    }

    public void updateData(List<Product> newProducts) {
        if (newProducts != null) {
            productList.clear();
            for (Product product : newProducts) {
                if (product != null) {
                    productList.add(product);
                } else {
                    Log.d(TAG, "Attempted to add null product to the list.");
                }
            }
            notifyDataSetChanged();
            Log.d(TAG, "Adapter data updated. New size: " + productList.size());
        } else {
            Log.d(TAG, "Received null product list");
        }
    }


}