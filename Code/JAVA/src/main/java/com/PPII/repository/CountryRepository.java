package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Countries;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRepository extends Repository<Countries> {
    public CountryRepository(SqlConnect db){
        this.db=db;
    }
    private Countries CountryInstancing(ResultSet rs) throws SQLException {
        return new Countries(rs.getString(1), rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
    }

    public void findById(String id) {
        ResultSet rs = (new SqlSelectFormatter(this.db.getConnection(), "Country", "Countryid='" + id+"'", "*")).prepareStatement();
        try {
            while (rs.next()) {
                addEntity(CountryInstancing(rs));
            }
            rs.close();
        } catch (SQLException e) {
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll() {
        ResultSet rs = (new SqlSelectFormatter(this.db.getConnection(), "Country", null, "*")).prepareStatement();
        try {
            while (rs.next()) {
                addEntity(CountryInstancing(rs));
            }
            rs.close();
        } catch (SQLException e) {
            DatabaseGui.println(e.getMessage());
        }
    }
}
