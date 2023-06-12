package com.brandonhxrr.artlife.data.Blog;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brandonhxrr.artlife.R;
import com.google.android.material.textview.MaterialTextView;

public class BlogViewHolder extends RecyclerView.ViewHolder {


    private MaterialTextView blogTitle;
    private ImageView blogImage;

    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);

        blogTitle = itemView.findViewById(R.id.blog_title);
        blogImage = itemView.findViewById(R.id.blog_image);
    }

    public ImageView getBlogImage() {
        return blogImage;
    }

    public MaterialTextView getBlogTitle() {
        return blogTitle;
    }
}
