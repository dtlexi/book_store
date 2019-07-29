package com.lexi.ren.test;


import com.lexi.ren.model.User;
import com.lexi.ren.service.UserService;
import com.lexi.ren.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

public class UserServiceTest {

    @Test
    public void testFindUserById() throws SQLException {
        UserService service = new UserService();
        User user = service.findUserById(1);
        System.out.println(user);
    }
    @Test
    public void testFindUserByUserNameAndPwd() throws SQLException {
        UserService service = new UserService();
        User user = service.findUserByUserNameAndPwd("123","123");
        System.out.println(user);
    }

    @Test
    public void testFindUserByActiveCode() throws SQLException {
        UserService service = new UserService();
        User user = service.findUserByActiveCode("459411b7-347e-4643-83aa-b806bd367675");
        System.out.println(user);
    }
}
