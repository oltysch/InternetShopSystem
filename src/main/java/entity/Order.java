package entity;

import entity.products.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 21.05.2015.
 */
public class Order {
    private Client client;
    private ArrayList<Product> orderList = new ArrayList<Product>(); //Product in this order
    private Boolean isPaidUp; //checking payment of order
    private Boolean isRegistered; //checking the registered of order


    //it's adds a other orderList to this orderList
    public void addProductToOrder(ArrayList<Product> orderList) {
        this.orderList=orderList;
    }

    //it's adds a single Product into orderList
    public void addProductToOrder(Product product) {
        orderList.add(product);
    }

    //shows entity in order list
    public void showOrder() {
        for (Product product:orderList) {
            System.out.println("Название: " + product.getType() + "Цена: "+product.getPrice());
        }
    }

    public Boolean isPaidUp() {
        return isPaidUp;
    }

    public void setPaidUp(Boolean isPaidUp) {
        this.isPaidUp = isPaidUp;
    }

    public Boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
