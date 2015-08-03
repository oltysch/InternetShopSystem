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

    /*public JdbcDaoFactory(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }*/

    @Override
    public DaoManager createDaoManager() {
        return new DaoManager(connectionPool.retrieve());
    }

    @Override
    public void releaseConnection(DaoManager daoManager) {
        Connection connection = daoManager.getConnection();
        connectionPool.putBack(daoManager.getConnection());
    }

    /*@Override
    public GunDao createGunDao() {
        return new JdbcGunDao(connectionPool.retrieve());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(connectionPool.retrieve());
    }*/

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
