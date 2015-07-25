package com.epam.ot.dao;

import com.epam.ot.products.Gun;

public interface GunDao {

    Gun findById(int id);

    void removeById(int id);

    void update();

    void save();

    Gun merge();

    //TODO - choose inputs: com.epam.ot.entity or com.epam.ot.entity values or &&
    void insert(int id, String model, String origin, String handy, int firingRange, int effectiveFiringRange, boolean cartridgeClipAvailability, boolean opticsAvailability, String material);

    void insert(Gun gun);

    void upset();
}
