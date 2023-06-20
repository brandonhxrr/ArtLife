package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.brandonhxrr.artlife.R;
import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

public class FullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ZoomageView imageView = findViewById(R.id.image);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("urlImage")) {
            String urlImage = intent.getStringExtra("urlImage");
            Glide.with(this).load(urlImage).into(imageView);
        }
    }
}