package com.PPII.parsers;

public abstract class AirportParser {
    public AirportParser(){

    }
    public Parser parse(){
        Parser parser = new Parser("airports.dat"); //on load le fichier airports.dat
        parser.addLexer("^\\d{1,5}$"); //regle pour la colonne 0: id
        parser.addLexer("^\".*\"$");//regle pour la colonne 1: nomAirport
        parser.addLexer("^\".*\"$");//regle pour la colonne 2: nomcity
        parser.addLexer("^\".*\"$");//regle pour la colonne 3: nomCountry
        parser.addLexer("^\"\\w{1,3}\"$");//regle pour la colonne 4: IATA
        parser.addLexer("^\"\\w{1,4}\"$");//regle pour la colonne 5: ICAO
        parser.addLexer("^-{0,1}\\d*.\\d*$");//regle pour la colonne 6: latitude
        parser.addLexer("^-{0,1}\\d*.\\d*$");//regle pour la colonne 7: longitude
        parser.addLexer("^\\d*$");//regle pour la colonne 8: altitude
        parser.addLexer("^[0-9\\.-]*$");//regle pour la colonne 9: timzeone
        parser.addLexer("^\"[EASOZNU]\"$");//regle pour la colonne 10: dst
        parser.addLexer("^\".*\"$");//regle pour la colonne 11: tz
        parser.addLexer("^\"\\w*\"$");//regle pour la colonne 12: type
        parser.addLexer("^\"\\w*\"$");//regle pour la colonne 13: source
        return parser;
    }
}
