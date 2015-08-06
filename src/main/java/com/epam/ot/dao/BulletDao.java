package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.util.List;

public interface BulletDao {
    Bullet findById(int id);

    List<Bullet> findByCaliber(String caliber);

    List<Bullet> findByName(String name);

    List<Bullet> findByType(String type);

    List<Bullet> findByPriceRange(int min, int max);

    List<Bullet> findAll();

    void update(Bullet bullet);

    void insert(Bullet bullet);

    boolean remove(Bullet bullet);

    boolean removeById(int id);
}
