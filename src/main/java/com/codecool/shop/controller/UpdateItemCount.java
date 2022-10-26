package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart/update"})
public class UpdateItemCount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            int count = Integer.parseInt(req.getParameter("count"));
            CartDao cart = CartDaoMem.getInstance();
            cart.updateCount(id, count);
            resp.setStatus(200);
            System.out.println(cart.getAll());
        } else {
            resp.setStatus(404);
        }

    }
}
