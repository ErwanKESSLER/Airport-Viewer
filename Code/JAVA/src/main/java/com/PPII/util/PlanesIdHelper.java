package com.PPII.util;

import com.PPII.parsers.PlaneParser;

import java.util.HashMap;
import java.util.Queue;

public class PlanesIdHelper extends PlaneParser {
    public HashMap<String, Integer> planesIATAHashmap() {
        HashMap<String, Integer> planesIATA = new HashMap<>();

        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                planesIATA.put(parts[1], id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planesIATA;
    }

    public HashMap<String, Integer> planesICAOHashmap() {
        HashMap<String, Integer> planesICAO = new HashMap<>();

        try {
            Queue<String> data = parse().executeLexers(false, ",(?=[0-9\"-\\\\])");
            int id = 0;
            for (String element : data) {
                String[] parts = element.split(";;");
                planesICAO.put(parts[2], id);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planesICAO;
    }

}
