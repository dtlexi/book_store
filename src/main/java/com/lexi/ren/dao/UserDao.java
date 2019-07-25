package com.lexi.ren.dao;

import com.lexi.ren.model.User;
import com.lexi.ren.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="INSERT INTO USER (username,password,gender,email,telephone,introduce,activeCode,state,role,registTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
        runner.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());
    }
}
