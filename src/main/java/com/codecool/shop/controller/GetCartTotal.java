package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart/total"})
public class GetCartTotal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cart = CartDaoMem.getInstance();
        resp.setContentType("application/json");
        resp.getWriter().print("{\"total\": " + cart.getTotalPrice());
        resp.getWriter().print(", \"count\": " + cart.getItemCount() + "}");
    }
}
