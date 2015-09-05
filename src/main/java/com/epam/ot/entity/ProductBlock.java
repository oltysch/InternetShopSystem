package com.epam.ot.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductBlock {
    private String name;
    private String description;
    private UUID uuid;
    private String productType;
    private Double price;
    private List<Characteristic> characteristics;

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
        String result;
        if (description == null || description.equals("")) {
            result = "";
        } else if (description.length() > 101) {
            result = description.substring(0, 100) + "...";
        } else {
            result = description;
        }
        return result;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFullDescription() {
        String result;
        if (description == null) {
            result = "";
        } else {
            result = description;
        }
        return result;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void addCharacteristics(String name, String value) {
        characteristics.add(new Characteristic(name, value));
    }
}
