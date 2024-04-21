package com.example.sneakrapp;

import com.example.sneakrapp.models.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sneakrapp.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;
    private SharedPreferences sharedPreferences;
    private static final String CART_PREFS = "CartPrefs";
    private static final String CART_KEY = "CartItems";
    private Gson gson;

    private CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        gson = new Gson();
        cartItems = loadCartItems();
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    private List<Product> loadCartItems() {
        String json = sharedPreferences.getString(CART_KEY, "");
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type) != null ? gson.fromJson(json, type) : new ArrayList<>();
    }

    private void saveCartItems() {
        String json = gson.toJson(cartItems);
        sharedPreferences.edit().putString(CART_KEY, json).apply();
    }

    public synchronized void addProduct(Product product) {
        for (Product p : cartItems) {
            if (p.getName().equals(product.getName())) {
                return; // Product already in the cart, so don't add it again
            }
        }
        cartItems.add(product);
        saveCartItems(); // Save the updated list to SharedPreferences
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void removeProduct(Product product) {
        Iterator<Product> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            Product item = iterator.next();
            if (item.getName().equals(product.getName())) {
                iterator.remove();
                saveCartItems(); // Ensure changes are saved
                break;
            }
        }
    }

}