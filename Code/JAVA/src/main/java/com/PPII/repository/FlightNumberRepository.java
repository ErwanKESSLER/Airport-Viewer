package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.FlightNumber;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightNumberRepository extends Repository<FlightNumber> {
    public FlightNumberRepository(SqlConnect db){
        this.db=db;
    }
    private FlightNumber FlightNumberInstancing(ResultSet rs) throws SQLException {
        return new FlightNumber(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
    }
    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"FlightNumber","id="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(FlightNumberInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll(){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"FlightNumber",null,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(FlightNumberInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByAirline(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"FlightNumber","airlineId="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(FlightNumberInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
}
