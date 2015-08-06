package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.util.List;

public interface GunDao {
    Gun findById(int id);

    List<Gun> findAll();

    List<Gun> findByType(String type);

    List<Gun> findByModel(String model);

    List<Gun> findByPriceRange(int min, int max);

    List<Gun> findByOrigin(String origin);

    List<Gun> findByFiringRange(int min, int max);

    List<Gun> findByEffectiveFiringRange(int min, int max);

    List<Gun> findByMagazineCapacity(int min, int max);

    List<Gun> findByCaliber(String caliber);

    List<Gun> findByFireRate(int min, int max);

    void update(Gun gun);

    void insert(Gun gun);
    //TODO remake removers
//    boolean remove(Gun gun);

    boolean removeById(int id);
}
