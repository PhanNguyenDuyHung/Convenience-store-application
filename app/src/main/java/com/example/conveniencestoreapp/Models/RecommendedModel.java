package com.example.conveniencestoreapp.Models;

public class RecommendedModel {
    String name;
    String img_url;
    String description;
    String rating;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String img_url, String description, String rating) {
        this.name = name;
        this.img_url = img_url;
        this.description = description;
        this.rating = rating;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
