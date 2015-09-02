package com.epam.ot.connectionPool;

import com.epam.ot.util.PropertyManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    public static final Logger logger = Logger.getLogger(PropertyManager.class);
    private static ConnectionPool instance;
    private List<Connection> connectionList;
    private String driverName;
    private String url;
    private String login;
    private String password;
    private int maxConnections;

    private ConnectionPool(String driverName, String url, String login, String password, int maxConnections) {
        this.driverName = driverName;
        this.url = url;
        this.login = login;
        this.password = password;
        this.maxConnections = maxConnections;
        logger.info("Starting server:");
        logger.info("Driver: " + driverName);
        logger.info("Server: " + url);
        logger.info("Username: " + login);
        logger.info("Password: " + password);
        try {
            Class.forName(this.driverName);
            logger.info("Registered JDBC Driver");
            connectionList = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found!" + "\n" + e);
        }
        for (int i = 0; i < this.maxConnections; i++) {
            connectionList.add(newConnection());
        }
    }

    public static synchronized ConnectionPool getInstance(String driverName, String url, String login, String password, int maxConnections) {
        if ((driverName == null) || (url == null) || (login == null) || (password == null) || (maxConnections == 0))
            throw new IllegalArgumentException("Arguments can't contain null values");
        if (instance == null) {
            instance = new ConnectionPool(driverName, url, login, password, maxConnections);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        PooledConnection newConn = null;
        if (connectionList.isEmpty()) {
            newConn = new PooledConnection(newConnection(), instance);
        } else {
            Connection connection = connectionList.get(connectionList.size() - 1);
            try {
                if (connection == null || connection.isClosed()) {
                    connection = newConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            newConn = new PooledConnection(connection, instance);
            connectionList.remove(connection);
        }
        return newConn;
    }

    private Connection newConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
            logger.info("Created new connection in connetion pool");
        } catch (SQLException e) {
            logger.error("Can't create new connection for " + url + "\n" + e);
        }
        return conn;
    }

    public synchronized void putBack(Connection connection) {
        if (connection != null) {
            connectionList.add(connection);
            logger.info("Connection added to pool");
        }
    }

    public synchronized void release() {
        for (Connection currentConnection : connectionList) {
            try {
                currentConnection.close();
                logger.info("Closed connection for pool: " + currentConnection);
            } catch (SQLException e) {
                logger.error("Can't close connectin for pool: " + currentConnection);
            }
        }
    }


    public int getAvailableConnectionsCount() {
        return connectionList.size();
    }
}
