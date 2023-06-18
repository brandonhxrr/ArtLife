package com.brandonhxrr.artlife.ui;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.BlogAdapter;
import com.brandonhxrr.artlife.data.Blog.BlogData;
import com.brandonhxrr.artlife.data.Painting.PaintingAdapter;
import com.brandonhxrr.artlife.data.Painting.PaintingData;
import com.brandonhxrr.artlife.ui.intro.IntroFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends Fragment {

    MaterialTextView textHello;
    CircleImageView profileImage;
    RecyclerView recyclerView, recentsRecyclerView;
    FirebaseUser currentUser;
    AppCompatImageView map;

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
       recyclerView = view.findViewById(R.id.recycler_view);
       recentsRecyclerView = view.findViewById(R.id.recent_recycler_view);
       map = view.findViewById(R.id.map);

       BlogData data = new BlogData();

       recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
       recyclerView.setAdapter(new BlogAdapter(data.getData()));

        PaintingData paintingData = new PaintingData();

        recentsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),  LinearLayoutManager.HORIZONTAL, false));
        recentsRecyclerView.setAdapter(new PaintingAdapter(paintingData.getData()));

       textHello.setText("Hola, " + currentUser.getDisplayName().split(" ")[0]);
       if(currentUser.getPhotoUrl() != null){
           Glide.with(view).load(currentUser.getPhotoUrl()).into(profileImage);
       }

       map.setOnClickListener(v -> {

       });

       return view;
    }
}