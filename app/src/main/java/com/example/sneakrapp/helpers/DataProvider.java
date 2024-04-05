package com.example.sneakrapp.helpers;

import java.util.LinkedHashMap;
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


}