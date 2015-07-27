package com.epam.ot.dao;

import com.epam.ot.products.Gun;

public interface GunDao {

    Gun findById(int id);

    void removeById(int id);

    void update();

    void save();

    Gun merge();

    //TODO - choose inputs: entity or entity values or &&
    void insert(int id, String model, String origin, String handy, int firingRange, int effectiveFiringRange, boolean cartridgeClipAvailability, boolean opticsAvailability, String material);

    void insert(Gun gun);

    void upset();
}
