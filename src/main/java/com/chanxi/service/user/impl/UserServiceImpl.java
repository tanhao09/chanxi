package com.chanxi.service.user.impl;

import com.chanxi.dao.user.UserDao;
import com.chanxi.model.user.User;
import com.chanxi.model.user.UserArgs;
import com.chanxi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ymind on 2018/6/4.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public User search(UserArgs args) {
        return userDao.search(args);
    }
}
