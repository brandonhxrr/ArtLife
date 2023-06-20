package com.brandonhxrr.artlife.data.Blog;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.ui.BlogDetail;
import com.brandonhxrr.artlife.ui.PaintingDetail;
import com.bumptech.glide.Glide;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder> {

    List<Blog> blogs;

    public BlogAdapter(List<Blog> blogs) {
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
        Glide.with(holder.itemView).load(blogs.get(position).imageUri).into(holder.getBlogImage());
        holder.getBlogTitle().setText(blogs.get(position).title);
        holder.getCard().setOnClickListener(v -> {
            Intent startDetails = new Intent(holder.itemView.getContext(), BlogDetail.class);
            startDetails.putExtra("Data", blogs.get(position));
            holder.itemView.getContext().startActivity(startDetails);
        });
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }
}
