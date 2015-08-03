package com.epam.ot.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {
    private static ConnectionPool instance;
    private Vector<Connection> availableConnections = new Vector<>();
    private Vector<Connection> usedConnections = new Vector<>();
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
        try {
            Class.forName(this.driverName);
        } catch (Exception e) {
            throw new ConnectionPoolException(e);
        }
        for (int i = 0; i < this.maxConnections; i++) {
            availableConnections.addElement(getConnection());
        }
    }

    public static synchronized ConnectionPool getInstance(String driverName, String url, String login, String password, int maxConnections) {
        if (instance == null) {
            instance = new ConnectionPool(driverName, url, login, password, maxConnections);
        }
        return instance;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new ConnectionPoolException(e);
        }
        return conn;
    }

    public synchronized Connection retrieve() {
        Connection newConn = null;
        if (availableConnections.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConnections.lastElement();
            availableConnections.removeElement(newConn);
        }
        usedConnections.addElement(newConn);
        return newConn;
    }

    public synchronized void putBack(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConnections.removeElement(c)) {
                availableConnections.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConnections");
            }
        }
    }

    public int getAvailableConnectionsCount() {
        return availableConnections.size();
    }
}
