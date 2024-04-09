package com.example.sneakrapp.helpers;

import com.example.sneakrapp.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataProvider {
//    public static Map<Integer, String> generateShoeProducts() {
//        Map<Integer, String> words =
//                new LinkedHashMap<Integer, String>();
//        words.put(1, "Shoe1");
//        words.put(2, "Shoe2");
//        words.put(3, "Shoe3");
//        words.put(4, "Shoe4");
//        words.put(5, "Shoe5");
//        words.put(6, "Shoe6");
//        words.put(7, "Shoe7");
//        words.put(8, "Shoe8");
//        words.put(9, "Shoe9");
//        return words;
//    }


    public static Map<Integer, Map<String, Object>> generateShoeProducts() {
        Map<Integer, Map<String, Object>> products = new LinkedHashMap<>();
        String[][] shoeData = {
                {"x Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer Shoes", "Description for Designer Shoes", "300.00"},
                {"Running Shoes", "Description for Running Shoes", "100.00"},
                {"Casual Shoes", "Description for Casual Shoes", "80.00"},
                {"Sports Shoes", "Description for Sports Shoes", "120.00"},
                {"Formal Shoes", "Description for Formal Shoes", "150.00"},
                {"Sneakers", "Description for Sneakers", "90.00"},
                {"Boots", "Description for Boots", "200.00"},
                {"Sandals", "Description for Sandals", "50.00"},
                {"Slippers", "Description for Slippers", "30.00"}
        };

        for (int i = 0; i < shoeData.length; i++) {
            Map<String, Object> shoeProperties = new LinkedHashMap<>();
            shoeProperties.put("name", shoeData[i][0]);
            shoeProperties.put("description", shoeData[i][1]);
            shoeProperties.put("price", Double.parseDouble(shoeData[i][2]));
            products.put(i + 1, shoeProperties);
        }
        return products;
    }

    public static List<Product> getProducts() {
        List<Product> productsList = new LinkedList<>();
        Map<Integer, Map<String, Object>> products = generateShoeProducts();

        for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
            int id = entry.getKey();
            Map<String, Object> details = entry.getValue();
            String name = (String) details.get("name");
            String description = (String) details.get("description");
            double price = (double) details.get("price");
            String icon = "firstpic" + id;
            String heart = "heart" + id;

            Product product = new Product(id, icon, name, description, price);
            productsList.add(product);
        }
        return productsList;
    }

}