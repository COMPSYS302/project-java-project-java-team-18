package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        //CardView wishlistButton;
        ImageButton wishlistButton;

//        public ViewHolder() {
//            wishlistButton = findViewById(R.id.wishlistButton);
//        }

//        public ViewHolder() {
//            wishlistButton = findViewById(R.id.wishlistButton);
//        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();

//        vh.wishlistButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
//                designerActivity.putExtra("Message from main", "Hi");
//                startActivity(designerActivity);
//            }
//        });
        vh.wishlistButton= findViewById(R.id.wishlistButton);

        vh.wishlistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loadWishlistActivity = new Intent(getBaseContext(), WishlistActivity.class);
                startActivity(loadWishlistActivity);
            }
        });


    }


    }

