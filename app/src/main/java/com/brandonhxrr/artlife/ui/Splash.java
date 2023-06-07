package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.brandonhxrr.artlife.Main;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.ui.intro.Intro;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    Intent startIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        startIntro = ( currentUser != null) ?
                new Intent(Splash.this, Main.class) :
                new Intent(Splash.this, Intro.class);

        new CountDownTimer(2000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(startIntro);
                Splash.this.finish();
            }
        }.start();
    }
}