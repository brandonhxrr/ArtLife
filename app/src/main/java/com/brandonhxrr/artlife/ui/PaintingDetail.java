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
import com.brandonhxrr.artlife.data.Painting.Painting;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaintingDetail extends AppCompatActivity {

    Painting painting;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        ImageView paintingImage = findViewById(R.id.painting_image);
        FloatingActionButton fullscreenButton = findViewById(R.id.fab_button);
        TextView paintingTitle = findViewById(R.id.title_image);
        TextView paintingLocation = findViewById(R.id.painting_location);
        TextView paintingMuseum = findViewById(R.id.painting_museum);
        ImageButton saveButton = findViewById(R.id.save_button);
        ImageButton openButton = findViewById(R.id.open_button);
        TextView paintingAuthor = findViewById(R.id.painting_author);
        TextView paintingDescription = findViewById(R.id.detail_image);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("Data")) {
           painting = (Painting) intent.getSerializableExtra("Data");

            Glide.with(this).load(painting.getImageUri()).into(paintingImage);
            paintingTitle.setText(painting.getName());
            paintingLocation.setText(painting.getLocation() + ", " + painting.getDate());
            paintingMuseum.setText(painting.getMuseum());
            paintingAuthor.setText(painting.getAuthor());
            paintingDescription.setText(painting.getDescription());
        }
    }
}