package com.epam.ot.action.tools;

import com.epam.ot.entity.ShoppingCart;

import java.io.*;

public class ShoppingCartSerializer {
    /**
     * @param cart
     * @return written in String shopping cart
     * @throws IOException
     */
    public static String writeCartInString(ShoppingCart cart) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(cart);
        return bos.toString();
    }

    /**
     * @param string
     * @return readed from String shopping cart
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ShoppingCart readCartFromString(String string) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(string.getBytes());
        ObjectInputStream in = new ObjectInputStream(bis);
        return (ShoppingCart) in.readObject();
    }
}
