package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Airport;
import com.PPII.entities.Route;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRepository extends Repository<Route> {
    public RouteRepository(SqlConnect db){
        this.db=db;
    }
    private Route RouteInstancing(ResultSet rs) throws SQLException{
        return new Route(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7));
    }
    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Route","routeId="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(RouteInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll(){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Route",null,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(RouteInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findBySourceAirport(Integer airportId){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Route","sourceairportid="+airportId,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(RouteInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByDestinationAirport(Integer airportId){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Route","destinationAirportId="+airportId,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(RouteInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByAirline(Integer airlineId){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Route","airlineId="+airlineId,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(RouteInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
}
