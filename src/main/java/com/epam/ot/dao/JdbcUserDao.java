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
    public static final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    public static final String FIND_BY_UUID = "SELECT * FROM USERS WHERE uuid = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM USERS WHERE LOGIN = ?";
    public static final String FIND_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = ?";
    public static final String FIND_BY_ROLE = "SELECT * FROM USERS WHERE ROLE = ?";
    public static final String UPDATE_USER = "UPDATE USERS SET USERS.EMAIL=?, USERS.ROLE=?, USERS.PASSWORD = ? WHERE LOGIN = ?";
    public static final String INSERT_USER = "INSERT INTO USERS VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    public static final String REMOVE_USER = "DELETE FROM USERS WHERE ID=?";

    private final Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                //TODO add email
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_UUID);
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                //TODO add email
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);
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
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ROLE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, String.valueOf(user.getRole()));
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getLogin());
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
}
