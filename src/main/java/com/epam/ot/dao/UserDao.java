package com.epam.ot.dao;

import com.epam.ot.users.User;

public interface UserDao {
    User findById(int id);

    User findByLogin(String login);

    void update(User user);

    User save(User user);

    User merge(User user);

    User insert(User user);

    boolean remove(User user);

    boolean removeById(int id);
}