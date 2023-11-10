package com.dattran.employeemanagement.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {

    //Get connection
    public Connection getConnection(String objectName) throws SQLException;
    //Release connection
    public void releaseConnection(Connection connection, String objectName) throws SQLException;
}