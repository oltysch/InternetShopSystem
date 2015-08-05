package com.epam.ot.dao;

import com.epam.ot.connectionPool.ConnectionPool;
import com.epam.ot.util.PropertyManager;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {
    private PropertyManager propertyManager = new PropertyManager("connection.properties");
    private final ConnectionPool connectionPool = ConnectionPool.getInstance(
            propertyManager.getProperty("driverName"),
            propertyManager.getProperty("url"),
            propertyManager.getProperty("login"),
            propertyManager.getProperty("password"),
            Integer.parseInt(propertyManager.getProperty("maxConnections")));

    @Override
    public GunDao createGunDao() {
        return new JdbcGunDao(connectionPool.getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(connectionPool.getConnection());
    }

    /*@Override
    public void beginConnectionScope() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void endConnectionScope() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }*/
}
