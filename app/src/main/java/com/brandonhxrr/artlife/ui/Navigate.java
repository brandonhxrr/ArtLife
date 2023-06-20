package com.brandonhxrr.artlife.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;
import com.brandonhxrr.artlife.R;

public class Navigate extends Fragment {

    WebView webView;

    public Navigate() {
    }

    public static Navigate newInstance() {
        Navigate fragment = new Navigate();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_navigate, container, false);

        webView = v.findViewById(R.id.webView);

        openMaps();
        return v;
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void openMaps() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            double latitude = 0.0;
            double longitude = 0.0;

            if (ActivityCompat.checkSelfPermission(webView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(webView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

            String query = "https://www.google.com/maps/search/museo%20%de%20arte/"+latitude+","+longitude;

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(query);


        } else {
            Toast.makeText(webView.getContext(), "Por favor, activa la ubicación para usar esta función", Toast.LENGTH_SHORT).show();
            Intent locationSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(locationSettingsIntent);
        }
    }
}