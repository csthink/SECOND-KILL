package com.csthink.secondkill.service.impl;

import com.csthink.secondkill.entity.User;
import com.csthink.secondkill.dao.UserDao;
import com.csthink.secondkill.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User find(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public void addUser(User user) {
        userDao.insert(user);
    }
}
