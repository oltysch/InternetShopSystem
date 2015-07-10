package entity;

import entity.Lights;
import entity.products.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 22.05.2015.
 */
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

    //creating a new entity
    public void createNewProduct(String name, double price) {
        products.add(new Lights(name, price));

    }
}
