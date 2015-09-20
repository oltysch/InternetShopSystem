package com.epam.ot.dao;

public abstract class DaoFactory {
    public static DaoFactory getInstance() {
        return new JdbcDaoFactory();
    }

    public abstract GunDao createGunDao();

    public abstract BulletDao createBulletDao();

    public abstract UserDao createUserDao();

    public abstract BulletsTypesDao createBulletsTypesDao();

    public abstract GunsTypesDao createGunsTypesDao();

    public abstract UsersRolesDao createUsersRolesDao();
}
