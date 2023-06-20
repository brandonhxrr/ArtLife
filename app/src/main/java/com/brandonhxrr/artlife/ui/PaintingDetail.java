package com.brandonhxrr.artlife.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Painting.Painting;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class PaintingDetail extends AppCompatActivity {

    Painting painting;
    Boolean isFavorite = false;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        ImageView paintingImage = findViewById(R.id.painting_image);
        TextView paintingTechnique = findViewById(R.id.painting_technique);
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
            paintingTechnique.setText(painting.getTechnique());
            paintingDescription.setText(painting.getDescription());

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference userRef = db.collection("users").document(mAuth.getCurrentUser().getUid());
            DocumentReference paintingRef = userRef.collection("favorites").document(String.valueOf(painting.getId()));

            paintingRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        isFavorite = true;
                        saveButton.setImageResource(R.drawable.saved_icon);
                    } else {
                        isFavorite = false;
                        saveButton.setImageResource(R.drawable.not_saved_icon);
                    }
                } else {
                }
            });


            openButton.setOnClickListener(v -> {

                Intent startMaps = new Intent(Intent.ACTION_VIEW, Uri.parse(painting.getUrlMuseum()));

                if (startMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(startMaps);
                } else {
                    Toast.makeText(getApplicationContext(), "No se puede abrir Google Maps", Toast.LENGTH_SHORT).show();
                }

            });

            saveButton.setOnClickListener(v -> {
                if (isFavorite) {
                    paintingRef.delete()
                            .addOnSuccessListener(aVoid -> saveButton.setImageResource(R.drawable.not_saved_icon))
                            .addOnFailureListener(e -> {
                            });
                } else {
                    paintingRef.set(new HashMap<>())
                            .addOnSuccessListener(aVoid -> saveButton.setImageResource(R.drawable.saved_icon))
                            .addOnFailureListener(e -> {
                            });
                }
                isFavorite = !isFavorite;
            });
        }
    }
}