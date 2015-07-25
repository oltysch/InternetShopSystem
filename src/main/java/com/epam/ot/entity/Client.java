package com.epam.ot.entity;

public class Client {
    private Order order;

    //make order
    public void toOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    //shows the order
    public void showOrder() {
        System.out.println("Ваш заказ: ");
        order.showOrder();
    }

    //pay order
    public void pay() {
        if (order.isPaidUp()) {
            System.out.println("Вы уже оплатили заказ");
        } else {
            order.setPaidUp(true);
        }
    }

    //take order
    public void takeOrder() {
        if (order.isPaidUp()==false) {
            System.out.println("Вы еще не оплатили товар");
        } else if (order.isRegistered()==false) {
            System.out.println("Ваша заявка еще не обработана");
        } else {
            System.out.println("Спасибо за покупку!");
        }
    }
}