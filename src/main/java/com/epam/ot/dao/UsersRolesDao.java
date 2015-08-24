package com.epam.ot.dao;

import java.util.List;

public interface UsersRolesDao {
    List<String> findAll();

    void insert(String string);

    boolean remove(String string);

    void beginTransaction();

    void commitConnection();

    void rollbackConnection();
}
