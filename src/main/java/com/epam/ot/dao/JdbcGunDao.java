package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGunDao implements GunDao {
    public static final String FIND_BY_ID = "SELECT * FROM GUNS WHERE id = ?";
    //TODO split this into gun types
    public static final String FIND_ALL = "SELECT ID, GUNS.TYPE, MODEL, PRICE, ORIGIN, CALIBER, MAGAZINE_CAPACITY, FIRE_RATE, FIRING_RANGE, EFFECTIVE_FIRING_RANGE FROM GUNS, GUNS_TTC WHERE GUNS_TTC.GUN_ID=GUNS.ID";
    public static final String INSERT_INTO_GUNS = "INSERT INTO GUNS (id, model, origin, handy, firing_range, effective_firing_range, cartridge_clip_availability, optics_availability, material) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_GUN = "DELETE FROM GUNS WHERE id = ?";
    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String ORIGIN = "origin";
    public static final String HANDY = "handy";
    public static final String FIRING_RANGE = "firing_Range";
    public static final String EFFECTIVE_FIRING_RANGE = "effective_Firing_Range";
    public static final String CARTRIDGE_CLIP_AVAILABILITY = "cartridge_Clip_Availability";
    public static final String OPTICS_AVAILABILITY = "optics_Availability";
    public static final String MATERIAL = "material";
    private final Connection connection;

    //TODO remake JdbcGunDao
    public JdbcGunDao(Connection connection) {
        this.connection = connection;
    }

    public Gun findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Gun gun = new Gun();
                return gun;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Gun> findAll() {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getInt(10));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return guns;
    }

    public void update(Gun gun) {

    }

    public void insert(Gun gun) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GUNS);
            preparedStatement.setInt(1, gun.getId());
            preparedStatement.setString(2, gun.getType());
            preparedStatement.setString(3, gun.getModel());
            preparedStatement.setDouble(4, gun.getPrice());
            preparedStatement.setString(5, gun.getOrigin());
//            TODO make description
//            preparedStatement.setClob(6, gun.get);
            //TODO insert ttc's

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean remove(Gun gun) {
        return true;
    }

    public boolean removeById(int id) {
        return true;
    }
}
