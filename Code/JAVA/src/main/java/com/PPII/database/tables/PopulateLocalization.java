package com.PPII.database.tables;

import com.PPII.parsers.AirportParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateLocalization extends AirportParser {
    private SqlRequestFormatter formatter;

    public PopulateLocalization(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(),4,"Localization");
    }

    public void runLocalization() {
        Parser parser = parse();
        try {
            sendLocalization(parser.executeLexers(false, ",(?=[0-9\"-\\\\])"));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendLocalization(Queue<String> data) throws MissingDataException, ParsingException, IncorrectDataFormatException {
        //construct alpha3 hashmap
        HashMap<String, String[]> alpha3 = (new Iso3166Helper()).alpha3();
        if (alpha3 == null) {
            throw new ParsingException("Names to alpha3 conversion failed");
        }
        HashMap<String, Boolean> cityEncountered = new HashMap<>();
        int globalCityId = 0;
        for (String element : data) {
            String[] parts = element.split(";;");
            if (cityEncountered.get(parts[2])==null){
                try {
                    String[] infos=alpha3.get(parts[3]);
                    //inserting cityId, cityName, Alpha3, timeZone
                    this.formatter.insertBatch(globalCityId,parts[2],infos[2], parts[9].equals("NULL")?parts[9]:Float.parseFloat(parts[9]));
                    cityEncountered.put(parts[2],true);
                    globalCityId++;
                } catch (NullPointerException e) {
                    throw new MissingDataException("Missing that field: " + parts[3] + " in the alpha3 conversion table");
                }
            }

        }
        this.formatter.finishBatch();
    }


}
