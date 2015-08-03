package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;

public interface GunDao {
    Gun findById(int id);

    void update(Gun gun);

    void save(Gun gun);

    void merge(Gun gun);

    void insert(Gun gun);

    boolean remove(Gun gun);

    boolean removeById(int id);
}
