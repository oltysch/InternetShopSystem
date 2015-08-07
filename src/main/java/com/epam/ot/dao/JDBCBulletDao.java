package com.epam.ot.dao;

import com.epam.ot.products.Bullet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCBulletDao implements BulletDao {
    public static final String FIND_ALL = "SELECT * FROM BULLETS";
    public static final String FIND_BY_ID = "SELECT * FROM BULLETS WHERE id = ?";
    public static final String FIND_BY_CALIBER = "SELECT * FROM BULLETS WHERE BULLETS.CALIBER=?";
    public static final String FIND_BY_NAME = "SELECT * FROM BULLETS WHERE BULLETS.NAME=?";
    public static final String FIND_BY_TYPE = "SELECT * FROM BULLETS WHERE BULLET_TYPE=?";
    public static final String FIND_BY_PRICE_RANGE = "SELECT * FROM BULLETS WHERE BULLETS.PRICE>=? AND BULLETS.PRICE<=?";
    public static final String INSERT_BULLET = "INSERT INTO BULLETS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
    public static final String REMOVE_BULLET_BY_ID = "DELETE FROM BULLETS WHERE BULLETS.ID=?;";
    public static final String REMOVE_BULLET = "DELETE FROM BULLETS WHERE BULLETS.ID=? AND BULLETS.CALIBER=? AND BULLETS.NAME=?";
    //    public static final String UPDATE_BULLET = "UPDATE BULLETS SET VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) WHERE ID = ?";
    public static final String UPDATE_BULLET = "UPDATE BULLETS SET CALIBER=?, BULLETS.NAME=?, BULLET_TYPE=?, PRICE=?, QTY=?, DESCRIPTION=? WHERE ID=?";
    private final Connection connection;

    public JDBCBulletDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bullet findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CALIBER);
            preparedStatement.setString(1, caliber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TYPE);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRICE_RANGE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
                Bullet bullet = new Bullet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getInt(6));
                bullet.setId(resultSet.getInt(1));
                bullet.setDescription(resultSet.getString(7));
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
            preparedStatement.setInt(7, bullet.getId());
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
            preparedStatement.setString(1, bullet.getCaliber());
            preparedStatement.setString(2, bullet.getName());
            preparedStatement.setString(3, bullet.getType());
            preparedStatement.setDouble(4, bullet.getPrice());
            preparedStatement.setInt(5, bullet.getQty());
            preparedStatement.setString(6, bullet.getDescription());
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
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BULLET);
            preparedStatement.setInt(1, bullet.getId());
            preparedStatement.setString(2, bullet.getCaliber());
            preparedStatement.setString(3, bullet.getName());
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
    public boolean removeById(int id) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BULLET_BY_ID);
            preparedStatement.setInt(1, id);
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
