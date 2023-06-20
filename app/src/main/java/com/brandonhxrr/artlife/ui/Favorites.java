package com.brandonhxrr.artlife.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.BlogData;
import com.brandonhxrr.artlife.data.LargeBlog.LargeBlogAdapter;
import com.brandonhxrr.artlife.data.Painting.PaintingAdapter;
import com.brandonhxrr.artlife.data.Painting.PaintingData;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        return view;
    }
}