package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.brandonhxrr.artlife.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import de.hdodenhof.circleimageview.CircleImageView;

public class Logout extends AppCompatActivity {

    CircleImageView profileImage;
    TextView userName;
    TextView userEmail;
    Button btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        profileImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        btnLogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();

        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(profileImage);
        userName.setText(mAuth.getCurrentUser().getDisplayName());
        userEmail.setText(mAuth.getCurrentUser().getEmail());

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent startLogin = new Intent(Logout.this, Login.class);
            startLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startLogin);
            finish();
        });

    }
}