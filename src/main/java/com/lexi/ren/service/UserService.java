package com.lexi.ren.service;

import com.lexi.ren.dao.UserDao;
import com.lexi.ren.model.User;

import java.security.PublicKey;
import java.sql.SQLException;

public class UserService {
    UserDao _userDao;

    UserDao getUserDao() {
        if (_userDao == null) {
            _userDao = new UserDao();
        }
        return _userDao;
    }

    public void addUser(User user) throws SQLException {
        getUserDao().addUser(user);
    }

    public User findUserById(int id) throws SQLException {
        return getUserDao().findUserById(id);
    }

    public User findUserByActiveCode(String code) throws SQLException {
        return getUserDao().findUserByActiveCode(code);
    }

    public boolean activeUser(String code) throws SQLException {
        return getUserDao().activeUser(code);
    }

    public User findUserByUserNameAndPwd(String username, String pwd) throws SQLException {
        return getUserDao().findUserByUserNameAndPwd(username, pwd);
    }

    public User login(String username, String password) {
        User user = null;
        try {
            user = getUserDao().findUserByUserNameAndPwd(username, password);
        } catch (SQLException e) {
            throw new RuntimeException("登录失败！");
        }
        if (user == null) {
            return null;
        }
        if(user.getState()==0)
        {
            throw new RuntimeException("当前用户需激活！");
        }
        return user;
    }
}
