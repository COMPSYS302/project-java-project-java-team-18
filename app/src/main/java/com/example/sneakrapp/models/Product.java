package com.example.sneakrapp.models;

public class Product {

    int digit;
    String iconFileName, heartname;

    public int getDigit() {
        return digit;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public String getHeartname() {
        return heartname;
    }

    public Product(int digit, String iconFileName, String heartName) {
        this.digit = digit;
        this.iconFileName = iconFileName;
        this.heartname = heartName;
    }
}
