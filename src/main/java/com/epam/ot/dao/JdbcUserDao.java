package com.epam.ot.dao;

import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;
import com.epam.ot.util.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcUserDao implements UserDao {
    private final Connection connection;
    private final PropertyManager propertyManager;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
        propertyManager = new PropertyManager("query.properties");
    }

    private User findFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
        user.setId(resultSet.getLong(1));
        user.setUuid((UUID) resultSet.getObject(2));
        user.setCash(resultSet.getDouble(7));
        user.setBanned(resultSet.getBoolean(8));
        user.setXid(resultSet.getString(9));
        user.setCart(resultSet.getString(10));
        return user;
    }

    private void makePreparedStatement(User user, PreparedStatement preparedStatement, int startIndex) throws SQLException {
        preparedStatement.setString(startIndex, user.getLogin());
        preparedStatement.setString(startIndex + 1, user.getEmail());
        preparedStatement.setString(startIndex + 2, String.valueOf(user.getRole()));
        preparedStatement.setString(startIndex + 3, user.getPassword());
        preparedStatement.setDouble(startIndex + 4, user.getCash());
        preparedStatement.setBoolean(startIndex + 5, user.isBanned());
        String xid = user.getXid();
        if (xid != null) {
            preparedStatement.setString(startIndex + 6, xid);
        } else {
            preparedStatement.setNull(startIndex + 6, Statement.SUCCESS_NO_INFO);
        }
        String cart = user.getCart();
        if (cart != null) {
            preparedStatement.setString(startIndex + 7, cart);
        } else {
            preparedStatement.setNull(startIndex + 7, Statement.SUCCESS_NO_INFO);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = findFromResultSet(resultSet);
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
    public User findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public User findByUuid(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.uuid"));
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.login"));
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.email"));
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public User findByRole(String role) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.role"));
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public User findByXid(String xid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.xid"));
            preparedStatement.setString(1, xid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = findFromResultSet(resultSet);
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
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.update"));
            makePreparedStatement(user, preparedStatement, 1);
            preparedStatement.setString(9, String.valueOf(user.getUuid()));
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
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.insert"));
            preparedStatement.setObject(1, user.getUuid());
            makePreparedStatement(user, preparedStatement, 2);
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
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.delete"));
            preparedStatement.setObject(1, user.getUuid());
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
