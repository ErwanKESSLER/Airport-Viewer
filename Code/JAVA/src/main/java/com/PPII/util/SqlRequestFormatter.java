package com.PPII.util;

import com.PPII.GUI.DatabaseGui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SqlRequestFormatter {
    private Connection connection;
    private PreparedStatement stm;
    private int NumberBatch;
    private int NumberValue = 0;
    private String Table;

    public SqlRequestFormatter(Connection connection, int numberValue, String table) {
        this.connection = connection;
        this.NumberValue = numberValue;
        this.Table = table;
        this.NumberBatch = 0;
        prepareStatement();
    }

    private void prepareStatement() {
        StringBuilder valuesString = new StringBuilder();
        for (int i = 0; i < this.NumberValue; i++) {
            valuesString.append("?");
            if (i != this.NumberValue - 1) {
                valuesString.append(',');
            } else {
                valuesString.append(')');
            }
        }
        try {
            //we create the statement from the table name and the correct parameterized string
            this.connection.setAutoCommit(false);
            this.stm = this.connection.prepareStatement("INSERT INTO " + this.Table + " VALUES(" + valuesString.toString());
        } catch (SQLException e) {
            DatabaseGui.println("Failed on : INSERT INTO " + this.Table + " VALUES(" + valuesString.toString());
            e.printStackTrace();
        }
    }

    private void parseValue(Object... values) {
        int index = 1;
        try {
            //we fill the prepared statement with the objects as strings
            for (Object val : values) {
                if (val == null) {
                    this.stm.setNull(index, Types.VARCHAR);
                } else if (val.toString().equals("NULL")) {
                    this.stm.setNull(index, Types.VARCHAR);
                } else if (val instanceof Double) {
                    this.stm.setDouble(index, (Double) val);
                } else if (val instanceof Float) {
                    this.stm.setFloat(index, (Float) val);
                } else if (val instanceof Integer) {
                    this.stm.setInt(index, (Integer) val);
                } else if (val instanceof String) {
                    this.stm.setString(index, (String) val);
                } else {
                    this.stm.setString(index, val.toString());
                }
                index++;
            }
        } catch (SQLException e) {
            DatabaseGui.println("Failed on " + values[0] + " on index " + index);
            e.printStackTrace();
        }
    }

    public int insertValues(Object... values) {
        try {
            parseValue(values);
            //we return how many lines are affected
            int numberOfUpdates = stm.executeUpdate();
            this.connection.commit();
            stm.close();
            prepareStatement();
            return numberOfUpdates;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void insertBatch(Object... values) {
        int maxNumberBatch = 1000;
        try {
            parseValue(values);
            this.stm.addBatch();
            this.NumberBatch++;
            if (this.NumberBatch > maxNumberBatch) {
                int[] temp = stm.executeBatch();
                this.connection.commit();
                stm.close();
                prepareStatement();
                this.NumberBatch = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finishBatch() {
        try {
            int[] temp = stm.executeBatch();
            this.connection.commit();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
