package com.PPII.util;

import com.PPII.GUI.DatabaseGui;

import java.sql.*;

public class SqlConnect {
    private Connection connection;
    private Statement statement;

    public SqlConnect() {
    }

    public SqlConnect(String host, String user, String password) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connection = DriverManager.getConnection(
                    String.format("jdbc:oracle:thin:@%s:1521:xe", host), user, password);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                connection.close();
                DatabaseGui.println("Database connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void openConnection(String host, String user, String password, String serviceName,int port) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connection = DriverManager.getConnection(
                    String.format("jdbc:oracle:thin:@//%s:%d/%s", host,port,serviceName), user, password);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeRequest(String request) {
        if (this.statement == null) {
            return null;
        }
        try {
            return this.statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String request) {
        if (this.statement == null) {
            return -1;
        }
        try {
            return this.statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void displayeSet(ResultSet set,int row) throws SQLException {
        while (set.next()) {
            byte rows = set.getByte(row);
            DatabaseGui.println(rows);
        }
    }
    public static void displayeSet(ResultSet set) throws SQLException {
        while (set.next()) {
            int row = set.getRow();
            DatabaseGui.println(row);
        }
    }


}