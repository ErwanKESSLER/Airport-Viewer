package com.PPII.database;

import com.PPII.GUI.DatabaseGui;
import com.PPII.util.SqlConnect;

import java.sql.SQLException;
import java.util.Scanner;

import static com.PPII.util.SqlConnect.displayeSet;

public class CreateDataBase {
    private SqlConnect db;
    private boolean optimization;

    public CreateDataBase(boolean dropTables, boolean createTables, boolean createConstraint, boolean optimization) {
        this.optimization = optimization;
        DatabaseGui.println("Database Connection ......");
        connectDatabase();
        DatabaseGui.println("Database connected");
        DatabaseGui.println("Database Creation ......");
        run(dropTables, createTables, createConstraint);
        DatabaseGui.println("Database was successfully created");

    }

    public CreateDataBase() {
        this.db = null;
        this.optimization = true;
    }

    public CreateDataBase(boolean all) {
        DatabaseGui.println("Database Connection ......");
        connectDatabase();
        DatabaseGui.println("Database connected");
        if (all) {
            optimization = false;
            DatabaseGui.println("Database Creation ......");
            run(all, all, all);
            DatabaseGui.println("Database was successfully created");

        }
    }

    public void setOptimization(boolean optimization) {
        this.optimization = optimization;
    }

    public void connectDatabase() {
        db = new SqlConnect();
        Scanner myObj = new Scanner(System.in);
        //DatabaseGui.println("Enter passwd");
        //String password = myObj.nextLine();

        //db.openConnection("oracle.telecomnancy.univ-lorraine.fr", "grp5", "lemotdepassedugroupe5", "TNCY",1521);
        db.openConnection("localhost", "hey", "root", "XE", 1522);
    }

    public SqlConnect getDb() {
        return this.db;
    }

    private void run(boolean dropTables, boolean createTables, boolean createConstraint) {

        if (dropTables) {
            db.executeUpdate("DROP TABLE Airport CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE Localization CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE Airline CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE Country CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE Route CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE plane CASCADE CONSTRAINTS");
            db.executeUpdate("DROP TABLE EquipmentList CASCADE CONSTRAINTS");
            if (!optimization) {
                db.executeUpdate("DROP TABLE RouteList CASCADE CONSTRAINTS");
                db.executeUpdate("DROP TABLE FlightNumber CASCADE CONSTRAINTS");
            }
        }
        if (createTables) {
            db.executeUpdate("CREATE TABLE Airport(" +
                    "  id number(5) PRIMARY KEY,\n" +
                    "  Name varchar2(200),\n" +
                    "  IATA varchar2(3),\n" +
                    "  ICAO varchar2(4),\n" +
                    "  latitude number,\n" +
                    "  longitude number,\n" +
                    "  altitude number,\n" +
                    "  CityId number NOT NULL)");
            db.executeUpdate("CREATE TABLE Localization\n" +
                    "(\n" +
                    "  CityId number PRIMARY KEY,\n" +
                    "  CityName varchar2(200),\n" +
                    "  CountryId varchar2(3) NOT NULL,\n" +
                    "  Timezone number\n" +
                    ")");
            db.executeUpdate("CREATE TABLE Country\n" +
                    "(\n" +
                    "  CountryId varchar2(4) PRIMARY KEY,\n" +
                    "  CoutryName varchar2(200),\n" +
                    "  CountryRealName varchar2(200),\n" +
                    "  Latitude number,\n" +
                    "  Longitude number\n" +
                    ")");
            db.executeUpdate("CREATE TABLE Airline\n" +
                    "(\n" +
                    "  id number PRIMARY KEY,\n" +
                    "  Name varchar2(200),\n" +
                    "  Alias varchar2(100),\n" +
                    "  IATA varchar2(2),\n" +
                    "  ICAO varchar2(3),\n" +
                    "  Callsign varchar2(200),\n" +
                    "  CountryId varchar2(3),\n" +
                    "  Active varchar2(1)\n" +
                    ")");
            db.executeUpdate("CREATE TABLE Route\n" +
                    "(\n" +
                    "  RouteId number PRIMARY KEY,\n" +
                    "  AirlineId number(5) NOT NULL,\n" +
                    "  SourceAirportId number(5) NOT NULL,\n" +
                    "  DestinationAirportId number(5) NOT NULL,\n" +
                    "  Escales number,\n" +
                    "  Codeshare varchar2(1),\n" +
                    "  EquipmentsId number\n" +
                    ")");
            db.executeUpdate("CREATE TABLE Plane\n" +
                    "(\n" +
                    "  PlaneId number PRIMARY KEY,\n" +
                    "  IATA varchar2(3),\n" +
                    "  ICAO varchar2(4),\n" +
                    "  Name varchar2(200)\n" +
                    ")");
            if (!optimization) {
                db.executeUpdate("CREATE TABLE RouteList\n" +
                        "(\n" +
                        "  RouteId number PRIMARY KEY,\n" +
                        "  AirportId number(5),\n" +
                        "  RouteIdNext number\n" +
                        ")");
                db.executeUpdate("CREATE TABLE FlightNumber\n" +
                        "(\n" +
                        "  Id number PRIMARY KEY,\n" +
                        "  FlightNumber varchar2(10),\n" +
                        "  AirlineID number,\n" +
                        "  RouteId number UNIQUE NOT NULL\n" +
                        ")");
            }

            db.executeUpdate("CREATE TABLE EquipmentList\n" +
                    "(\n" +
                    "  EquipmentId number PRIMARY KEY,\n" +
                    "  PlaneId number,\n" +
                    "  EquipmentNext number \n" +
                    ")");
        }

        if (createConstraint) {
            db.executeUpdate("ALTER TABLE airport ADD FOREIGN KEY (CityId) REFERENCES Localization (CityId)");
            db.executeUpdate("ALTER TABLE Localization ADD FOREIGN KEY (CountryId) REFERENCES Country (CountryId)");
            db.executeUpdate("ALTER TABLE Airline ADD FOREIGN KEY (CountryId) REFERENCES Country (CountryId)");
            db.executeUpdate("ALTER TABLE Route ADD FOREIGN KEY (AirlineId) REFERENCES Airline (id)");
            db.executeUpdate("ALTER TABLE Route ADD FOREIGN KEY (EquipmentsId) REFERENCES EquipmentList (EquipmentId)");
            db.executeUpdate("ALTER TABLE EquipmentList ADD FOREIGN KEY (EquipmentNext) REFERENCES EquipmentList (EquipmentId)");
            db.executeUpdate("ALTER TABLE EquipmentList  ADD FOREIGN KEY (PlaneId) REFERENCES plane (PlaneId)");
            db.executeUpdate("ALTER TABLE Route ADD FOREIGN KEY (SourceAirportId) REFERENCES airport (id)");
            db.executeUpdate("ALTER TABLE Route ADD FOREIGN KEY (DestinationAirportId) REFERENCES airport (id)");
            if (!optimization) {
                db.executeUpdate("ALTER TABLE FlightNumber ADD FOREIGN KEY (AirlineID) REFERENCES Airline (id)");
                db.executeUpdate("ALTER TABLE RouteList ADD FOREIGN KEY (AirportId) REFERENCES airport (id)");
                db.executeUpdate("ALTER TABLE FlightNumber ADD FOREIGN KEY (RouteId) REFERENCES RouteList (RouteId)");
                db.executeUpdate("ALTER TABLE RouteList ADD FOREIGN KEY (RouteIdNext) REFERENCES RouteList (RouteId)");
            }
        }
    }

    public void test() {
        try {
            db.executeRequest("insert into airport values (1,'Goroka Airport','GKA','AYGA',-6.081689834590001,145.391998291,5282,10)");
            displayeSet(db.executeRequest("select * from airport"));
            displayeSet(db.executeRequest("select * from airport"), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}