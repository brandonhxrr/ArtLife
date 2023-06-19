package com.brandonhxrr.artlife.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brandonhxrr.artlife.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Painting extends AppCompatActivity {

    private ImageView paintingImage;
    private FloatingActionButton fullscreenButton;
    private TextView paintingTitle;
    private TextView paintingLocation;
    private TextView paintingMuseum;
    private ImageButton saveButton;
    private ImageButton openButton;
    private TextView paintingAuthor;
    private TextView paintingDescription;
    com.brandonhxrr.artlife.data.Painting.Painting painting;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        paintingImage = findViewById(R.id.painting_image);
        fullscreenButton = findViewById(R.id.fab_button);
        paintingTitle = findViewById(R.id.title_image);
        paintingLocation = findViewById(R.id.painting_location);
        paintingMuseum = findViewById(R.id.painting_museum);
        saveButton = findViewById(R.id.save_button);
        openButton = findViewById(R.id.open_button);
        paintingAuthor = findViewById(R.id.painting_author);
        paintingDescription = findViewById(R.id.detail_image);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("Data")) {
           painting = (com.brandonhxrr.artlife.data.Painting.Painting) intent.getSerializableExtra("Data");

            Toast.makeText(this, "TXT: " + painting.getName(), Toast.LENGTH_SHORT).show();
            Glide.with(this).load(painting.getImageUri()).into(paintingImage);
            paintingTitle.setText(painting.getName());
            paintingLocation.setText(painting.getLocation() + ", " + painting.getDate());
            paintingMuseum.setText(painting.getMuseum());
            paintingAuthor.setText(painting.getAuthor());
            paintingDescription.setText(painting.getDescription());
        }
    }
}