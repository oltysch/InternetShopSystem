package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.util.List;
import java.util.UUID;

public interface BulletDao {
    Bullet findById(long id);

    Bullet findByUuid(UUID uuid);

    List<Bullet> findByCaliber(String caliber);

    List<Bullet> findByName(String name);

    List<Bullet> findByType(String type);

    List<Bullet> findByPriceRange(int min, int max);

    List<Bullet> findAll();

    void update(Bullet bullet);

    void insert(Bullet bullet);

    boolean remove(Bullet bullet);

    boolean removeById(long id);
}
