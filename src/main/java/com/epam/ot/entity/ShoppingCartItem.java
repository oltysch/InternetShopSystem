package com.epam.ot.entity;

import java.util.UUID;

public class ShoppingCartItem {
    private int count;
    private UUID productUuid;

    public ShoppingCartItem(UUID productUuid) {
        this.productUuid = productUuid;
        count = 1;
    }

    public ShoppingCartItem(UUID productUuid, int count) {
        this.count = count;
        this.productUuid = productUuid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(int count) {
        this.count += count;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }
}
