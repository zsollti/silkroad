package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;


    @WebServlet(urlPatterns = "/index")
    public class IndexController extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String requestPage = request.getParameter("type");
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, response, request.getServletContext());
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
            ProductService productService = new ProductService(productDataStore,productCategoryDataStore,supplierDataStore);
            context.setVariable("data", productService.getAllProduct());
            CartDaoMem cartDataStore = CartDaoMem.getInstance();
            CartService cartService = new CartService(cartDataStore, productCategoryDataStore);
            context.setVariable("itemCount", cartService.getItemCount());
            if(requestPage.equals("category")){
                String title = "Products by Product Categories";
                context.setVariable("title", title);
            }else{
                String title = "Products by Suppliers";
                context.setVariable("title", title);
            }
            engine.process("product/indexPage.html", context, response.getWriter());

        }
    }




