package com.brandonhxrr.artlife.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.data.Blog.Blog;
import com.brandonhxrr.artlife.data.Painting.Painting;
import com.bumptech.glide.Glide;

public class BlogDetail extends AppCompatActivity {

    Blog blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        ImageView blogImage = findViewById(R.id.blog_image);
        TextView blogTitle = findViewById(R.id.blog_title);
        TextView blogAuthor = findViewById(R.id.blog_author);
        TextView blogDate = findViewById(R.id.blog_date);
        TextView blogContent = findViewById(R.id.blog_content);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("Data")) {
            blog = (Blog) intent.getSerializableExtra("Data");

            Glide.with(this).load(blog.getImageUri()).into(blogImage);
            blogTitle.setText(blog.getTitle());
            blogDate.setText(blog.getDate());
            blogAuthor.setText(blog.getAuthor());
            blogContent.setText(blog.getContent());
        }

        blogImage.setOnClickListener(v -> {
            Intent startDetails = new Intent(BlogDetail.this, FullScreen.class);
            startDetails.putExtra("urlImage", blog.getImageUri());
           startActivity(startDetails);
        });


    }
}