package com.PPII.database.tables;

import com.PPII.parsers.Parser;
import com.PPII.parsers.RouteParser;
import com.PPII.util.*;

import java.util.HashMap;
import java.util.Queue;

public class PopulateEquipmentList extends RouteParser {
    private SqlRequestFormatter formatter;

    public PopulateEquipmentList(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 3, "EquipmentList");
    }

    public void runEquipmentList() {
        Parser parser = parse();
        try {
            sendEquipmentList(parser.executeLexers(false, ","));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEquipmentList(Queue<String> data) throws MissingDataException, ParsingException, IncorrectDataFormatException {
        int equipmentId = 0;
        HashMap<String, Integer> planesIATA = (new PlanesIdHelper()).planesIATAHashmap();
        HashMap<String, Integer> planesICAO = (new PlanesIdHelper()).planesICAOHashmap();
        for (String element : data) {
            String[] parts = element.split(";;");
            String[] equipmentList = parts[8].split(" ");
            for (int i = 0; i < equipmentList.length; i++) {
                if (equipmentList[i].equals(" ") || (i < (equipmentList.length - 1) && equipmentList[i + 1].equals(" "))) {
                    throw new ParsingException("Error equipments " + parts[8] + " " + parts[0]);
                }
                Integer planeId = planesIATA.get(equipmentList[i]);
                if (planeId == null) {
                    planeId = planesICAO.get(equipmentList[i]);
                    if (planeId == null) {
                        //we should catch the error but we will ignore it
                    }
                }
                if (equipmentList.length - 1 == i) {
                    this.formatter.insertBatch(equipmentId, planeId, null);
                } else {
                    this.formatter.insertBatch(equipmentId, planeId, equipmentId + 1);
                }
                equipmentId++;
            }
        }
        this.formatter.finishBatch();

    }

}
