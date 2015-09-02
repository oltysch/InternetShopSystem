package com.epam.ot.entity;

import java.io.Serializable;
import java.util.UUID;

public class ShoppingCartItem implements Serializable {
    private static final long serialVersionUID = -3723655422050746847L;
    private int count;
    private String productUuid;

    public ShoppingCartItem(UUID productUuid) {
        this.productUuid = productUuid.toString();
        count = 1;
    }

    public ShoppingCartItem(UUID productUuid, int count) {
        this.count = count;
        this.productUuid = productUuid.toString();
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
        return UUID.fromString(productUuid);
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid.toString();
    }
}
