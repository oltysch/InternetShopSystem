package com.epam.ot.dao;

import com.epam.ot.entity.Gun;
import com.epam.ot.util.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcGunDao implements GunDao {
    private final Connection connection;
    private final PropertyManager propertyManager;

    public JdbcGunDao(Connection connection) {
        this.connection = connection;
        propertyManager = new PropertyManager("query.properties");
    }

    private Gun findFromResultSet(ResultSet resultSet) throws SQLException {
        Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5));
        gun.setId(resultSet.getInt(1));
        gun.setUuid((UUID) resultSet.getObject(2));
        gun.setOrigin(resultSet.getString(6));
        gun.setDescription(resultSet.getString(7));
        gun.setFiringRange(resultSet.getInt(8));
        gun.setEffectiveFiringRange(resultSet.getInt(9));
        gun.setMagazineCapacity(resultSet.getInt(10));
        gun.setCaliber(resultSet.getString(11));
        gun.setFireRate(resultSet.getInt(12));
        return gun;
    }

    private void makePreparedStatement(Gun gun, PreparedStatement preparedStatement, int startIndex) throws SQLException {
        preparedStatement.setString(startIndex, gun.getType());
        preparedStatement.setString(startIndex + 1, gun.getName());
        preparedStatement.setDouble(startIndex + 2, gun.getPrice());
        String origin = gun.getOrigin();
        if (origin != null) {
            preparedStatement.setString(startIndex + 3, origin);
        } else {
            preparedStatement.setNull(startIndex + 3, Statement.SUCCESS_NO_INFO);
        }
        String description = gun.getDescription();
        if (description != null) {
            preparedStatement.setString(startIndex + 4, description);
        } else {
            preparedStatement.setNull(startIndex + 4, Statement.SUCCESS_NO_INFO);
        }
        Integer firingRange = gun.getFiringRange();
        if (firingRange != null) {
            preparedStatement.setInt(startIndex + 5, firingRange);
        } else {
            preparedStatement.setNull(startIndex + 5, Statement.SUCCESS_NO_INFO);
        }
        Integer effeciveFiringRange = gun.getEffectiveFiringRange();
        if (effeciveFiringRange != null) {
            preparedStatement.setInt(startIndex + 6, effeciveFiringRange);
        } else {
            preparedStatement.setNull(startIndex + 6, Statement.SUCCESS_NO_INFO);
        }
        Integer magazineCapacity = gun.getMagazineCapacity();
        if (magazineCapacity != null) {
            preparedStatement.setInt(startIndex + 7, magazineCapacity);
        } else {
            preparedStatement.setNull(startIndex + 7, Statement.SUCCESS_NO_INFO);
        }
        String caliber = gun.getCaliber();
        if (caliber != null) {
            preparedStatement.setString(startIndex + 8, caliber);
        } else {
            preparedStatement.setNull(startIndex + 8, Statement.SUCCESS_NO_INFO);
        }
        Integer fireRate = gun.getFireRate();
        if (fireRate != null) {
            preparedStatement.setInt(startIndex + 9, fireRate);
        } else {
            preparedStatement.setNull(startIndex + 9, Statement.SUCCESS_NO_INFO);
        }
    }

    @Override
    public List<Gun> findAll() {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<String> findAllTypes() {
        List<String> types = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.types"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString(1));
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
        return types;
    }

    @Override
    public Gun findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
                return gun;
            } else {
                return null;
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
    }

    @Override
    public Gun findByUuid(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.uuid"));
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
                return gun;
            } else {
                return null;
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
    }

    @Override
    public List<Gun> findByType(String type) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.type"));
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByModel(String model) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.model"));
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByOrigin(String origin) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.origin"));
            preparedStatement.setString(1, origin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByCaliber(String caliber) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.caliber"));
            preparedStatement.setString(1, caliber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByPriceRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.price"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.firingRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByEffectiveFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.effectiveFiringRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByMagazineCapacity(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.magazineCapacity"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
    public List<Gun> findByFireRate(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.fireRate"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = findFromResultSet(resultSet);
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.update"));
            makePreparedStatement(gun, preparedStatement, 1);
            preparedStatement.setObject(11, gun.getUuid());
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.insert"));
            preparedStatement.setObject(1, gun.getUuid());
            makePreparedStatement(gun, preparedStatement, 2);
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
    public boolean remove(Gun gun) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.delete"));
            preparedStatement.setString(1, String.valueOf(gun.getUuid()));
            preparedStatement.setString(2, String.valueOf(gun.getUuid()));
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

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void endTransaction() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
