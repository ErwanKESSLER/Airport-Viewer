package com.PPII.database.tables;

import com.PPII.GUI.DatabaseGui;
import com.PPII.parsers.FlightNumberParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateFlightNumber extends FlightNumberParser {
    private SqlRequestFormatter formatter;

    public PopulateFlightNumber(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 4, "FlightNumber");
    }

    public void runFlightNumber() {
        Parser parser = parse();
        try {
            //parser gives back (airline IATA OR ICAO, flight number, airports list)
            sendFlightNumber(parser.executeLexers(true, ","));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFlightNumber(Queue<String> data) throws IncorrectDataFormatException, ParsingException, MissingDataException {
        //construct alpha3 hashmap
        HashMap<String, Integer> airlineIATA = (new AirlineIdHelper()).airlineIATAHashmap();
        HashMap<String, Integer> airlineICAO = (new AirlineIdHelper()).airlineICAOHashmap();
        int routeId = 0;
        long id = 0;
        for (String element : data) {
            String[] parts = element.split(";;");
            try {
                Integer airlineId = parts[0].equals("NULL") ? null : airlineIATA.get(parts[0]);
                if (airlineId == null) {
                    airlineId = airlineICAO.get(parts[0]);
                    if (airlineId == null) {
                        throw new MissingDataException("This id is missing : "+parts[0]+" at line "+id+" in flightnumbers.csv");
                    }
                }
                this.formatter.insertBatch(id, parts[1], airlineId, routeId);
                String[] routeList = parts[2].split("-");
                for (String el : routeList) {
                    if (el.equals(" ")) {
                        throw new ParsingException("Error routelist " + id + " " + parts[2]);
                    }
                }
                routeId += routeList.length;
                id++;

            } catch (NullPointerException e) {
                DatabaseGui.println(parts[0] + " id failed");
                e.printStackTrace();
            }
        }
        this.formatter.finishBatch();
    }
}
