package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public interface GunDao {
    List<Gun> findAll();

    Gun findById(long id);

    Gun findByUuid(UUID uuid);

    List<Gun> findByType(String type);

    List<Gun> findByModel(String model);

    List<Gun> findByOrigin(String origin);

    List<Gun> findByCaliber(String caliber);

    List<Gun> findByPriceRange(int min, int max);

    List<Gun> findByFiringRange(int min, int max);

    List<Gun> findByEffectiveFiringRange(int min, int max);

    List<Gun> findByMagazineCapacity(int min, int max);

    List<Gun> findByFireRate(int min, int max);

    void update(Gun gun);

    void insert(Gun gun);

    boolean remove(Gun gun);
}
