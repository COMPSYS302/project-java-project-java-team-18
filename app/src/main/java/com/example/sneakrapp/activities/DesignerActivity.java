package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakrapp.ProductAdaptor;
import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;
import com.example.sneakrapp.models.Product;

import java.util.List;
import java.util.Map;

public class DesignerActivity extends AppCompatActivity {
//    do the viewholder and all that

//    private class ViewHolder {
//        ImageView productDetails;
//
//        public ViewHolder() {
//            productDetails = findViewById(R.id.product_listview_item_arrow);
//        }
//
//    }
//
//    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        List<Product> product = DataProvider.getProducts();

        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.product_listview_designer,
                product);

        ListView listView = findViewById(R.id.products_listview);
        listView.setAdapter(productAdaptor);

//        vh = new ViewHolder();
//
//        vh.productDetails.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);
//
//                Intent detailsActivity = new Intent(getBaseContext(), DetailsActivity.class);
//                startActivity(detailsActivity);
//            }
//        });

    }

}