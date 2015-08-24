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
    public BulletDao createBulletDao() {
        return new JDBCBulletDao(connectionPool.getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(connectionPool.getConnection());
    }

    @Override
    public BulletsTypesDao createBulletsTypesDao() {
        return new JdbcBulletsTypesDao(connectionPool.getConnection());
    }

    @Override
    public GunsTypesDao createGunsTypesDao() {
        return new JdbcGunsTypesDao(connectionPool.getConnection());
    }

    @Override
    public UsersRolesDao createUsersRolesDao() {
        return new JdbcUsersRolesDao(connectionPool.getConnection());
    }
}
