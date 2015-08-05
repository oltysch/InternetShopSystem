package com.epam.ot.dao;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {
    private final Connection connection;
    private GunDao gunDao = null;
    private UserDao userDao = null;

    public DaoManager(Connection connection) {
        this.connection = connection;
    }

    public UserDao getUserDao() {
        userDao = new JdbcUserDao(connection);
        return userDao;
    }

    public GunDao getGunDao() {
        gunDao = new JdbcGunDao(connection);
        return gunDao;
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void beginTransaction() {
        PooledConnection pooledConnection;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
