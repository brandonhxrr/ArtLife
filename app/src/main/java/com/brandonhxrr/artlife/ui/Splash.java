package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.ui.intro.Intro;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(2000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent startIntro = new Intent(Splash.this, Intro.class);
                startActivity(startIntro);
                Splash.this.finish();
            }
        }.start();
    }
}