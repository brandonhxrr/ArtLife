package com.brandonhxrr.artlife.data.LargeBlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.bumptech.glide.Glide;

public class LargeBlogAdapter extends RecyclerView.Adapter<LargeBlogViewHolder> {

    private Blog blogs[];

    public LargeBlogAdapter(Blog[] blogs) {
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public LargeBlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.large_blog, parent, false);
        return new LargeBlogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LargeBlogViewHolder holder, int position) {
        Glide.with(holder.itemView).load(blogs[position].getImageUri()).into(holder.getBlogImage());
        holder.getBlogTitle().setText(blogs[position].getTitle());
        holder.getBlogAuthor().setText(blogs[position].getAuthor());
        holder.getBlogDate().setText(blogs[position].getDate());
    }

    @Override
    public int getItemCount() {
        return blogs.length;
    }
}
