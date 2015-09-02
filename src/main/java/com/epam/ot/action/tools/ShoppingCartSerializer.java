package com.epam.ot.action.tools;

import com.epam.ot.entity.ShoppingCart;

import java.io.*;

public class ShoppingCartSerializer {
    public static String writeCartInString(ShoppingCart cart) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(cart);
            return bos.toString();
        } catch (IOException e) {
            throw new SerializeException("cart writing error" + e);
        }
    }

    public static ShoppingCart readCartFromString(String string) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(string.getBytes());
            ObjectInputStream in = new ObjectInputStream(bis);
            return (ShoppingCart) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializeException("cart reading error" + e);
        }
    }
}
