package com.epam.ot.entity;

import com.epam.ot.products.*;

import java.util.ArrayList;

public class Administrator {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Client> blackClients = new ArrayList<>();

    //register the order
    public void registerOrder(Client client) {
        if (client.getOrder().isPaidUp()) {
            client.getOrder().setRegistered(true);
        } else {
            blackClients.add(client);
        }
    }

    //TODO - make more universal method
    public void createNewComputerDevice(String type, String name, int power, Double price) {
        products.add(new Gun());
    }
}
