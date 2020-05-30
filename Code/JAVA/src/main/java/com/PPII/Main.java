package com.PPII;


import com.PPII.database.CreateDataBase;
import com.PPII.database.PopulateDataBase;
import com.PPII.exports.JSONexport;
import com.PPII.exports.SelectInterestingData;
import com.PPII.repository.EquipmentListRepository;
import com.PPII.repository.RouteRepository;

public class Main {
    public static void main(String[] args) {
       /* CreateDataBase db= new CreateDataBase(true,true,false,false);
        (new PopulateDataBase(db,true)).run();
        db= new CreateDataBase(false,false,true,false);
        /*EquipmentListRepository equipmentListRepository=new EquipmentListRepository(db.getDb());
        equipmentListRepository.findById(67090);
        equipmentListRepository.displayEntities();
        /*DatabaseGui.println("by source airport");
        RouteRepository routeRepository = new RouteRepository();
        routeRepository.findBySourceAirport(4067);
        routeRepository.displayEntities();
        DatabaseGui.println("by airline");
        routeRepository = new RouteRepository();
        routeRepository.findByAirline(2556);
        routeRepository.displayEntities();
        DatabaseGui.println("by destination airport");
        routeRepository = new RouteRepository();
        routeRepository.findByDestinationAirport(3550);
        routeRepository.displayEntities();
        DatabaseGui.println("by route id");
        routeRepository = new RouteRepository();
        routeRepository.findById(66194);
        routeRepository.displayEntities();
        /*AirportRepository airportRepository=new AirportRepository();
        airportRepository.findByRadius(50.0,50.0,1000);
        airportRepository.displayEntities();*/

        //RouteRepository routeRepository=(new SelectInterestingData("Paris","New York")).possibleRoute();
        //routeRepository.displayEntities();
        //new JSONexport(db.getDb());
    }

}