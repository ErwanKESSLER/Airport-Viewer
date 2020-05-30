package com.PPII.database.tables;

import com.PPII.GUI.DatabaseGui;
import com.PPII.parsers.Parser;
import com.PPII.parsers.RouteParser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateRoute extends RouteParser {
    private SqlRequestFormatter formatter;

    public PopulateRoute(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 7, "Route");
    }

    public void runRoute() {
        Parser parser = parse();
        try {
            sendRoute(parser.executeLexers(false, ","));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRoute(Queue<String> data) throws MissingDataException, ParsingException, IncorrectDataFormatException {
        HashMap<Integer, Integer> airlineIds = (new AirlineIdHelper()).airlineIdHashmap();
        HashMap<String, Integer> airlineIATAs = (new AirlineIdHelper()).airlineIATAHashmap();
        HashMap<String, Integer> airlineICAOs = (new AirlineIdHelper()).airlineICAOHashmap();
        HashMap<Integer, Integer> airportIds = (new AirportIdHelper()).aiportIdHashmap();
        HashMap<String, Integer> airportIATAs = (new AirportIdHelper()).airportIATAHashmap();
        HashMap<String, Integer> airportICAOs = (new AirportIdHelper()).airportICAOHashmap();
        int routeId = 0;
        int equipmentId = 0;
        for (String element : data) {
            String[] parts = element.split(";;");
            Integer airlineId = parts[1].equals("NULL") ? null : airlineIds.get(Integer.parseInt(parts[1]));
            if (airlineId == null) {
                airlineId = airlineIATAs.get(parts[0]);
                if (airlineId == null) {
                    airlineId = airlineICAOs.get(parts[0]);
                    if (airlineId == null) {
                        throw new MissingDataException("ICAO IATA and Airline ID didnt match : " + parts[0]);
                    }
                }
            }
            Integer sourceAirportId = parts[3].equals("NULL") ? null : airportIds.get(Integer.parseInt(parts[3]));
            if (sourceAirportId == null) {
                sourceAirportId = airportIATAs.get(parts[2]);
                if (sourceAirportId == null) {
                    sourceAirportId = airportICAOs.get(parts[2]);
                    if (sourceAirportId == null) {
                        throw new MissingDataException("ICAO IATA and Source Airport ID didnt match : " + parts[2] + " " + parts[3]);
                    }
                }
            }
            Integer destinationAirportId = parts[5].equals("NULL") ? null : airportIds.get(Integer.parseInt(parts[5]));
            if (destinationAirportId == null) {
                destinationAirportId = airportIATAs.get(parts[4]);
               if (destinationAirportId == null) {
                    destinationAirportId = airportICAOs.get(parts[4]);
                    if (destinationAirportId == null) {
                        throw new MissingDataException("ICAO IATA and Destination Airport ID didnt match : " + parts[4] + " " + parts[5]);
                    }
                }
            }

            formatter.insertBatch(routeId, airlineId, sourceAirportId, destinationAirportId, parts[7], parts[6], equipmentId);
            routeId++;
            if (parts.length!=9){
                DatabaseGui.println(element);
            }
            String[] equipmentList = parts[8].split(" ");
            for (String el : equipmentList) {
                if (el.equals(" ")) {
                    throw new ParsingException("Error equipments "+parts[8] + " "+parts[0]);
                }
            }
            equipmentId += equipmentList.length;

        }
        this.formatter.finishBatch();
    }
}
