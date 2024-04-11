package com.example.sneakrapp.models;

public class Product {

    private int id;
    private String icon;
    private String name;
    private String description;
    private String price;
    private String heart;

    private String size;
    private int quantity;

    public int getId() {
        return id;
    }

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

    public Product(int id, String icon, String name, String description, String price) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.price = price;
        //this.heart = heart;
    }
}
