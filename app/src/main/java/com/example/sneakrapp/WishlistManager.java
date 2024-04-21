package com.example.sneakrapp;

import com.example.sneakrapp.models.Product;
import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<Product> iterator = wishlistItems.iterator();
        while (iterator.hasNext()) {
            Product item = iterator.next();
            if (item.getName().equals(product.getName())) {
                iterator.remove();
                break; // Stop the loop after removing the item
            }
        }
    }


    public boolean isProductInWishlist(Product product) {
        return wishlistItems.contains(product);
    }
}
