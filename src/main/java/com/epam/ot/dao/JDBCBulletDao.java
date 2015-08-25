package com.epam.ot.dao;

import com.epam.ot.products.Bullet;
import com.epam.ot.util.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JDBCBulletDao implements BulletDao {
    private final Connection connection;
    private final PropertyManager propertyManager;

    public JDBCBulletDao(Connection connection) {
        this.connection = connection;
        propertyManager = new PropertyManager("query.properties");
    }

    @Override
    public List<Bullet> findAll() {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                bullets.add(bullet);
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
        return bullets;
    }

    @Override
    public Bullet findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                return bullet;
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
    public Bullet findByUuid(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.uuid"));
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                return bullet;
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
    public List<Bullet> findByCaliber(String caliber) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.caliber"));
            preparedStatement.setString(1, caliber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                bullets.add(bullet);
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
        return bullets;
    }

    @Override
    public List<Bullet> findByName(String name) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.name"));
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                bullets.add(bullet);
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
        return bullets;
    }

    @Override
    public List<Bullet> findByType(String type) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.type"));
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                bullets.add(bullet);
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
        return bullets;
    }

    @Override
    public List<Bullet> findByPriceRange(int min, int max) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.priceRange"));
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(7));
                bullet.setId(resultSet.getInt(1));
                bullet.setUuid((UUID) resultSet.getObject(2));
                bullet.setDescription(resultSet.getString(8));
                bullets.add(bullet);
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
        return bullets;
    }

    @Override
    public void update(Bullet bullet) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.update"));
            preparedStatement.setString(1, bullet.getCaliber());
            preparedStatement.setString(2, bullet.getName());
            preparedStatement.setString(3, bullet.getType());
            preparedStatement.setDouble(4, bullet.getPrice());
            preparedStatement.setInt(5, bullet.getQty());
            preparedStatement.setString(6, bullet.getDescription());
            preparedStatement.setLong(7, bullet.getId());
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
    public void insert(Bullet bullet) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.insert"));
            preparedStatement.setObject(1, bullet.getUuid());
            preparedStatement.setString(2, bullet.getCaliber());
            preparedStatement.setString(3, bullet.getName());
            preparedStatement.setString(4, bullet.getType());
            preparedStatement.setDouble(5, bullet.getPrice());
            preparedStatement.setInt(6, bullet.getQty());
            preparedStatement.setString(7, bullet.getDescription());
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
    public boolean remove(Bullet bullet) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.delete"));
            preparedStatement.setLong(1, bullet.getId());
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
