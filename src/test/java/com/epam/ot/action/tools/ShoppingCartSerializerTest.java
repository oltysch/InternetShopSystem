package com.epam.ot.action.tools;

import com.epam.ot.entity.ShoppingCart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.UUID;

import static org.junit.Assert.*;

public class ShoppingCartSerializerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    //TODO make tests
    @Test
    public void testWriteCartInString() throws Exception {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(UUID.randomUUID(), 3);
        cart.addProduct(UUID.randomUUID(), 1);
        cart.addProduct(UUID.randomUUID(), 2);
        System.out.println(cart.getProducts());
        String str = ShoppingCartSerializer.writeCartInString(cart);
        System.out.println(str);
        ShoppingCart cart2 = ShoppingCartSerializer.readCartFromString(str);
        System.out.println(cart2);
    }

    @Test
    public void testReadCartFromString() throws Exception {

    }
}