package com.PPII.entities;

public class FlightNumber {
    //attributs
    private Integer id;
    private String flightNumber;
    private Integer airlineId;
    private Integer routeId;

    //constructeurs
    public FlightNumber() {
    }

    ;

    public FlightNumber(Integer id, String flightNumber, Integer airlineId, Integer routeId) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airlineId = airlineId;
        this.routeId = routeId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    @Override
    public String toString() {
        return this.id+"; "+this.flightNumber+"; "+this.airlineId+"; "+this.routeId;
    }
}
