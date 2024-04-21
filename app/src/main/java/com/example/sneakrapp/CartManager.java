package com.example.sneakrapp;

import com.example.sneakrapp.models.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public synchronized void addProduct(Product product) {
        if (product != null && !cartItems.contains(product)) {
            cartItems.add(product);
        }
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void removeProduct(Product product) {
        Iterator<Product> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            Product item = iterator.next();
            if (item.getName().equals(product.getName())) {
                iterator.remove();
                break; // Stop the loop after removing the item
            }
        }
    }

    public boolean isProductInCart(Product product) {
        return cartItems.contains(product);
    }
}
