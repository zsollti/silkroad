package com.codecool.shop.model;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;

import java.util.HashMap;

public class Order {

    public CartDao cartdao = CartDaoMem.getInstance();
    public HashMap<Product, Integer> cart = cartdao.getAll();
    public Customer customer;

    public Order(Customer customer) {
        this.customer = customer;
    }


}
