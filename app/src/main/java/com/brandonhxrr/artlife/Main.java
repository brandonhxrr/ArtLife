package com.brandonhxrr.artlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.brandonhxrr.artlife.data.Painting.Painting;
import com.brandonhxrr.artlife.data.Painting.PaintingData;
import com.brandonhxrr.artlife.ui.Blogs;
import com.brandonhxrr.artlife.ui.Favorites;
import com.brandonhxrr.artlife.ui.Home;
import android.Manifest;
import android.os.Environment;
import android.provider.MediaStore;
import com.brandonhxrr.artlife.ui.Navigate;
import com.brandonhxrr.artlife.ui.PaintingDetail;
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
    int i = 0;

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
                    requestPermission();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, Navigate.newInstance())
                            .commitNow();
                }
            } else if (itemId == R.id.menu_favorites) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, Favorites.newInstance())
                        .commitNow();
            } else if (itemId == R.id.menu_home) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, Home.newInstance())
                        .commitNow();
            }else if (itemId == R.id.menu_blog){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, Blogs.newInstance())
                        .commitNow();
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Main.MY_PERMISSIONS_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            if (grantResults.length > 0) {
                boolean cameraPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean storagePermissionGranted = grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (cameraPermissionGranted && storagePermissionGranted) {
                   openCamera();
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

    @SuppressLint("QueryPermissionsNeeded")
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
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Abrir Google Lens con la imagen capturada
                //openGoogleLens(imageUri);

                PaintingData pdata = new PaintingData();

                Intent intent = new Intent(Main.this, PaintingDetail.class);
                intent.putExtra("Data", pdata.getData()[i%pdata.getData().length]);
                startActivity(intent);
            } else if (requestCode == REQUEST_IMAGE_GALLERY && data != null && data.getData() != null) {
                // Abrir Google Lens con la imagen seleccionada de la galería
                openGoogleLens(data.getData());
            }
            i++;
        }
    }

    private void openGoogleLens(Uri imageUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/searchbyimage?image_url=" + imageUri.toString()));
        startActivity(intent);
    }
}
