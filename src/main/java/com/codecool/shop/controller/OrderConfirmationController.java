package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet(urlPatterns = {"/order-confirmation"})
public class OrderConfirmationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDaoMem cart = CartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("productMap", cart.getAll());
        context.setVariable("total", cart.getTotalPrice());
        context.setVariable("itemCount", cart.getItemCount());
        engine.process("product/order_confirmation.html", context, resp.getWriter());
        Gson gson = new Gson();
        File newfile = new File("src/main/webapp/static/orders/OrderLog.json");
        newfile.createNewFile();
        FileWriter file = new FileWriter("src/main/webapp/static/orders/OrderLog.json");
        Order order = OrderDao.getInstance().getOrder();
        System.out.println(order);
        String jsonInString = gson.toJson(order);
        file.write(jsonInString);
        file.flush();
        file.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}