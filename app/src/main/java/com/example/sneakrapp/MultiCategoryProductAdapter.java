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

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.product_listview_item_icon);
            nameTextView = itemView.findViewById(R.id.product_listview_textview_icon);
            descriptionTextView = itemView.findViewById(R.id.product_listview_textview_description);
            priceTextView = itemView.findViewById(R.id.product_listview_textview_price);
            productDetails = itemView.findViewById(R.id.product_listview_item_arrow);
            layout = itemView;
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
        if (product != null) {
            holder.nameTextView.setText(product.getName());
            holder.descriptionTextView.setText(product.getDescription());
            holder.priceTextView.setText(product.getPrice());

            int imageResId = context.getResources().getIdentifier(product.getIcon(), "drawable", context.getPackageName());
            holder.iconImageView.setImageResource(imageResId);
        } else {
            Log.d(TAG, "Product at position " + position + " is null.");
        }


        holder.productDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log the click event
                Log.d(TAG, "Product details clicked");

                // Create and start the intent for the DetailsActivity
                Intent detailsActivity = new Intent(context, DetailsActivity.class);
                Gson gson = new Gson();
                String productJson = gson.toJson(product);
                detailsActivity.putExtra("product details", productJson);
                context.startActivity(detailsActivity);
            }
        });

        // Log the product details
        Log.d(TAG, "Product name: " + product.getName());
        Log.d(TAG, "Product description: " + product.getDescription());
        Log.d(TAG, "Product price: " + product.getPrice());
    }

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
