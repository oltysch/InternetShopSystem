package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public interface UserDao {
    User findById(long id);

    User findByUuid(UUID uuid);

    User findByAccount(String login, String password);

    List<User> findAll();

    void updatePassword(User user);

    void insert(User user);

    boolean remove(User user);

    boolean removeById(long id);

    boolean removeByUuid(UUID uuid);

    boolean removeByLogin(String login);
}
