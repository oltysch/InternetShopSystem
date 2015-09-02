/**
 * Created by Admin on 21.05.2015.
 * The Basic class from Product
 */

package com.epam.ot.entity;

import java.util.UUID;

public abstract class Product {
    private long id;
    private UUID uuid;
    private String name;
    private Double price;
    private String description;

    public Product(String name, Double price) {
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

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract ProductBlock toBlock();
}
