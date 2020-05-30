package com.PPII.repository;

import com.PPII.entities.EquipmentList;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentListRepository extends Repository<EquipmentList> {
    public EquipmentListRepository(SqlConnect db){
        this.db=db;
    }
    public void findById(Integer id) {
        int range = 0;
        ResultSet rs = (new SqlSelectFormatter(this.db.getConnection(), "EquipmentList", "EquipmentId=" + id, "*")).prepareStatement();
        try {
            EquipmentList equipmentList = new EquipmentList();
            rs.next();
            equipmentList.setEquipmentId(rs.getInt(1));
            equipmentList.addPlaneId(rs.getInt(2));
            while (rs.getString(3) != null) {
                rs = (new SqlSelectFormatter(this.db.getConnection(), "EquipmentList", "EquipmentId=" + rs.getInt(3), "*")).prepareStatement();

                rs.next();
                equipmentList.addPlaneId(rs.getInt(2));
                range++;
            }
            equipmentList.setRange(range);
            addEntity(equipmentList);
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
