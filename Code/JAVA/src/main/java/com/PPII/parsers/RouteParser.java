package com.PPII.parsers;

public abstract class RouteParser {
    public RouteParser(){

    }
    public Parser parse(){
        Parser parser = new Parser("routes.dat"); //on load le fichier routes.dat
        parser.addLexer("^\\w{1,3}$"); //regle pour la colonne 0: airlien IATA OR ICAO
        parser.addLexer("^\\d{1,5}$");//regle pour la colonne 1: airline ID
        parser.addLexer("^\\w{3}$");//regle pour la colonne 2: source Airport
        parser.addLexer("^\\d{1,5}$");//regle pour la colonne 3: source Airport ID
        parser.addLexer("^\\w{3}$");//regle pour la colonne 4: destination Airport
        parser.addLexer("^\\d{1,5}$");//regle pour la colonne 5: destination Airport ID
        parser.addLexer("^\\w{0,1}$");//regle pour la colonne 6: codeshare
        parser.addLexer("^\\d{1}$");//regle pour la colonne 7: stops
        parser.addLexer("^.*$");//regle pour la colonne 8: Equipment
        return parser;
    }
}
