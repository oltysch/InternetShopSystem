package com.epam.ot.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductBlock {
    private String name;
    private String shortDescription;
    private UUID uuid;
    private String productType;
    private Double price;
    private List<Characteristic> characteristics;
    private String fullDescription;

    public ProductBlock() {
        characteristics = new ArrayList<>();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void addCharacteristics(String name, String value) {
        characteristics.add(new Characteristic(name, value));
    }
}
