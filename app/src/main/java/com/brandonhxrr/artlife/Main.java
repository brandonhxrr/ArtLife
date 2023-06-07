package com.brandonhxrr.artlife;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.brandonhxrr.artlife.ui.Home;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, Home.newInstance())
                    .commitNow();
        }
    }
}