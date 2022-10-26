package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.secure.PasswordHashing;
import com.codecool.shop.secure.PasswordVerifyer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("costumer/login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        PasswordHashing passwordHashing = new PasswordHashing();
        PasswordVerifyer passwordVerifyer = new PasswordVerifyer();
        //implement get password from database by username
        String hashedPassword = "test";

        if (username.equals("admin") && password.equals("admin")) {
            resp.sendRedirect("/admin");
        }
        else if (password.equals(hashedPassword)) {
            session.setAttribute("username", username);
            System.out.println(session.getAttribute("username"));
            resp.sendRedirect("/");
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
