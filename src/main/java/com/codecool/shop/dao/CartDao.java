package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface CartDao {
    void add(Product product);

    void updateCount(int id, int count);

    void remove(int id);

    void decreaseCount(int id);

    Product getProductByid(int id);

    HashMap<Product, Integer> getAll();

    void clear();

    int getItemCount();

    BigDecimal getTotalPrice();
}
