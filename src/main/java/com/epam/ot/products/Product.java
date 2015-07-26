/**
 * Created by Admin on 21.05.2015.
 * The Basic class from Product
 */

package com.epam.ot.products;

public abstract class Product {
    private long id;
    private String name;
    private Double price;

    public Product(long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
