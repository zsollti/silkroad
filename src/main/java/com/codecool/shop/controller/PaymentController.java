package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Date;

@WebServlet(name = "payment", urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        String payType = req.getParameter("paytype");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDao cartDao = CartDaoMem.getInstance();
        context.setVariable("totalPrice", cartDao.getTotalPrice());
        if (payType.equals("PayPal")) {
            engine.process("payment/paypal.html", context, resp.getWriter());
        } else if (payType.equals("CreditCard")) {
            engine.process("payment/creditcard.html", context, resp.getWriter());
        } else {
            resp.sendError(418, "I'm a teapot");
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        Integer expirationMonth = Integer.valueOf(req.getParameter("expiration-month"));
        Integer expirationYear = 2000 + Integer.valueOf(req.getParameter("expiration-year"));
        Date expirationDate = new Date(expirationYear, expirationMonth, 1);
        if (expirationDate.after(new Date())) {
            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\": \"fail\"}");
        }
        resp.sendRedirect("/order-confirmation");
    }
}
