package com.PPII.repository;


import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Plane;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneRepository extends Repository<Plane> {
    public PlaneRepository(SqlConnect db){
        this.db=db;
    }
    private Plane LocalizationInstancing(ResultSet rs) throws SQLException {
        return new Plane(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
    }
    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Plane","planeId="+id,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(LocalizationInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
    public void findAll(){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Plane",null,"*")).prepareStatement();
        try{
            while (rs.next()){
                addEntity(LocalizationInstancing(rs));
            }
            rs.close();
        }catch (SQLException e){
            DatabaseGui.println(e.getMessage());
        }
    }
}
