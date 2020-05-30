package com.PPII.database.tables;

import com.PPII.parsers.AirportParser;
import com.PPII.parsers.Parser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateCountries extends AirportParser {
    private SqlRequestFormatter formatter;

    public PopulateCountries(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(),5,"Country");
    }

    public void runCountries() {
        Parser parser=parse();
        try {
            sendCountries(parser.executeLexers(false, ",(?=[0-9\"-\\\\])"));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCountries(Queue<String> data) throws IncorrectDataFormatException, ParsingException, MissingDataException {
        //construct alpha3 hashmap
        HashMap<String, String[]> alpha3 = (new Iso3166Helper()).alpha3();
        if (alpha3==null){
            throw new ParsingException("Names to alpha3 conversion failed");
        }
        HashMap<String, Boolean> done = new HashMap<>();
        String[] infos;
        for (String element : data) {
            String[] parts = element.split(";;");
            if (done.get(parts[3]) == null) {
                try {
                    infos = alpha3.get(parts[3]);
                    //inserting alpha3, name, realname, lattitude, longitude
                    this.formatter.insertBatch(infos[2],parts[3],infos[0],Double.parseDouble(infos[4]),Double.parseDouble(infos[5]));

                    done.put(parts[3], true);
                } catch (NullPointerException e) {
                    throw new MissingDataException("Missing that field: "+parts[3]+" in the alpha3 conversion table");
                }
            }
        }
        this.formatter.finishBatch();
    }


}
