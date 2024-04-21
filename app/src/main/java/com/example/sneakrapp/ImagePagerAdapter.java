package com.example.sneakrapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ViewHolder> {
    private List<String> imageUrls;
    private Context context;
    private Product product;

    private boolean enableClickThrough;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView); // Ensure this matches your ImageView in item_image.xml
        }
    }
    public ImagePagerAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(imageUrls.get(position))
                .override(Target.SIZE_ORIGINAL)
                .fitCenter()
                .error(R.drawable.firstpic1)  // Make sure you have a default image in drawable to handle errors
                .into(holder.imageView);

//            if (enableClickThrough) {
//                holder.imageView.setOnClickListener(v -> {
//                    Intent intent = new Intent(context, DetailsActivity.class);
//                    Gson gson = new Gson();
//                    String productJson = gson.toJson(product);
//                    intent.putExtra("product", productJson);
//                    context.startActivity(intent);
//                });
//            }
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


}