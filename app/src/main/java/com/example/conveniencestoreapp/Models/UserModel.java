package com.example.conveniencestoreapp.Models;

public class UserModel {
    String name;
    String email;
    String password;

    String phonenumber;
    String address;

    public UserModel(String name, String email, String password, String phonenumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.address = address;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
