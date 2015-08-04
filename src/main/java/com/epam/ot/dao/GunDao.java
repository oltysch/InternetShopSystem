package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.util.List;

public interface GunDao {
    Gun findById(int id);

    List<Gun> findAll();

    void update(Gun gun);

    void insert(Gun gun);

    boolean remove(Gun gun);

    boolean removeById(int id);
}
