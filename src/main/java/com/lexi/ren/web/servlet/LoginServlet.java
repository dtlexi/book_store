package com.lexi.ren.web.servlet;

import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service=new UserService();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        User user=service.login(username,password);
        if(user!=null)
        {
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else {
            req.setAttribute("login_msg","用户名或密码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
