package com.brandonhxrr.artlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import com.brandonhxrr.artlife.ui.Home;
import android.Manifest;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class Main extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 100;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.menu_camera){

            }else if(itemId == R.id.menu_navigate){
                if (!checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST);
                } else {
                    bottomNavigationView.setSelectedItemId(R.id.menu_home);
                    openMaps();
                }
                bottomNavigationView.setSelectedItemId(R.id.menu_home);
                return true;
            }else if(itemId == R.id.menu_favorites){

            }else if(itemId == R.id.menu_home){

            }
           return true;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, Home.newInstance())
                    .commitNow();
        }


    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(this, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openMaps();
            } else {
                // Permiso denegado, muestra un mensaje o toma alguna acción alternativa
            }
        }
    }

    private void openMaps() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Verificar si la ubicación está activa
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            double latitude = 0.0;
            double longitude = 0.0;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

            String searchQuery = "galería de arte";
            String locationQuery = String.format(Locale.getDefault(), "%f,%f", latitude, longitude);
            String uriString = String.format(Locale.getDefault(), "geo:%s?q=%s", locationQuery, Uri.encode(searchQuery));

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No se encontró Google Maps en el dispositivo", Toast.LENGTH_SHORT).show();
            }
        } else {
            // La ubicación no está activa, solicitar al usuario que la active
            Toast.makeText(this, "Por favor, activa la ubicación para usar esta función", Toast.LENGTH_SHORT).show();
            Intent locationSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(locationSettingsIntent);
        }
    }




}