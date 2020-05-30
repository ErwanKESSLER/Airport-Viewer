package com.PPII.database.tables;

import com.PPII.GUI.DatabaseGui;
import com.PPII.parsers.AirlineParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateAirline extends AirlineParser {
    private SqlRequestFormatter formatter;

    public PopulateAirline(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 8, "Airline");
    }

    public void runAirline() {
        Parser parser = parse();
        try {

            //parser gives back (id,AirlineName,Alias,IATA,ICAO,Callsign,Country,Active)
            sendAirline(parser.executeLexers(false, ",(?=[0-9\"-\\\\])"));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendAirline(Queue<String> data) throws IncorrectDataFormatException, ParsingException, MissingDataException {
        //construct alpha3 hashmap
        HashMap<String, String[]> alpha3 = (new Iso3166Helper()).alpha3();
        if (alpha3 == null) {
            throw new ParsingException("Names to alpha3 conversion failed");
        }
        String[] infos;
        int id=0;
        for (String element : data) {
            String[] parts = element.split(";;");
            try {
                infos = alpha3.get(parts[6]);
                this.formatter.insertBatch(id,parts[1],parts[2],parts[3],parts[4],parts[5],infos[2],parts[7]);
                id++;
            } catch (NullPointerException e) {
                DatabaseGui.println(parts[0]+" id failed");
                e.printStackTrace();
            }
        }
        this.formatter.finishBatch();
    }
}
