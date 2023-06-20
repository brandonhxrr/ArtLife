package com.brandonhxrr.artlife.data.LargeBlog;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brandonhxrr.artlife.R;
import com.google.android.material.textview.MaterialTextView;

public class LargeBlogViewHolder extends RecyclerView.ViewHolder {


    private MaterialTextView blogTitle;
    private MaterialTextView blogAuthor;
    private MaterialTextView blogDate;
    private ImageView blogImage;
    private LinearLayout container;

    public LargeBlogViewHolder(@NonNull View itemView) {
        super(itemView);

        blogTitle = itemView.findViewById(R.id.blog_title);
        blogImage = itemView.findViewById(R.id.blog_image);
        blogAuthor = itemView.findViewById(R.id.blog_author);
        blogDate = itemView.findViewById(R.id.blog_date);
        container = itemView.findViewById(R.id.container);
    }

    public ImageView getBlogImage() {
        return blogImage;
    }

    public MaterialTextView getBlogTitle() {
        return blogTitle;
    }

    public MaterialTextView getBlogAuthor() {
        return blogAuthor;
    }

    public MaterialTextView getBlogDate() {
        return blogDate;
    }

    public LinearLayout getContainer() {
        return container;
    }
}
