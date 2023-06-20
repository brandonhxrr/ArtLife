package com.brandonhxrr.artlife.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.brandonhxrr.artlife.data.Blog.BlogAdapter;
import com.brandonhxrr.artlife.data.Blog.BlogData;
import com.brandonhxrr.artlife.data.LargeBlog.LargeBlogAdapter;
import com.brandonhxrr.artlife.data.Painting.Painting;
import com.brandonhxrr.artlife.data.Painting.PaintingAdapter;
import com.brandonhxrr.artlife.data.Painting.PaintingData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Favorites extends Fragment {

    RecyclerView recyclerView;

    public Favorites() {}

    public static Favorites newInstance() {
        return new Favorites();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference favoritesRef = db.collection("users").document(mAuth.getCurrentUser().getUid()).collection("favorites");

        favoritesRef.addSnapshotListener((queryDocumentSnapshots, error) -> {
            if (error != null) {
                return;
            }

            List<String> favoritePaintingIds = new ArrayList<>(); // Reiniciar la lista en cada actualización

            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                String paintingId = documentSnapshot.getId();
                favoritePaintingIds.add(paintingId);
            }

            if (!favoritePaintingIds.isEmpty()) {
                getFavoritePaintings(favoritePaintingIds, view);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void getFavoritePaintings(List<String> favoritePaintingIds, View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("paintings")
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) {
                        return;
                    }

                    List<Painting> paintingList = new ArrayList<>();

                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Painting paintingItem = documentSnapshot.toObject(Painting.class);
                        if(favoritePaintingIds.contains(String.valueOf(paintingItem.getId())))  paintingList.add(paintingItem);
                    }
                    recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2, GridLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new PaintingAdapter(paintingList));
                });
    }

}