package com.PPII.exports;

import com.PPII.entities.Airport;
import com.PPII.entities.Route;
import com.PPII.repository.AirportRepository;
import com.PPII.repository.RouteRepository;
import com.PPII.util.SqlConnect;

import java.util.ArrayList;

public class SelectInterestingData {
    private String start;
    private String end;
    public SelectInterestingData(String fromCity,String toCity){
        this.start=fromCity;
        this.end=toCity;
    }
    public AirportRepository StartingAirports(SqlConnect db){
        AirportRepository airportRepository=new AirportRepository(db);
        airportRepository.findByCity(start);
        return airportRepository;
    }
    public AirportRepository EndingAirports(SqlConnect db){
        AirportRepository airportRepository=new AirportRepository(db);
        airportRepository.findByCity(end);
        return airportRepository;
    }

    public RouteRepository possibleRoute(SqlConnect db){
        AirportRepository startingAirports=StartingAirports(db);
        AirportRepository endingAirports=EndingAirports(db);
        RouteRepository possibleRoute=new RouteRepository(db);
        for (Airport startAirport:startingAirports.getList()){
            RouteRepository startingRoute=new RouteRepository(db);
            startingRoute.findBySourceAirport(startAirport.getId());
            for (Route route:startingRoute.getList()){
                for (Airport endAirport:endingAirports.getList()){
                    if (route.getDestinationAirportId().equals(endAirport.getId())){
                        possibleRoute.addEntity(route);
                    }
                }
            }
        }
        return possibleRoute;
    }



}
