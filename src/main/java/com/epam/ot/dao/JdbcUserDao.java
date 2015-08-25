package com.epam.ot.dao;

import com.epam.ot.users.Role;
import com.epam.ot.users.User;
import com.epam.ot.util.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.all"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
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
    public User findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.select.id"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
                user.setId(resultSet.getInt(1));
                user.setUuid((UUID) resultSet.getObject(2));
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
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
                user.setId(resultSet.getInt(1));
                user.setUuid((UUID) resultSet.getObject(2));
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
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
                user.setId(resultSet.getLong(1));
                user.setUuid((UUID) resultSet.getObject(2));
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
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
                user.setId(resultSet.getLong(1));
                user.setUuid((UUID) resultSet.getObject(2));
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
                User user = new User(resultSet.getString(3), resultSet.getString(4), Role.valueOf(resultSet.getString(5)), resultSet.getString(6));
                user.setId(resultSet.getLong(1));
                user.setUuid((UUID) resultSet.getObject(2));
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
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, String.valueOf(user.getRole()));
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getUuid()));
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
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, String.valueOf(user.getRole()));
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
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(propertyManager.getProperty("users.delete"));
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setObject(2, user.getUuid());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
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
