package com.epam.ot.entity;

import java.util.UUID;

public class User {
    private long id;
    private UUID uuid;
    private String login;
    private String email;
    private Role role;
    private String password;

//    private List<Product> shopcart;

    public User(String login, String email, Role role, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;

//        shopcart = new ArrayList<>();
    }

    /*public List<Product> getShopcart() {
        return shopcart;
    }

    public Product getProduct(int productNumber) {
        return shopcart.get(productNumber);
    }

    //TODO - make productsCount++ - if added product already in cart
    public void addProduct(Product product) {
        if (product != null) {
            shopcart.add(product);
        }
    }

    public void removeProduct(Product product) {
        shopcart.remove(product);
    }

    public void removeProduct(String productUuid) {
        for (Product product : shopcart) {
            if (product.getUuid().equals(UUID.fromString(productUuid))) {
                shopcart.remove(product);
                break;
            }
        }
    }*/

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}