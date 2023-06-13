package com.brandonhxrr.artlife.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.BlogAdapter;
import com.brandonhxrr.artlife.data.Blog.BlogData;

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

        BlogData data = new BlogData();

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new BlogAdapter(data.getData()));
        return view;
    }
}