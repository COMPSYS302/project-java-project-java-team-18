package com.example.sneakrapp.helpers;

import com.example.sneakrapp.models.Product;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataProvider {
    public static Map<Integer, String> generateShoeProducts() {
        Map<Integer, String> words =
                new LinkedHashMap<Integer, String>();
        words.put(1, "Shoe1");
        words.put(2, "Shoe2");
        words.put(3, "Shoe3");
        words.put(4, "Shoe4");
        words.put(5, "Shoe5");
        words.put(6, "Shoe6");
        words.put(7, "Shoe7");
        words.put(8, "Shoe8");
        words.put(9, "Shoe9");
        return words;
    }

    public static List<Product> getProducts() {
        List<Product> productsList = new LinkedList<Product>();
        Map<Integer, String> words = generateShoeProducts();
        for (Integer key : words.keySet()) {
            int digit = key;
            //String maoriTranslation = words.get(key);
            String text = words.get(key);
            String icon = "icon" + String.valueOf(key);
            String heart = "heart" + String.valueOf(key);
            Product n = new Product(digit, icon, text, heart);
            productsList.add(n);
        }
        return productsList;
    }

}