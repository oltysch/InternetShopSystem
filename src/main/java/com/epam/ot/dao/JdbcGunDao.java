package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcGunDao implements GunDao {
    //TODO - move this into properties
    public static final String FIND_BY_ID = "SELECT * FROM GUNS WHERE id = ?";
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

    public void update(Gun gun) {

    }

    public Gun save(Gun gun) {
        return null;
    }

    public Gun merge(Gun gun) {
        return null;
    }

    public Gun insert(Gun gun) {
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
        return gun;
    }

    public boolean remove(Gun gun) {
        return true;
    }

    public boolean removeById(int id) {
        return true;
    }
}
