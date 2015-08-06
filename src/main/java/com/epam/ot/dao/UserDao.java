package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    User findById(int id);

    User findByAccount(String login, String password);

    List<User> findAll();

    void updatePassword(User user);

    void insert(User user);

    boolean remove(User user);

    boolean removeById(int id);

    boolean removeByLogin(String login);
}
