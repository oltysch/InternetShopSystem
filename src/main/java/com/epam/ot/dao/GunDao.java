package com.epam.ot.dao;

import com.epam.ot.products.Gun;

public interface GunDao {
    Gun findById(int id);

    void update(Gun gun);

    Gun save(Gun gun);

    Gun merge(Gun gun);

    Gun insert(Gun gun);

    boolean remove(Gun gun);

    boolean removeById(int id);
}
