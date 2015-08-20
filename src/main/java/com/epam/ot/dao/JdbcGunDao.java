package com.epam.ot.dao;

import com.epam.ot.products.Gun;
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
    public Gun findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
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
                Gun gun = new Gun(resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getString(12), resultSet.getInt(11), resultSet.getInt(13), resultSet.getInt(9), resultSet.getInt(10));
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
    public List<Gun> findByModel(String model) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.model"));
            preparedStatement.setString(1, model);
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
    public List<Gun> findByOrigin(String origin) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.origin"));
            preparedStatement.setString(1, origin);
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
    public List<Gun> findByCaliber(String caliber) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.caliber"));
            preparedStatement.setString(1, caliber);
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
    public List<Gun> findByPriceRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.price"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
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
    public List<Gun> findByFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.firingRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
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
    public List<Gun> findByEffectiveFiringRange(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.effectiveFiringRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
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
    public List<Gun> findByMagazineCapacity(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.magazineCapacity"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
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
    public List<Gun> findByFireRate(int min, int max) {
        List<Gun> guns = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.select.fireRate"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.update"));
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("guns.insert"));
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
    public void commitConnection() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
