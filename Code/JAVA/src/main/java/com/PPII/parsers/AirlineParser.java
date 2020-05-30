package com.PPII.parsers;

public abstract class AirlineParser {
    public AirlineParser(){

    }
    public Parser parse(){
        Parser parser = new Parser("airlines.dat"); //on load le fichier airlines.dat
        parser.addLexer("^\\d{1,5}$"); //regle pour la colonne 0: id
        parser.addLexer("^\".*\"$");//regle pour la colonne 1: nomAirline
        parser.addLexer("^\".*\"$");//regle pour la colonne 2: alias
        parser.addLexer("^\"\\w{1,3}\"$");//regle pour la colonne 3: IATA
        parser.addLexer("^\"\\w{1,4}\"$");//regle pour la colonne 4: ICAO
        parser.addLexer("^\".*\"$");//regle pour la colonne 5: callsign
        parser.addLexer("^\".*\"$");//regle pour la colonne 6: country
        parser.addLexer("^\".\"$");//regle pour la colonne 7: active
        return parser;
    }
}
