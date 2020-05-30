package com.PPII.repository;

import com.PPII.GUI.DatabaseGui;
import com.PPII.entities.Localization;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalizationRepository extends Repository<Localization> {
    public LocalizationRepository(SqlConnect db){
        this.db=db;
    }
    private Localization LocalizationInstancing(ResultSet rs) throws SQLException{
        return new Localization(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
    }
    public void findById(Integer id){
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Localization","Cityid="+id,"*")).prepareStatement();
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
        ResultSet rs=(new SqlSelectFormatter(this.db.getConnection(),"Localization",null,"*")).prepareStatement();
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
