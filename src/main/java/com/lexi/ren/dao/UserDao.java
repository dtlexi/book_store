package com.lexi.ren.dao;

import com.lexi.ren.model.User;
import com.lexi.ren.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.SQLException;

public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="INSERT INTO user (username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        runner.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());
    }

    public User findUserById(int id) throws SQLException {
        QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());

        String sql="select * from user where id=?";
        User user= runner.query(sql,new BeanHandler<User>(User.class),id);
        return user;
    }
}
