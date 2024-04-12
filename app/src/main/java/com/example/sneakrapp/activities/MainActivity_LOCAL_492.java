package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        CardView designerCategory;
        ImageView wishlistButton;


        public ViewHolder() {
            designerCategory = findViewById(R.id.designerCategory);
            wishlistButton = findViewById(R.id.wishlistButton);
        }
    }
    ViewHolder vh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();

        vh.designerCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent designerActivity = new Intent(MainActivity.this, DesignerActivity.class);

                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                startActivity(designerActivity);
            }
        });

        vh.wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loadWishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                startActivity(loadWishlistActivity);
            }
        });
    }


    }

