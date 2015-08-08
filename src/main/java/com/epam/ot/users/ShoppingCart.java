package com.epam.ot.users;

import com.epam.ot.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int numberOfItems;
    private double total;
    List<Product> products = new ArrayList<>();

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void clearCart() {
        products.removeAll(products);
    }
}
