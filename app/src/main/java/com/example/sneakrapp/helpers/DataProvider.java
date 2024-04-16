package com.example.sneakrapp.helpers;

import android.util.Log;

import com.example.sneakrapp.models.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataProvider {

    public static Map<Integer, Map<String, Object>> generateShoeProducts() {
        Map<Integer, Map<String, Object>> products = new LinkedHashMap<>();
        String[][] shoeData = {

                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Gucci Butterfly Sneaker", "Luxury-High End Gucci Sneakers, made hand crafted in Italy.", "250.00"},
                {"Active-Wear", "Rarke", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Louis Vuitton Slides", "Limited-Pillow Edition Slides, Imported directly from France ", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Supreme x Nike Air Force 1", "Exclusive Supreme Collaboration with Nike to deliver limited-edition skateboarding AF1", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Lacoste Graduate Sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Designer", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Active-Wear", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},
                {"Newest-Collections", "Travis Scott Air Jordan 4 Retro sneakers", "Description for Travis Scott Air Jordan", "250.00"},        };

        for (int i = 0; i < shoeData.length; i++) {
            Map<String, Object> shoeProperties = new LinkedHashMap<>();
            shoeProperties.put("category", shoeData[i][0]);
            shoeProperties.put("name", shoeData[i][1]);
            shoeProperties.put("description", shoeData[i][2]);
            shoeProperties.put("price", Double.parseDouble(shoeData[i][3]));
            products.put(i + 1, shoeProperties);
        }
        return products;
    }

    public static List<Product> getProducts(String category) {
        List<Product> productsList = new LinkedList<>();
        Map<Integer, Map<String, Object>> products = generateShoeProducts();

        // Debug log to check the category being passed
        Log.d("DataProvider", "Category: " + category);

        for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
            Map<String, Object> details = entry.getValue();
            String productCategory = (String) details.get("category");

            // Debug log to check the category of each product
            Log.d("DataProvider", "Product Category: " + productCategory);

            if (category.equals(productCategory)) {
                int id = entry.getKey();
                String name = (String) details.get("name");
                String description = (String) details.get("description");
                double price = (double) details.get("price");
                String formattedPrice = String.format("$%.2f", price);
                String icon = "firstpic" + id;
                String heart = "heart" + id;

                Product product = new Product(id, icon, name, description, formattedPrice);
                productsList.add(product);
            }
        }
        // Debug log to check the size of the product list
        Log.d("DataProvider", "Product List Size: " + productsList.size());

        // Log the products retrieved for this category
        for (Product product : productsList) {
            Log.d("DataProvider", "Product: " + product.getName());
        }

        return productsList;
    }

}
