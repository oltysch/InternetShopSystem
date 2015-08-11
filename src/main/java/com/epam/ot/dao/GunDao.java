package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public interface GunDao {
    Gun findById(long id);

    Gun findByUuid(UUID uuid);

    Gun findByField(String field, Object value);

    List<Gun> findArrayByField(String field, Object value);

    List<Gun> findArrayByFieldRange(String field, Object minValue, Object maxValue);

    List<Gun> findAll();

    void update(Gun gun);

    void insert(Gun gun);

    boolean remove(Gun gun);

    boolean removeByField(String field, Object value);

    /*List<Gun> findByType(String type);

    List<Gun> findByModel(String model);

    List<Gun> findByPriceRange(int min, int max);

    List<Gun> findByOrigin(String origin);

    List<Gun> findByFiringRange(int min, int max);

    List<Gun> findByEffectiveFiringRange(int min, int max);

    List<Gun> findByMagazineCapacity(int min, int max);

    List<Gun> findByCaliber(String caliber);

    List<Gun> findByFireRate(int min, int max);*/

//    boolean remove(Gun gun);

//    boolean removeById(long id);

//    boolean removeByUuid(UUID uuid);
}
