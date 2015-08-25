package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.util.List;
import java.util.UUID;

public interface GunsTypesDao {
    List<String> findAll();

    void insert(String string);

    boolean remove(String string);

    void beginTransaction();

    void endTransaction();

    void rollbackTransaction();

    void close();
}
