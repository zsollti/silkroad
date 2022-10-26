package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDaoMem cartDataStore = CartDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory nft = new ProductCategory("NFT", "NFT Picture", "Unique picture, which u can say only u have truely");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(nft);

        //setting up products and printing it
        Product iphone = new Product("Iphone", new BigDecimal("500"), "USD", "A phone from Apple", tablet, amazon);
        productDataStore.add(iphone);
        cartDataStore.add(iphone);
        Product samsung = new Product("Samsung", new BigDecimal("200"), "USD", "A phone from Samsung", tablet, lenovo);
        productDataStore.add(samsung);
        cartDataStore.add(samsung);
        Product oneplus = new Product("Oneplus", new BigDecimal("300"), "USD", "A phone from OnePlus", tablet, amazon);
        productDataStore.add(oneplus);
        cartDataStore.add(oneplus);
        Product fairyDust = new Product("\"Fairy Dust\"", new BigDecimal("500"), "USD", "Magic white powder", tablet, amazon);
        productDataStore.add(fairyDust);
        cartDataStore.add(fairyDust);
        Product silkNFT = new Product("Silk", new BigDecimal("30100"), "USD", "Silk looking material", nft, amazon);
        productDataStore.add(silkNFT);
        Product roadNft = new Product("Road", new BigDecimal("32200"), "USD", "A long road", nft, amazon);
        productDataStore.add(roadNft);
        Product cellingDogNFT = new Product("Celling Dog", new BigDecimal("40000"), "USD", "A dog on the celling", nft, amazon);
        productDataStore.add(cellingDogNFT);
        Product shrekNFT = new Product("Shrek", new BigDecimal("35500"), "USD", "Made in China", nft, amazon);
        productDataStore.add(shrekNFT);
        Product duckTankNFT = new Product("Duck Tank", new BigDecimal("20100"), "USD", "A tank of ducks", nft, amazon);
        productDataStore.add(duckTankNFT);
    }
}
