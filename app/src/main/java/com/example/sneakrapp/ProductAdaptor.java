package com.example.sneakrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.sneakrapp.models.Product;

import java.util.List;

public class ProductAdaptor extends ArrayAdapter<Product> {

    private class ViewHolder {
        ImageView iconImageView, heartImageView;
        TextView productTextView, descriptionTextView, priceTextView;

        public ViewHolder(View currentListViewItem) {
            iconImageView = currentListViewItem.findViewById(R.id.product_listview_item_icon);
            productTextView = currentListViewItem.findViewById(R.id.product_listview_textview_icon);
            heartImageView = currentListViewItem.findViewById(R.id.product_listview_item_heart);
            descriptionTextView = currentListViewItem.findViewById(R.id.product_listview_textview_description);
            priceTextView = currentListViewItem.findViewById(R.id.product_listview_textview_price);




        }
    }


    Context mContext;
    int mLayout;
    List<Product> products;

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



        //Set the attributed of list_view_number_item views
        int i = mContext.getResources().getIdentifier(
                currentProduct.getIcon(), "drawable",
                mContext.getPackageName());

        int j = mContext.getResources().getIdentifier(
                currentProduct.getHeart(), "drawable",
                mContext.getPackageName());

        //Setting the icon
        vh.iconImageView.setImageResource(i);

        vh.heartImageView.setImageResource(j);
        vh.productTextView.setText(currentProduct.getName());
        vh.descriptionTextView.setText(currentProduct.getDescription());

        //vh.priceTextView.setText(currentProduct.getPrice());



        return currentListViewItem;
    }
}
