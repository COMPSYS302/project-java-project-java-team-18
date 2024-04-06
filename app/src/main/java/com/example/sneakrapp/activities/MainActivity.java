package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder {
        CardView cardviewNumbers;

        public ViewHolder() {
            cardviewNumbers = findViewById(R.id.cardViewNumbers);
        }
    }

    ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vh = new ViewHolder();

        vh.cardviewNumbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent designerActivity = new Intent(getBaseContext(), DesignerActivity.class);
                designerActivity.putExtra("Message from main", "Hi");
                startActivity(designerActivity);
            }
        });

    }

}