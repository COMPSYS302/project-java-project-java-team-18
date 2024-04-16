package com.example.sneakrapp;

import com.example.sneakrapp.models.Product;
import java.util.ArrayList;
import java.util.List;

public class WishlistManager {

    private static WishlistManager instance;
    private List<Product> wishlistItems;

    private WishlistManager() {
        wishlistItems = new ArrayList<>();
    }

    public static synchronized WishlistManager getInstance() {
        if (instance == null) {
            instance = new WishlistManager();
        }
        return instance;
    }

    public synchronized void addProduct(Product product) {
        if (product != null && !wishlistItems.contains(product)) {
            wishlistItems.add(product);
        }
    }

    public List<Product> getWishlistItems() {
        return new ArrayList<>(wishlistItems);
    }

    public void removeProduct(Product product) {
        wishlistItems.remove(product);
    }
}
