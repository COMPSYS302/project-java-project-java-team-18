package com.example.sneakrapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.sneakrapp.activities.DetailsActivity;
import com.example.sneakrapp.activities.MainActivity;
import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;

import java.util.List;

public class MainSlideshowAdapter extends RecyclerView.Adapter<MainSlideshowAdapter.ViewHolder> {


    private Context context;
    private List<Product> product;


    ImageView imageView;



    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    public MainSlideshowAdapter(Context context, List<Product> product) {
        this.context = context;
        this.product = product;

    }
    ViewHolder vh;


    @Override
    public MainSlideshowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);

        return new MainSlideshowAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MainSlideshowAdapter.ViewHolder holder, int position) {
        Product currentProduct = product.get(position);
        //Log.d("AdapterDebug", "Binding position: " + position + ", URL: " + imageUrls.get(position));
        Glide.with(context)
                .load(currentProduct.getImageUrls().get(0))
                .override(Target.SIZE_ORIGINAL)
                .error(R.drawable.firstpic1)
                .into(holder.imageView);


                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detailsActivity = new Intent(context, DetailsActivity.class);
                        Gson gson = new Gson();
                        String productJson = gson.toJson(currentProduct);
                        detailsActivity.putExtra("product_details", productJson);
                        context.startActivity(detailsActivity);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return product.size();    }
//
//    @Override
//    public int getItemCount() {
//        return imageUrls.size();
//    }


}
