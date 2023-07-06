package com.example.conveniencestoreapp.Models;

public class NavCategoryDetailModel {
    String name;
    String type;
    String img_url;
    String price;

    public NavCategoryDetailModel() {
    }

    public NavCategoryDetailModel(String name, String type, String img_url, String price) {
        this.name = name;
        this.type = type;
        this.img_url = img_url;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
