package com.PPII.util;

import com.PPII.parsers.AirlineParser;

import java.util.HashMap;
import java.util.Queue;

public class AirlineIdHelper extends AirlineParser {


    public HashMap<Integer, Integer> airlineIdHashmap() {
        HashMap<Integer, Integer> airlineId = new HashMap<>();
        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                airlineId.put(Integer.parseInt(parts[0]), id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airlineId;
    }

    public HashMap<String, Integer> airlineIATAHashmap() {
        HashMap<String, Integer> airlineId = new HashMap<>();
        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                airlineId.put(parts[3], id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airlineId;
    }

    public HashMap<String, Integer> airlineICAOHashmap() {
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

}
