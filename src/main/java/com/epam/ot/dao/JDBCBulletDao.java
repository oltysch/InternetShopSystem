package com.epam.ot.dao;

import com.epam.ot.entity.Bullet;
import com.epam.ot.util.PropertyManager;

import java.sql.*;
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

    private Bullet findFromResultSet(ResultSet resultSet) throws SQLException {
        Bullet bullet = new Bullet(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));
        bullet.setId(resultSet.getInt(1));
        bullet.setUuid((UUID) resultSet.getObject(2));
        bullet.setQty(resultSet.getInt(7));
        bullet.setDescription(resultSet.getString(8));
        return bullet;
    }

    private void makePreparedStatement(Bullet bullet, PreparedStatement preparedStatement, int startIndex) throws SQLException {
        preparedStatement.setString(startIndex, bullet.getCaliber());
        preparedStatement.setString(startIndex + 1, bullet.getName());
        preparedStatement.setString(startIndex + 2, bullet.getType());
        preparedStatement.setDouble(startIndex + 3, bullet.getPrice());
        Integer qty = bullet.getQty();
        if (qty != null) {
            preparedStatement.setInt(startIndex + 4, qty);
        } else {
            preparedStatement.setNull(startIndex + 4, Statement.SUCCESS_NO_INFO);
        }
        String description = bullet.getDescription();
        if (description != null) {
            preparedStatement.setString(startIndex + 5, description);
        } else {
            preparedStatement.setNull(startIndex + 5, Statement.SUCCESS_NO_INFO);
        }

    }

    @Override
    public List<Bullet> findAll() {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("bullets.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
                Bullet bullet = findFromResultSet(resultSet);
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
            makePreparedStatement(bullet, preparedStatement, 1);
            preparedStatement.setString(7, String.valueOf(bullet.getUuid()));
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
            makePreparedStatement(bullet, preparedStatement, 2);
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
            preparedStatement.setString(1, String.valueOf(bullet.getUuid()));
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
