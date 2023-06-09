package com.brandonhxrr.artlife.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.ui.intro.IntroFragment;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends Fragment {

    MaterialTextView textHello;
    CircleImageView profileImage;
    FirebaseUser currentUser;

    public Home() {}
    public static Home newInstance() {
        return new Home();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);

       textHello = view.findViewById(R.id.txt_hello);
       profileImage = view.findViewById(R.id.profile_image);

       textHello.setText("Hola, " + currentUser.getDisplayName());
       if(currentUser.getPhotoUrl() != null){
           profileImage.setImageURI(currentUser.getPhotoUrl());
       }

       return view;
    }
}