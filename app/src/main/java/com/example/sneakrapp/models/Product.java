package com.example.sneakrapp.models;

public class Product {

    private int id;
    private String icon;
    private String name;
    private String description;
    private double price;
    private String heart;

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

    public double getPrice() {return price;}

    public String getHeart() {
        return heart;
    }

    public Product(int id, String icon, String name, String description, double price) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.price = price;
        //this.heart = heart;
    }
}
