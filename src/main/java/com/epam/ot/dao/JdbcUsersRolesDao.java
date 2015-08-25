package com.epam.ot.dao;

import com.epam.ot.util.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUsersRolesDao implements UsersRolesDao {
    private final Connection connection;
    private final PropertyManager propertyManager;

    public JdbcUsersRolesDao(Connection connection) {
        this.connection = connection;
        this.propertyManager = new PropertyManager("query.properties");
    }

    @Override
    public List<String> findAll() {
        List<String> types = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.roles.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString(1));
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
        return types;
    }

    @Override
    public void insert(String string) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.roles.insert"));
            preparedStatement.setString(1, string);
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
    public boolean remove(String string) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.roles.delete"));
            preparedStatement.setString(1, string);
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
