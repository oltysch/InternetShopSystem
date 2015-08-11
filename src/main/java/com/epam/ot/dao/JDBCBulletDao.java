package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JDBCBulletDao implements BulletDao {
    public static final String FIND_ALL = "SELECT * FROM BULLETS";
    public static final String FIND_BY_FIELD = "SELECT * FROM BULLETS WHERE ? = ?";
    public static final String FIND_BY_FIELD_RANGE = "SELECT * FROM BULLETS WHERE ? >= ? AND ? <= ?";
    //TODO make update all
    public static final String UPDATE_BULLET = "UPDATE BULLETS SET CALIBER=?, BULLETS.NAME=?, BULLET_TYPE=?, PRICE=?, QTY=?, DESCRIPTION=? WHERE ID=?";
    public static final String INSERT_BULLET = "INSERT INTO BULLETS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
    public static final String REMOVE_BULLET_BY_FIELD = "DELETE FROM BULLETS WHERE ?=?";

    private final Connection connection;

    public JDBCBulletDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bullet findById(long id) {
        return findByField("id", id);
    }

    @Override
    public Bullet findByUuid(UUID uuid) {
        return findByField("uuid", uuid);
    }

    @Override
    public Bullet findByField(String field, Object value) {
        List<Bullet> bullets = findArrayByField(field, value);
        if (bullets.size() > 0) {
            return bullets.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Bullet> findArrayByField(String field, Object value) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
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
    public List<Bullet> findArrayByFieldRange(String field, Object minValue, Object maxValue) {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD_RANGE);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, minValue);
            preparedStatement.setString(3, field);
            preparedStatement.setObject(4, maxValue);
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
    public List<Bullet> findAll() {
        List<Bullet> bullets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
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
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BULLET);
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
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BULLET);
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
        return removeByField("id", bullet.getId());
    }

    @Override
    public boolean removeByField(String field, Object value) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BULLET_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
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
