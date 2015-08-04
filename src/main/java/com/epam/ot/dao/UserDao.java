package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;

public interface UserDao {
    User findById(int id);

    User findByAccount(String login, String password);

    void update(User user);

    void insert(User user);

    boolean remove(User user);

    boolean removeById(int id);
}
