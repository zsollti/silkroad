package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Order;

public class OrderDao {

    private static OrderDao instance = null;
    private Order order;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    //getter for order
    public Order getOrder() {
        return order;
    }

    //setter for order
    public void setOrder(Order order) {
        this.order = order;
    }

}
