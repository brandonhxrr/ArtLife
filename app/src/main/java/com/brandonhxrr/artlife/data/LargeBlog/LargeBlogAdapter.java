package com.brandonhxrr.artlife.data.LargeBlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.bumptech.glide.Glide;

import java.util.List;

public class LargeBlogAdapter extends RecyclerView.Adapter<LargeBlogViewHolder> {

    List<Blog> blogs;

    public LargeBlogAdapter(List<Blog> blogs) {
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
        Glide.with(holder.itemView).load(blogs.get(position).getImageUri()).into(holder.getBlogImage());
        holder.getBlogTitle().setText(blogs.get(position).getTitle());
        holder.getBlogAuthor().setText(blogs.get(position).getAuthor());
        holder.getBlogDate().setText(blogs.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }
}
