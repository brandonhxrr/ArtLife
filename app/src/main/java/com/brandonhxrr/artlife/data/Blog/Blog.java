package com.brandonhxrr.artlife.data.Blog;

import java.io.Serializable;

public class Blog implements Serializable {
    public String imageUri;
    public String title;
    public String author;
    public String date;
    public String content;

    public  Blog() {

    }

    public Blog(String imageUri, String title, String author, String date, String content) {
        this.imageUri = imageUri;
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
