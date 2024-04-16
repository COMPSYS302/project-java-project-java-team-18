package com.example.sneakrapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.List;

public class MainSlideshowAdapter extends RecyclerView.Adapter<MainSlideshowAdapter.ViewHolder> {

    private List<String> imageUrls;
    private Context context;
    private List<Product> product;

    private boolean enableClickThrough;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView); // Ensure this matches your ImageView in item_image.xml
        }
    }
    public MainSlideshowAdapter(Context context, List<Product> product, List<String> imageUrls, boolean enableClickThrough) {
        this.context = context;
        this.product = product;
        this.imageUrls = imageUrls;
        this.enableClickThrough = enableClickThrough;

    }

    @Override
    public MainSlideshowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new MainSlideshowAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MainSlideshowAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(imageUrls.get(position))
                .override(Target.SIZE_ORIGINAL)
                .fitCenter()
                .error(R.drawable.firstpic1)  // Make sure you have a default image in drawable to handle errors
                .into(holder.imageView);

            if (enableClickThrough) {
                holder.imageView.setOnClickListener(v -> {
                    Gson gson = new Gson();
                    String productJson = gson.toJson(product);
                    Intent intent = new Intent(context, DetailsActivity.class);

                    intent.putExtra("product details", productJson);
                    context.startActivity(intent);
                });
            }
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }



}
