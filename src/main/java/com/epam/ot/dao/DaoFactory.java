package com.epam.ot.dao;

import com.epam.ot.connectionPool.ConnectionPool;

public abstract class DaoFactory {
    public static DaoFactory getInstance() {
        return new JdbcDaoFactory();
    }

    public abstract GunDao createGunDao();

    public abstract BulletDao createBulletDao();

    public abstract UserDao createUserDao();

    /*public abstract void beginConnectionScope();

    public abstract void beginTransaction();

    public abstract void endTransaction();

    public abstract void endConnectionScope();*/
}
