package com.PPII.parsers;

public class FlightNumberParser {
    public FlightNumberParser(){

    }
    public Parser parse(){
        Parser parser = new Parser("flightnumbers.csv"); //on load le fichier flightnumber.csv
        parser.addLexer("^.*$"); //regle pour la colonne 0: Airline IATA or ICAO
        parser.addLexer("^.*$");//regle pour la colonne 1: Flight number
        parser.addLexer("^.*$");//regle pour la colonne 2: Aiports Lists
        return parser;
    }
}
