package com.dattran.employeemanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
    private String url;
    private String driverName;
    private String username;
    private String password;

    private Stack<Connection> pool;

    public ConnectionPoolImpl() {
        this.driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(this.driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.url = "jdbc:mysql://localhost:3306/it6020003_data";
        this.username = "root";
        this.password = "Tranvandat29@";
        this.pool = new Stack<>();
    }

    @Override
    public Connection getConnection(String objectName) throws SQLException {
        if (this.pool.isEmpty()) {
            System.out.println(objectName+" have created a new Connection.");
            return DriverManager.getConnection(this.url, this.username, this.password);
        } else {
            System.out.println(objectName+" have popped the Connection.");
            return this.pool.pop();
        }
    }

    @Override
    public void releaseConnection(Connection connection, String objectName) throws SQLException {
        System.out.println(objectName+" have pushed the Connection.");
        this.pool.push(connection);
    }

    protected void finalizes() throws Throwable {
        this.pool.clear();
        this.pool=null;
        System.out.println("ConnectionPool is closed.");
    }
}
