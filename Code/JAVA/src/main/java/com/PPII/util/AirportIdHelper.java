package com.PPII.util;

import com.PPII.parsers.AirlineParser;
import com.PPII.parsers.AirportParser;

import java.util.HashMap;
import java.util.Queue;

public class AirportIdHelper extends AirportParser {


    public HashMap<Integer, Integer> aiportIdHashmap() {
        HashMap<Integer, Integer> airportId = new HashMap<>();
        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
            String[] parts = element.split(";;");
            airportId.put(Integer.parseInt(parts[0]), id);
            id++;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return airportId;
    }

    public HashMap<String, Integer> airportIATAHashmap() {
        HashMap<String, Integer> airlineId = new HashMap<>();
        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                airlineId.put(parts[4], id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airlineId;
    }

    public HashMap<String, Integer> airportICAOHashmap() {
        HashMap<String, Integer> airlineId = new HashMap<>();
        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                airlineId.put(parts[5], id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airlineId;
    }

}
