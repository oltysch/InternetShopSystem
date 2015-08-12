package com.epam.ot.dao;

import com.epam.ot.users.Role;
import com.epam.ot.users.User;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> findAll();

    User findById(long id);

    User findByUuid(UUID uuid);

    User findByLogin(String login);

    User findByEmail(String email);

    User findByRole(String role);

    void updateUser(User user);

    void insert(User user);

    boolean remove(User user);
}
