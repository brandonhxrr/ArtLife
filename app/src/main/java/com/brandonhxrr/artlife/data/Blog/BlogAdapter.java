package com.brandonhxrr.artlife.data.Blog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brandonhxrr.artlife.R;
import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder> {

    private Blog blogs[];

    public BlogAdapter(Blog[] blogs) {
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog, parent, false);
        return new BlogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Glide.with(holder.itemView).load(blogs[position].imageUri).into(holder.getBlogImage());
        holder.getBlogTitle().setText(blogs[position].title);
    }

    @Override
    public int getItemCount() {
        return blogs.length;
    }
}
