package com.lexi.ren.web.servlet;

import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String activecode=req.getParameter("activecode");
        UserService service=new UserService();
        User user= null;
        try {
            user = service.findUserByActiveCode(activecode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user==null)
        {
            throw new RuntimeException("不存在当前用户！");
        }
        if(user.getState()==1)
        {
            throw new RuntimeException("当前用户已激活");
        }
        try {
            if(service.activeUser(activecode))
            {
                req.getRequestDispatcher("/activesuccess.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
