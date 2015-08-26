package com.epam.ot.dao;

import java.util.List;

public interface GunsTypesDao {
    List<String> findAll();

    void insert(String string);

    boolean remove(String string);

    void beginTransaction();

    void endTransaction();

    void rollbackTransaction();

    void close();
}
