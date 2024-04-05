package com.example.sneakrapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakrapp.R;
import com.example.sneakrapp.helpers.DataProvider;

import java.util.Map;

public class DesignerActivity extends AppCompatActivity {
//    do the viewholder and all that

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);

        Map<Integer, String> products = DataProvider.generateShoeProducts();
        String text = "";for (Integer key: products.keySet())
            text += "\n" + Integer.toString(key) + " : " + products.get(key);

        TextView textviewTemp = findViewById(R.id.textview_temp);
        textviewTemp.setText(text);

    }
}