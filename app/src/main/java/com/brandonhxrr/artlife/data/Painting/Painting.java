package com.brandonhxrr.artlife.data.Painting;

public class Painting {
    int id;
    String name;
    String author;
    String location;
    String date;
    String museum;
    String technique;
    String urlMuseum;
    String imageUri;
    String description;

    public Painting(int id, String name, String author, String location, String date, String museum, String technique, String urlMuseum, String imageUri, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.location = location;
        this.date = date;
        this.museum = museum;
        this.technique = technique;
        this.urlMuseum = urlMuseum;
        this.imageUri = imageUri;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public String getUrlMuseum() {
        return urlMuseum;
    }

    public void setUrlMuseum(String urlMuseum) {
        this.urlMuseum = urlMuseum;
    }

    public String getMuseum() {
        return museum;
    }

    public void setMuseum(String museum) {
        this.museum = museum;
    }
}
