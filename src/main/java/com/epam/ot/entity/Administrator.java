package com.epam.ot.entity;

import com.epam.ot.entity.Lights;
import com.epam.ot.products.Product;

import java.util.ArrayList;

public class Administrator {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Client> blackClients = new ArrayList<Client>();

    //register the order
    public void registerOrder(Client client) {
        if (client.getOrder().isPaidUp()) {
            client.getOrder().setRegistered(true);
        } else {
            blackClients.add(client);
        }
    }

    //creating a new com.epam.ot.entity
    public void createNewProduct(String name, double price) {
        products.add(new Lights(name, price));

    }
}
