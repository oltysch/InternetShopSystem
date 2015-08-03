package com.epam.ot.dao;

import java.sql.Connection;

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
}
