package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {
    public static final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    public static final String FIND_BY_ACCOUNT = "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
    private final Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByAccount(String login, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ACCOUNT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            //TODO find problem, result user always null
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = new User(resultSet.getString(2), "", resultSet.getString(3));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User merge(User user) {
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }
}
