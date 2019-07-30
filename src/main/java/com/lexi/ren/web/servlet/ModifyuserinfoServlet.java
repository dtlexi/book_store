package com.lexi.ren.web.servlet;

import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/Modifyuserinfo")
public class ModifyuserinfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        if(user.getPassword()==null)
        {
            throw new RuntimeException("密码不能为空");
        }


        UserService service=new UserService();
        user=service.updateUser(user);
        if(user!=null)
        {
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/modifyUserInfoSuccess.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/modifyuserinfo.jsp").forward(req,resp);
        }
    }
}
