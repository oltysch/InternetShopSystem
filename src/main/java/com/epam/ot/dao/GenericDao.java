package com.epam.ot.dao;

import java.util.List;
import java.util.UUID;

public interface GenericDao<T> {
    T findById(long id);

    T findByUuid(UUID uuid);

    T findByField(String field, Object value);

    List<T> findArrayByField(String field, Object value);

    List<T> findArrayByFieldRange(String field, Object minValue, Object maxValue);

    List<T> findAll();

    void update(T type);

    void insert(T type);

    boolean remove(T type);

    boolean removeByField(String field, Object value);
}
