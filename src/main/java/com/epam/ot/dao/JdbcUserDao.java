package com.epam.ot.dao;

import com.epam.ot.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcUserDao implements UserDao {
    public static final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    public static final String FIND_BY_UUID = "SELECT * FROM USERS WHERE uuid = ?";
    public static final String FIND_BY_ACCOUNT = "SELECT * FROM USERS WHERE LOGIN = ? AND USERS.PASSWORD = ?";
    public static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?, ?)";
    public static final String UPDATE_PASSWORD = "UPDATE USERS SET USERS.PASSWORD = ? WHERE LOGIN = ?";
    public static final String REMOVE_USER = "DELETE FROM USERS WHERE ID=? AND USERS.UUID=? AND LOGIN=? AND USERS.PASSWORD=?";
    public static final String FIND_ALL = "SELECT * FROM USERS";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM USERS WHERE ID=?";
    public static final String REMOVE_USER_BY_UUID = "DELETE FROM USERS WHERE USERS.UUID=?";
    public static final String REMOVE_USER_BY_LOGIN = "DELETE FROM USERS WHERE LOGIN=?";
    private final Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                //TODO add email
                User user = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_UUID);
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                //TODO add email
                User user = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
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
    public User findByAccount(String login, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ACCOUNT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
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
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
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
    public void updatePassword(User user) {
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
            preparedStatement.setString(4, user.getPassword());
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
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);
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
    public boolean removeById(long id) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setLong(1, id);
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
    public boolean removeByUuid(UUID uuid) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_UUID);
            preparedStatement.setObject(1, uuid);
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
    public boolean removeByLogin(String login) {
        boolean res = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
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
