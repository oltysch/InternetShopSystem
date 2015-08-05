package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {
    public static final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    public static final String FIND_BY_ACCOUNT = "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
    public static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?)";
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void insert(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
