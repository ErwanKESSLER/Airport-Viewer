package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Airport {

    //Attributs
    private Integer id;
    private String name;
    private String iata;
    private String icao;
    private double latitude;
    private double longitude;
    private Integer altitude;
    private Integer cityId;

    //Constructeur(s)
    public Airport() {
    }

    ;

    public Airport(Integer id, String name, String iata, String icao, double latitude, double longitude, Integer altitude, Integer cityId) {
        this.id = id;
        this.name = name;
        this.iata = iata;
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.cityId = cityId;
    }

    //Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id > 99999) {
            DatabaseGui.println("id trop grand");
        } else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 200) {
            DatabaseGui.println("name trop grand");
        } else this.name = name;
        ;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        if (iata.length() > 3) {
            DatabaseGui.println("iata trop grand");
        } else this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        if (icao.length() > 4) {
            DatabaseGui.println("icao trop grand");
        } else this.icao = icao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return this.id + "; " + this.name + "; " + this.iata + "; " + this.icao + "; " + this.latitude + "; " + this.longitude + "; " + this.altitude + "; " + this.cityId;
    }
}
