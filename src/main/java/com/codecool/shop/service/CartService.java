package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.math.BigDecimal;
import java.util.HashMap;

public class CartService {
    private CartDao cartDao;
    private ProductCategoryDao productCategoryDao;

    public CartService(CartDao cartDao, ProductCategoryDao productCategoryDao) {
        this.cartDao = cartDao;
        this.productCategoryDao = productCategoryDao;
    }

    public int getItemCount() {
        return cartDao.getItemCount();
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public HashMap<Product, Integer> getCartContent() {
        return cartDao.getAll();
    }

    public BigDecimal getTotalPrice() {
        return cartDao.getTotalPrice();
    }


}
