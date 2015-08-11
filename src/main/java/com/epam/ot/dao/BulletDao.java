package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.util.List;
import java.util.UUID;

public interface BulletDao {
    Bullet findById(long id);

    Bullet findByUuid(UUID uuid);

    Bullet findByField(String field, Object value);

    List<Bullet> findArrayByField(String field, Object value);

    List<Bullet> findArrayByFieldRange(String field, Object minValue, Object maxValue);

    List<Bullet> findAll();

    void update(Bullet bullet);

    void insert(Bullet bullet);

    boolean remove(Bullet bullet);

    boolean removeByField(String field, Object value);

    /*boolean removeById(long id);

    List<Bullet> findByCaliber(String caliber);

    List<Bullet> findByName(String name);

    List<Bullet> findByType(String type);

    List<Bullet> findByPriceRange(int min, int max);*/
}
