
package com.lexi.ren.web.servlet;

import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class RegisterServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        try {
            String code=req.getParameter("checkcode");
            if(!code.equals(req.getSession().getAttribute("checkcode_session")))
            {
                req.setAttribute("checkcode_err","验证码错误！");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
                return;
            }
            BeanUtils.populate(user,req.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
            user.setState(0);
            user.setRole("普通用户");
            user.setRegistTime(new Date());
            UserService service=new UserService();
            service.addUser(user);
            //跳转到注册成功页面
            req.getRequestDispatcher("/registersuccess.jsp").forward(req,resp);
            System.out.println(user);
        }  catch (Exception e) {
            req.setAttribute("register_err","当前用户已存在");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
}
