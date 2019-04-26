package com.csthink.secondkill.service;

import com.csthink.secondkill.entity.User;
import java.util.List;

public interface UserService {

    User find(Integer id);

    List<User> findAll();

    void addUser(User user);
}
