package com.PPII.database.tables;

import com.PPII.GUI.DatabaseGui;
import com.PPII.parsers.FlightNumberParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateRouteList extends FlightNumberParser {
    private SqlRequestFormatter formatter;

    public PopulateRouteList(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 3, "RouteList");
    }

    public void runRouteList() {
        Parser parser = parse();
        try {
            //parser gives back (airline IATA OR ICAO, flight number, airports list)
            sendRouteList(parser.executeLexers(true, ","));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRouteList(Queue<String> data) throws IncorrectDataFormatException, ParsingException, MissingDataException {
        //construct alpha3 hashmap
        HashMap<String, Integer> airportIATA = (new AirportIdHelper()).airportIATAHashmap();
        HashMap<String, Integer> airportICAO = (new AirportIdHelper()).airportICAOHashmap();
        int routeId = 0;
        for (String element : data) {
            String[] parts = element.split(";;");
            try {
                String[] routeList = parts[2].split("-");
                for (int i = 0; i < routeList.length; i++) {
                    if (routeList[i].equals(" ")) {
                        throw new ParsingException("Error routelist " + parts[2]);
                    }

                    Integer aiportId = routeList[i].equals("NULL") ? null : airportIATA.get(routeList[i]);
                    if (aiportId == null) {
                        aiportId = airportICAO.get(routeList[i]);
                        if (aiportId == null) {
                            throw new MissingDataException("Airport ICAO/IATA : "+routeList[i]);
                        }
                    }
                    if (routeList.length - 1 == i) {
                        this.formatter.insertBatch(routeId, aiportId, null);
                    } else {
                        this.formatter.insertBatch(routeId, aiportId, routeId + 1);
                    }
                    routeId++;
                }

            } catch (NullPointerException e) {
                DatabaseGui.println(parts[0] + " id failed");
                e.printStackTrace();
            }
        }
        this.formatter.finishBatch();
    }
}
