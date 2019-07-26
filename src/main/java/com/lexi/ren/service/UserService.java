package com.lexi.ren.service;

import com.lexi.ren.dao.UserDao;
import com.lexi.ren.model.User;

import java.sql.SQLException;

public class UserService {
    UserDao _userDao;
    UserDao getUserDao()
    {
        if(_userDao==null)
        {
            _userDao=new UserDao();
        }
        return _userDao;
    }

    public void addUser(User user) throws SQLException {
        getUserDao().addUser(user);
    }

    public User findUserById(int id) throws SQLException {
        return getUserDao().findUserById(id);
    }
}
