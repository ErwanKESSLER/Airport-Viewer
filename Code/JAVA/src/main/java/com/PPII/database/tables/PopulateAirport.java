package com.PPII.database.tables;

import com.PPII.parsers.AirportParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateAirport extends AirportParser {
    private SqlRequestFormatter formatter;

    public PopulateAirport(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 8, "Airport");
    }

    public void runAirport() {
        Parser parser = parse();
        try {
            sendAirport(parser.executeLexers(false, ",(?=[0-9\"-\\\\])"));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendAirport(Queue<String> data) throws IncorrectDataFormatException, ParsingException, MissingDataException {
        int currentId = 0;
        HashMap<String, Integer> cityEncountered = new HashMap<>();
        int globalCityId = 0;
        for (String element : data) {
            String[] parts = element.split(";;");
            try {
                if (cityEncountered.get(parts[2])==null){
                    cityEncountered.put(parts[2],globalCityId);
                    globalCityId++;
                }
                //inserting id,Name,IATA,ICAO,Lattitude,Longitude,Altitude,CityId
                this.formatter.insertBatch(currentId,parts[1],parts[4],parts[5],Double.parseDouble(parts[6]),Double.parseDouble(parts[7]),parts[8],cityEncountered.get(parts[2]));
                currentId++;

            } catch (NullPointerException e) {
                throw new MissingDataException("Aiport error at "+parts[1]);
            }

        }
        this.formatter.finishBatch();
    }
}
