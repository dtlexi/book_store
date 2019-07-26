
package com.lexi.ren.web.servlet;

import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class RegisterServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        User user=new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
            user.setState(0);
            user.setRole("普通用户");
            user.setRegistTime(new Date());
            UserService service=new UserService();
            service.addUser(user);

            System.out.println(user);

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
