package com.brandonhxrr.artlife.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.brandonhxrr.artlife.data.Blog.BlogAdapter;
import com.brandonhxrr.artlife.data.Blog.BlogData;
import com.brandonhxrr.artlife.data.LargeBlog.LargeBlogAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Blogs extends Fragment {

    RecyclerView recyclerView;

    public Blogs() {}

    public static Blogs newInstance() {
        return new Blogs();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blogs, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("blogs")
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) {
                        // Manejar el error si ocurre
                        return;
                    }

                    List<Blog> blogItemList = new ArrayList<>();

                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Blog blogItem = documentSnapshot.toObject(Blog.class);
                        blogItemList.add(blogItem);
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new LargeBlogAdapter(blogItemList));
                });
        return view;
    }
}