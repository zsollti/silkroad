package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class CartDaoMem implements CartDao {
    private HashMap<Product, Integer> cart = new HashMap<>();
    private int itemCount = 0;
    private BigDecimal price = new BigDecimal(0);
    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
        itemCount++;
        price = price.add(product.getDefaultPrice());

    }

    @Override
    public void updateCount(int id, int count) {
        Product product = ProductDaoMem.getInstance().find(id);
        if (count == 0) {
            remove(id);
        } else {
            cart.put(product, count);
            itemCount += count - cart.get(product);
            price = price.add(product.getDefaultPrice().multiply(new BigDecimal(count - cart.get(product))));
        }
    }

    @Override
    public void remove(int id) {
        Product product = ProductDaoMem.getInstance().find(id);
        price = price.subtract(product.getDefaultPrice().multiply(new BigDecimal(cart.get(product))));
        if (cart.containsKey(product)) {
            {
                itemCount = itemCount - cart.get(product);
                cart.remove(product);
            }
        }

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public void decreaseCount(int id) {
        Product product = ProductDaoMem.getInstance().find(id);
        if (cart.containsKey(product)) {
            if (cart.get(product) == 1) {
                cart.remove(product);
                itemCount--;
                price = price.subtract(product.getDefaultPrice());
            }
            if (cart.get(product) > 1) {
                cart.put(product, cart.get(product) - 1);
                price = price.subtract(product.getDefaultPrice());
                itemCount--;
            }
        }

    }

    @Override
    public Product getProductByid(int id) {
        return ProductDaoMem.getInstance().find(id);

    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return cart;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return price;
    }


}
