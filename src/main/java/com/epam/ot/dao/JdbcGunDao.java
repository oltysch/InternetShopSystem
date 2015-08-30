package com.epam.ot.dao;

import com.epam.ot.entity.Gun;
import com.epam.ot.util.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<Gun> findAll() {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public Gun findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
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
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
                gun.setId(resultSet.getInt(1));
                gun.setUuid((UUID) resultSet.getObject(2));
                gun.setDescription(resultSet.getString(7));
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
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByModel(String model) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.model"));
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByOrigin(String origin) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.origin"));
            preparedStatement.setString(1, origin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByCaliber(String caliber) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.caliber"));
            preparedStatement.setString(1, caliber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByPriceRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.price"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.firingRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByEffectiveFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.effectiveFiringRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByMagazineCapacity(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.magazineCapacity"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
    public List<Gun> findByFireRate(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.fireRate"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(11), resultSet.getInt(10), resultSet.getInt(12), resultSet.getInt(8), resultSet.getInt(9));
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.update"));
            preparedStatement.setString(1, gun.getType());
            preparedStatement.setString(2, gun.getName());
            preparedStatement.setDouble(3, gun.getPrice());
            preparedStatement.setString(4, gun.getOrigin());
            preparedStatement.setString(5, gun.getDescription());
            preparedStatement.setInt(6, gun.getFiringRange());
            preparedStatement.setInt(7, gun.getEffectiveFiringRange());
            preparedStatement.setInt(8, gun.getMagazineCapacity());
            preparedStatement.setString(9, gun.getCaliber());
            preparedStatement.setInt(10, gun.getFireRate());
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
            preparedStatement.setString(1, gun.getType());
            preparedStatement.setString(2, gun.getName());
            preparedStatement.setDouble(3, gun.getPrice());
            preparedStatement.setString(4, gun.getOrigin());
            preparedStatement.setString(5, gun.getDescription());
            preparedStatement.setInt(6, gun.getFiringRange());
            preparedStatement.setInt(7, gun.getEffectiveFiringRange());
            preparedStatement.setInt(8, gun.getMagazineCapacity());
            preparedStatement.setString(9, gun.getCaliber());
            preparedStatement.setInt(10, gun.getFireRate());
            preparedStatement.setObject(11, gun.getUuid());
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
