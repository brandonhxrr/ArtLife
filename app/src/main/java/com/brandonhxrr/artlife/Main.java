package com.brandonhxrr.artlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

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
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;

    private Uri imageUri;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_camera) {
                checkPermissionsAndOpenCamera();
            } else if (itemId == R.id.menu_navigate) {
                if (!checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST);
                } else {
                    openMaps();
                }
            } else if (itemId == R.id.menu_favorites) {

            } else if (itemId == R.id.menu_home) {

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
            if (grantResults.length > 0) {
                boolean cameraPermissionGranted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean storagePermissionGranted = grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (cameraPermissionGranted && storagePermissionGranted) {
                    openMaps();
                } else {
                    // Al menos uno de los permisos fue denegado, muestra un mensaje o toma alguna acción alternativa
                }
            }
        }
    }

    private void checkPermissionsAndOpenCamera() {
        boolean cameraPermission = checkPermission(Manifest.permission.CAMERA);
        boolean storagePermission = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (!cameraPermission || !storagePermission) {
            List<String> permissionsNeeded = new ArrayList<>();

            if (!cameraPermission) {
                permissionsNeeded.add(Manifest.permission.CAMERA);
            }

            if (!storagePermission) {
                permissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            ActivityCompat.requestPermissions(this, permissionsNeeded.toArray(new String[0]), MY_PERMISSIONS_REQUEST);
        } else {
            openCamera();
        }
    }

    private void openMaps() {
        // Tu código para abrir Google Maps aquí
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(this, "com.brandonhxrr.artlife.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        return imageFile;
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Abrir Google Lens con la imagen capturada
                openGoogleLens(imageUri);
            } else if (requestCode == REQUEST_IMAGE_GALLERY && data != null && data.getData() != null) {
                // Abrir Google Lens con la imagen seleccionada de la galería
                openGoogleLens(data.getData());
            }
        }
    }

    private void openGoogleLens(Uri imageUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/searchbyimage?image_url=" + imageUri.toString()));
        startActivity(intent);
    }
}
