package com.epam.ot.users;

import com.epam.ot.products.Product;

import java.util.ArrayList;
import java.util.List;

public class User extends AbstractUser {
    //TODO remake user class
    private List<Product> shopcart;

    public User(String login, String email, String password) {
        super(login, email, password);
        shopcart = new ArrayList<>();
    }

    public List<Product> getShopcart() {
        return shopcart;
    }

    public Product getProduct(int productNumber) {
        return shopcart.get(productNumber);
    }

    //TODO - make count++ - if added product already in cart
    public void addProduct(Product product) {
        if (product != null) {
            shopcart.add(product);
        }
    }

    public void removeProduct(Product product) {
        shopcart.remove(product);
    }

    public void removeProduct(int productId) {
        for (Product product : shopcart) {
            if (product.getId() == productId) {
                shopcart.remove(product);
                break;
            }
        }
    }
}