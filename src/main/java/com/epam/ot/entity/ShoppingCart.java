package com.epam.ot.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCart {
    //    list of items in ShoppingCart
    private List<ShoppingCartItem> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public List<ShoppingCartItem> getProducts() {
        return new ArrayList<>(products);
    }

    /**
     * @return total products count.
     */
    public int getProductsCount() {
        int res = 0;
        for (ShoppingCartItem item : products) {
            res += item.getCount();
        }
        return res;
    }

    /**
     * @param productUuid insert product UUID in list, or increments count by 1
     */
    public void addProduct(UUID productUuid) {
        addProduct(productUuid, 1);
    }


    /**
     * @param productUuid
     * @param count       insert or increments product UUID in list by count
     */
    public void addProduct(UUID productUuid, int count) {
        if (!incrementCountIfFound(productUuid, count)) {
            products.add(new ShoppingCartItem(productUuid, count));
        }
    }

    public void setProductCount(UUID productUuid, int count) {
        if (count <= 0) {
            clearProduct(productUuid);
        } else {
            ShoppingCartItem item = findItemInCart(productUuid);
            if (item != null) {
                item.setCount(count);
            }
        }
    }

    public int getProductCount(UUID productUuid) {
        int res = 0;
        ShoppingCartItem item = findItemInCart(productUuid);
        if (item != null) res = item.getCount();
        return res;
    }

    /**
     * @param productUuid removes product by UUID from Shopcart
     */
    public void clearProduct(UUID productUuid) {
        ShoppingCartItem item = findItemInCart(productUuid);
        if (item != null) {
            products.remove(item);
        }
    }


    /**
     * removes all products from Shopcart
     */
    public void clearCart() {
        products.clear();
    }

    private boolean incrementCountIfFound(UUID productUuid, int count) {
        boolean res = false;
        ShoppingCartItem item = findItemInCart(productUuid);
        if (item != null) {
            item.incrementCount(count);
            res = true;
        }
        return res;
    }

    private ShoppingCartItem findItemInCart(UUID productUuid) {
        ShoppingCartItem shoppingCartItem = null;
        for (ShoppingCartItem item : products) {
            if (item.getProductUuid().equals(productUuid)) {
                shoppingCartItem = item;
                break;
            }
        }
        return shoppingCartItem;
    }
}
