package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Airport;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class AirportRepository extends Repository<Airport>{
    public AirportRepository(SqlConnect db){
        this.db=db;
    }
    private Airport AirportInstancing(ResultSet rs) throws SQLException{
        return new Airport(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getInt(7),rs.getInt(8));
    }

    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Airport","id="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll(){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"airport",null,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByCountry(String Country){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"airport P, Localization L, Country C","P.CityId=L.CityId AND L.CountryId=C.CountryId AND CoutryName='"+Country+"'","P.*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByCity(String City){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"airport P, Localization L","P.CityId=L.CityId AND L.CityName='"+City+"'","P.*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }

    public void findByArea(double latCorner1,double lonCorner1,double latCorner2,double lonCorner2){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"airport P","P.Latitude>"+latCorner1+" AND P.Latitude<"+latCorner2+" AND P.longitude>"+lonCorner1+" AND P.Longitude<"+lonCorner2,"P.*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findByRadius(double latCenter,double lonCenter,double radius){
        //http://janmatuschek.de/LatitudeLongitudeBoundingCoordinates#PolesAnd180thMeridian
        double r=radius/6371;
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"airport P","acos(sin("+Math.toRadians(latCenter)+")*sin(P.latitude*3.14159265358979323846/180)+cos("+Math.toRadians(latCenter)+
                ")*cos(P.latitude*3.14159265358979323846/180)*cos(P.longitude*3.14159265358979323846/180-"+Math.toRadians(lonCenter)+"))<="+r,"P.*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(AirportInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }

}
