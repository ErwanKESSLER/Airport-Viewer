package com.PPII.util;

import com.PPII.parsers.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Iso3166Helper {

    public Iso3166Helper() {
        //return String[]{"Country","Alpha-2 code","Alpha-3 code","Numeric code","Latitude (average)","Longitude (average)"}
    }

    public HashMap<String, String[]> alpha3() throws IncorrectDataFormatException, MissingDataException {
        try {
            HashMap<String, String[]> alpha2toInfos = new HashMap<>();
            BufferedReader alpha3 = (new Parser()).openRessource("countriesCode.csv");
            Scanner sc2 = new Scanner(alpha3);
            sc2.nextLine(); //removing header
            while (sc2.hasNextLine()) {
                //split with correct regex and remove the "
                String[] parts = Arrays.stream(sc2.nextLine().split(", (?=\")")).map(field -> field.replaceAll("\"", "")).toArray(String[]::new);
                if (parts.length != 6) {
                    throw new IncorrectDataFormatException("countriesCode.csv wrong format at: " + parts[0]);
                }
                //interesting parts are 0 for name, 2 for alpha3, 4 for lattitude, 5 for longitude
                alpha2toInfos.put(parts[1], parts);
            }
            HashMap<String, String[]> alpha2s = new HashMap<>();
            BufferedReader alpha2 = (new Parser()).openRessource("countries.dat");
            Scanner sc = new Scanner(alpha2);
            while (sc.hasNextLine()) {
                //split with correct regex and remove the "
                String[] parts = Arrays.stream(sc.nextLine().split(",")).map(field -> field.replaceAll("\"", "")).toArray(String[]::new);
                if (parts.length != 4) {
                    throw new IncorrectDataFormatException("Countries.dat wrong format at: " + parts[0]);
                }
                //if we have a ISO 3166-2 then use it to identify the country
                String[] infos = alpha2toInfos.get(parts[2].equals("\\N") ? parts[1] : parts[2]);
                if (infos == null) {
                    throw new MissingDataException("Missing a field for : " + parts[0]);
                }
                alpha2s.put(parts[0], infos);
            }
            return alpha2s;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
