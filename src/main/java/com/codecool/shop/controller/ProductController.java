package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.CartService;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPage = req.getParameter("type");
        String headerTitle = req.getParameter("head");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,supplierDataStore);
        CartDaoMem cartDataStore = CartDaoMem.getInstance();
        CartService cartService = new CartService(cartDataStore, productCategoryDataStore);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("itemCount", cartService.getItemCount());
        if(requestPage==null){
            context.setVariable("searchType","Original");
            context.setVariable("category", productService.getProductCategory(1));
            context.setVariable("products", productService.getProductsForCategory(1));
            engine.process("product/index.html", context, resp.getWriter());
            System.out.println(CartDaoMem.getInstance().getAll());
        }else{
            context.setVariable("head",headerTitle);
            int index = Integer.parseInt(req.getParameter("id"));
            context.setVariable("searchType","Indexing");
            context.setVariable("category", requestPage);
            context.setVariable("itemCount", cartService.getItemCount());
            if(requestPage.equals("sup")){
                context.setVariable("products", productService.getProductsForSupplier(index));
                engine.process("product/index.html", context, resp.getWriter());
            }else{
                context.setVariable("products", productService.getProductsForCategory(index));
                engine.process("product/index.html", context, resp.getWriter());
            }
        }

    }

}


