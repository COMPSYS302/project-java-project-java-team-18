package com.example.sneakrapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.sneakrapp.models.Product;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;

    public CartAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, descriptionTextView, priceTextView;
        public ImageView productImageView, binIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_listview_textview_icon);
            descriptionTextView = itemView.findViewById(R.id.product_listview_textview_description);
            priceTextView = itemView.findViewById(R.id.product_listview_textview_price);
            productImageView = itemView.findViewById(R.id.product_listview_item_icon);
            binIcon = itemView.findViewById(R.id.product_listview_item_bin);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.priceTextView.setText(product.getPrice());
        if (product.getImageUrls() != null && !product.getImageUrls().isEmpty()) {
            Glide.with(context).load(product.getImageUrls().get(0)).into(holder.productImageView);
        }
        holder.binIcon.setOnClickListener(v -> removeItem(holder.getAdapterPosition()));
    }

    private void removeItem(int position) {
        if (position != RecyclerView.NO_POSITION && position < productList.size()) {
            productList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, productList.size());
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
