package com.PPII.database.tables;

import com.PPII.parsers.Parser;
import com.PPII.parsers.PlaneParser;
import com.PPII.util.SqlConnect;
import com.PPII.util.SqlRequestFormatter;

import java.util.Queue;

public class PopulatePlane extends PlaneParser {
    private SqlRequestFormatter formatter;

    public PopulatePlane(SqlConnect db) {
        this.formatter = new SqlRequestFormatter(db.getConnection(), 4, "plane");
    }

    public void runPlane() {
        Parser parser = parse();
        try {
            sendPlane(parser.executeLexers(false, ",(?=[0-9\"-\\\\])"));
            //on parse avec les regles le fichier et on specifie le delimiter (ici une virgule avec a la suite des caracteres correct (ne pas changer))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPlane(Queue<String> data) {
        int index = 0;
        String[] part = data.element().split(";;");
        for (String element : data) {
            String[] parts = element.split(";;");
            if (formatter.insertValues(index, parts[1], parts[2], parts[0]) != 1) {
                throw new Error(parts[1]);
            }
            index++;
        }

    }
}
