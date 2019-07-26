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
}
