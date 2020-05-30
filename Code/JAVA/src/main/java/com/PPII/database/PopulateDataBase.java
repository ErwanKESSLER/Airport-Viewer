package com.PPII.database;


import com.PPII.GUI.DatabaseGui;
import com.PPII.database.tables.*;
import com.PPII.util.SqlConnect;


public class PopulateDataBase {
    private SqlConnect db;
    private long timer;
    private boolean optimization;

    public PopulateDataBase(CreateDataBase db,boolean optimization) {
        this.db = db.getDb();
        this.optimization=optimization;
    }

    public void run() {
        s("Countries");
        (new PopulateCountries(this.db)).runCountries();
        e("Countries");
        s("Localization");
        (new PopulateLocalization(this.db)).runLocalization();
        e("Localization");
        s("Airport");
        (new PopulateAirport(this.db)).runAirport();
        e("Airport");
        s("Planes");
        (new PopulatePlane(this.db)).runPlane();
        e("Planes");
        s("Airline");
        (new PopulateAirline(this.db)).runAirline();
        e("Airline");
        s("Routes");
        (new PopulateRoute(this.db)).runRoute();
        e("Routes");
        s("EquipmentList");
        (new PopulateEquipmentList(this.db)).runEquipmentList();
        e("EquipmentList");
        if (!optimization){
            s("FlightNumber");
            (new PopulateFlightNumber(this.db)).runFlightNumber();
            e("FlightNumber");
            s("RouteList");
            (new PopulateRouteList(this.db)).runRouteList();
            e("RouteList");
        }

    }


    private void s(String tableName) {
        DatabaseGui.println("Filling " + tableName + " table");
        this.timer = System.currentTimeMillis();
    }

    private void e(String tableName) {
       DatabaseGui.println(tableName + " table filled up in " + (System.currentTimeMillis() - this.timer) + " ms");

    }
}
