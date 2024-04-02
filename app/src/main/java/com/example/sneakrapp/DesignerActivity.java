package com.example.sneakrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DesignerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);
    }

        public void designerOnClick(View v)
        {
            Intent thisIntent = getIntent();
            String messageFromMain = thisIntent.getStringExtra("Message from main");
            Toast.makeText(this, messageFromMain, Toast.LENGTH_LONG).show();
        }

}