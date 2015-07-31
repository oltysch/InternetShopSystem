package com.epam.ot.users;

import com.epam.ot.products.Product;

import java.util.List;

public class User extends AbstractUser {
    //TODO remake user class
    private List<Product> shopcart;

    public User(String login, String email, String password, String name) {
        super(login, email, password, name);
    }
}