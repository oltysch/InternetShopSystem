package com.epam.ot.dao;

import com.epam.ot.users.Role;
import com.epam.ot.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcUserDao implements UserDao {
    public static final String FIND_ALL = "SELECT * FROM USERS";
    public static final String FIND_BY_FIELD = "SELECT * FROM USERS WHERE ? = ?";
    public static final String FIND_BY_FIELD_RANGE = "SELECT * FROM USERS WHERE ? >= ? AND ? <= ?";
    //TODO make update all
    public static final String UPDATE_PASSWORD = "UPDATE USERS SET USERS.PASSWORD = ? WHERE LOGIN = ?";
    public static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    public static final String REMOVE_USER_BY_FIELD = "DELETE FROM USERS WHERE ?=?";

    private final Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findById(long id) {
        return findByField("id", id);
    }

    @Override
    public User findByUuid(UUID uuid) {
        return findByField("uuid", uuid);
    }

    @Override
    public User findByField(String field, Object value) {
        List<User> users = findArrayByField(field, value);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findArrayByField(String field, Object value) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), (Role) resultSet.getObject(5), resultSet.getString(6));
                user.setId(resultSet.getInt(1));
                user.setUuid((UUID) resultSet.getObject(2));
                users.add(user);
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
        return users;
    }

    @Override
    public List<User> findArrayByFieldRange(String field, Object minValue, Object maxValue) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FIELD_RANGE);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, minValue);
            preparedStatement.setString(3, field);
            preparedStatement.setObject(4, maxValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), (Role) resultSet.getObject(5), resultSet.getString(6));
                user.setId(resultSet.getInt(1));
                user.setUuid((UUID) resultSet.getObject(2));
                users.add(user);
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
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), (Role) resultSet.getObject(5), resultSet.getString(6));
                user.setId(resultSet.getInt(1));
                user.setUuid((UUID) resultSet.getObject(2));
                users.add(user);
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
        return users;
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
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
    public void insert(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setObject(1, user.getUuid());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setObject(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
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
    public boolean remove(User user) {
        return removeByField("id", user.getId());
    }

    @Override
    public boolean removeByField(String field, Object value) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_FIELD);
            preparedStatement.setString(1, field);
            preparedStatement.setObject(2, value);
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
}
