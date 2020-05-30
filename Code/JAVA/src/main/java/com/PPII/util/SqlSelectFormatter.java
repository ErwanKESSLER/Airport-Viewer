package com.PPII.util;

import com.PPII.GUI.DatabaseGui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlSelectFormatter {
    private Connection connection;
    private String tables;
    private String conditions;
    private String entities;

    public SqlSelectFormatter(Connection connection, String tables, String conditions, String entities) {
        this.connection = connection;
        this.tables = tables;
        this.conditions = conditions;
        this.entities = entities;
    }

    public ResultSet prepareStatement() {
        ResultSet rs = null;
        String selectSQL;
        if (this.conditions != null) {
            selectSQL = "SELECT " + entities + " FROM " + tables + " WHERE " + conditions;
        } else {
            selectSQL = "SELECT " + entities + " FROM " + tables;
        }
        try {
            rs = connection.createStatement().executeQuery(selectSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseGui.println("Error "+e.getMessage());
        }
        return rs;
    }
}
