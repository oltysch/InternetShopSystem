package com.epam.ot.dao;

import com.epam.ot.products.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcGunDao implements GunDao {
    public static final String FIND_ALL = "SELECT * FROM GUNS, GUNS_TTC WHERE GUNS_TTC.GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_FIELD = "SELECT * FROM GUNS, GUNS_TTC WHERE ? = ? AND GUN_UUID=GUNS.UUID";
    public static final String FIND_BY_FIELD_RANGE = "SELECT * FROM GUNS, GUNS_TTC WHERE ? >= ? AND ? <= ? AND GUN_UUID=GUNS.UUID";
    //TODO make update all
    public static final String UPDATE_GUN = "UPDATE GUNS SET TYPE=?, MODEL=?, PRICE=?, ORIGIN=?, DESCRIPTION=? WHERE GUNS.UUID = ?;UPDATE GUNS_TTC SET FIRING_RANGE=?, EFFECTIVE_FIRING_RANGE=?, MAGAZINE_CAPACITY=?, CALIBER=?, FIRE_RATE=? WHERE GUN_UUID = ?";
    public static final String INSERT_INTO_GUNS = "INSERT INTO GUNS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);INSERT INTO GUNS_TTC VALUES (?, ?, ?, ?, ?, ?)";
    public static final String REMOVE_GUN_BY_FIELD = "DELETE FROM GUNS_TTC WHERE GUN_UUID=(SELECT uuid FROM GUNS WHERE ? = ?); DELETE FROM GUNS WHERE ? = ?";

    private final Connection connection;

    public JdbcGunDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Gun findById(long id) {
        return findByField("id", id);
    }

    @Override
    public Gun findByUuid(UUID uuid) {
        return findByField("uuid", uuid);
    }

    @Override
    public Gun findByField(String field, Object value) {
        List<Gun> guns = findArrayByField(field, value);
        if (guns.size() > 0) {
            return guns.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Gun> findArrayByField(String field, Object value) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findArrayByFieldRange(String field, Object minValue, Object maxValue) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD_RANGE);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, minValue);
            preparedStatement.setString(3, field);
            preparedStatement.setObject(4, maxValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public List<Gun> findAll() {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
                guns.add(gun);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return guns;
    }

    @Override
    public void update(Gun gun) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GUN);
            preparedStatement.setString(1, gun.getType());
            preparedStatement.setString(2, gun.getModel());
            preparedStatement.setDouble(3, gun.getPrice());
            preparedStatement.setString(4, gun.getOrigin());
            preparedStatement.setString(5, gun.getDescription());
            preparedStatement.setObject(6, gun.getUuid());
            preparedStatement.setInt(7, gun.getTtc().getFiringRange());
            preparedStatement.setInt(8, gun.getTtc().getEffectiveFiringRange());
            preparedStatement.setInt(9, gun.getTtc().getMagazineCapacity());
            preparedStatement.setString(10, gun.getTtc().getCaliber());
            preparedStatement.setInt(11, gun.getTtc().getFireRate());
            preparedStatement.setObject(12, gun.getUuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void insert(Gun gun) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GUNS);
            preparedStatement.setObject(1, gun.getUuid());
            preparedStatement.setString(2, gun.getType());
            preparedStatement.setString(3, gun.getModel());
            preparedStatement.setDouble(4, gun.getPrice());
            preparedStatement.setString(5, gun.getOrigin());
            preparedStatement.setString(6, gun.getDescription());
            preparedStatement.setObject(7, gun.getUuid());
            preparedStatement.setInt(8, gun.getTtc().getFiringRange());
            preparedStatement.setInt(9, gun.getTtc().getEffectiveFiringRange());
            preparedStatement.setInt(10, gun.getTtc().getMagazineCapacity());
            preparedStatement.setString(11, gun.getTtc().getCaliber());
            preparedStatement.setInt(12, gun.getTtc().getFireRate());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public boolean remove(Gun gun) {
        return removeByField("id", gun.getId());
    }

    @Override
    public boolean removeByField(String field, Object value) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GUN_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
            preparedStatement.setString(3, field);
            preparedStatement.setObject(4, value);
            preparedStatement.executeUpdate();
            res = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return res;
    }
}
