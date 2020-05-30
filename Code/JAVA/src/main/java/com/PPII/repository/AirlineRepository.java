package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Airline;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirlineRepository extends Repository<Airline> {
    public AirlineRepository(SqlConnect db){
        this.db=db;
    }
    private Airline AirlineInstancing(ResultSet rs) throws SQLException {
        return new Airline(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
    }
    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Airline","id="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirlineInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll(){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Airline",null,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirlineInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
}
