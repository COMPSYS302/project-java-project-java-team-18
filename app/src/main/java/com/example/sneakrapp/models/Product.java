package com.example.sneakrapp.models;

import java.util.List;

public class Product {

    private int id;
    private String icon;
    private String name;
    private String description;
    private String price;
    private String heart;

    private String size;
    private int quantity;
    //private String imageUrls1, imageUrls2, imageUrls3, imageUrls4;
    private List<String> imageUrls;  // List to hold multiple image URLs


//    public int getId() {
//        return id;
//    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {return price;}

    public String getHeart() {
        return heart;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

//    public String getImageUrls() {
//        return imageUrls1;
//    }
//
//    public void setImageUrls(String imageUrls) {
//        this.imageUrls1 = imageUrls;
//    }

    public List<String> getImageUrls() { return imageUrls; }


    public Product( String name, String description, String price, List<String> imageUrls) {
        //this.id = id;
        //this.icon = icon;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrls = imageUrls;
    }
}