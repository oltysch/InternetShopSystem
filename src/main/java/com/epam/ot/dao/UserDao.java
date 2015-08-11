package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public interface UserDao {
    User findById(long id);

    User findByUuid(UUID uuid);

    User findByField(String field, Object value);

    List<User> findArrayByField(String field, Object value);

    List<User> findArrayByFieldRange(String field, Object minValue, Object maxValue);

    List<User> findAll();

    void updateUser(User user);

    void insert(User user);

    boolean remove(User user);

    boolean removeByField(String field, Object value);

//    User findByAccount(String login, String password);

    /*boolean removeById(long id);

    boolean removeByUuid(UUID uuid);

    boolean removeByLogin(String login);*/
}
