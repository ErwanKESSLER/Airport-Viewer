package com.PPII.repository;

import com.PPII.entities.RouteListe;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlSelectFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteListeRepository extends Repository<RouteListe> {
    public RouteListeRepository(SqlConnect db){
        this.db=db;
    }
    public void findById(Integer id) {
        int range = 0;
        ResultSet rs = (new SqlSelectFormatter(this.db.getConnection(), "RouteList", "routeId=" + id, "*")).prepareStatement();
        try {
            RouteListe routeListe = new RouteListe();
            rs.next();
            routeListe.setRouteId(rs.getInt(1));
            routeListe.addAirportId(rs.getInt(2));
            while (rs.getString(3) != null) {
                rs = (new SqlSelectFormatter(this.db.getConnection(), "RouteList", "routeId=" + rs.getInt(3), "*")).prepareStatement();

                rs.next();
                routeListe.addAirportId(rs.getInt(2));
                range++;
            }
            routeListe.setRange(range);
            addEntity(routeListe);
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
