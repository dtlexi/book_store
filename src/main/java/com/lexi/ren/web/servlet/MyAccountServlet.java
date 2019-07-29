package com.lexi.ren.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user")!=null)
        {
            //已经登录
            req.getRequestDispatcher("/myAccount.jsp").forward(req,resp);
        }else {
            //未登录
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }
    }
}
