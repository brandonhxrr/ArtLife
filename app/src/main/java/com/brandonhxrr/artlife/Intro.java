package com.brandonhxrr.artlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.brandonhxrr.artlife.ui.main.IntroFragment;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, IntroFragment.newInstance())
                    .commitNow();
        }
    }
}