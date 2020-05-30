package com.PPII.exports;

import com.PPII.entities.*;
import com.PPII.repository.*;
import com.PPII.util.IncorrectDataFormatException;
import com.PPII.util.SqlConnect;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JSONexport {
    public JSONexport(SqlConnect db){
        try {
            run(db);
        }catch (IncorrectDataFormatException e){
            e.printStackTrace();
        }
    }
    private void run(SqlConnect db) throws IncorrectDataFormatException{

        JSONObject json = new JSONObject();
        RouteRepository routeRepository=new RouteRepository(db);
        routeRepository.findAll();
        for (Route route:routeRepository.getList()){
            //starting end
            JSONObject airportStart=new JSONObject();
            AirportRepository startingAirport=new AirportRepository(db);
            startingAirport.findById(route.getSourceAirportId());
            if (startingAirport.getList().size()!=1){
                throw new IncorrectDataFormatException("Starting Airport have two similar ids in database");
            }
            Airport start=startingAirport.getList().get(0);
            airportStart.put("actualId",start.getId());
            airportStart.put("longitude",start.getLongitude());
            airportStart.put("latitude",start.getLatitude());
            airportStart.put("name",start.getName());
            LocalizationRepository localizationRepository1=new LocalizationRepository(db);
            localizationRepository1.findById(start.getCityId());
            if (localizationRepository1.getList().size()!=1){
                throw new IncorrectDataFormatException("Starting Airport has 2 or more city attached in the database");
            }
            airportStart.put("cityName",localizationRepository1.getList().get(0).getCityName());

            //other end
            JSONObject airportEnd=new JSONObject();
            AirportRepository endingAirport=new AirportRepository(db);
            endingAirport.findById(route.getDestinationAirportId());
            if (endingAirport.getList().size()!=1){
                throw new IncorrectDataFormatException("Ending Airport have two similar ids in database");
            }
            Airport end=endingAirport.getList().get(0);
            airportEnd.put("actualId",end.getId());
            airportEnd.put("longitude",end.getLongitude());
            airportEnd.put("latitude",end.getLatitude());
            airportEnd.put("name",end.getName());
            LocalizationRepository localizationRepository2=new LocalizationRepository(db);
            localizationRepository2.findById(end.getCityId());
            if (localizationRepository2.getList().size()!=1){
                throw new IncorrectDataFormatException("Ending Airport has 2 or more city attached in the database");
            }
            airportStart.put("cityName",localizationRepository2.getList().get(0).getCityName());

            JSONObject airline=new JSONObject();
            AirlineRepository airlineRepository=new AirlineRepository(db);
            airlineRepository.findById(route.getAirlineId());
            if (airlineRepository.getList().size()!=1){
                throw new IncorrectDataFormatException("Route has 2 or more airline attached in the database");
            }
            Airline airline1=airlineRepository.getList().get(0);
            airline.put("id",airline1.getId());
            airline.put("Name",airline1.getName());
            airline.put("Alias",airline1.getAlias());

            JSONObject routeObject=new JSONObject();

            routeObject.put("Airline",airline);
            routeObject.put("StartAirport",airportStart);
            routeObject.put("EndAiport",airportEnd);
            routeObject.put("escale",route.getEscales());

            json.put(route.getRouteId().toString(),routeObject);
        }
        try{
            PrintWriter out =new PrintWriter("routes.json");
            out.println(json.toString(3));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        db.closeConnection();

    }


}
