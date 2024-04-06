package com.example.sneakrapp.models;

public class Product {

    int digit;
    String iconFilename, heartName, text;

    public int getDigit() {
        return digit;
    }

    public String getIconFileName() {
        return iconFilename;
    }

    public String getText() {
        return text;
    }

    public String getHeartname() {
        return heartName;
    }

    public Product(int digit, String iconFileName, String text, String heartName) {
        this.digit = digit;
        this.text = text;
        this.iconFilename = iconFileName;
        this.heartName = heartName;
    }
}
