package com.PPII.parsers;

public abstract class PlaneParser {
    protected PlaneParser(){

    }
    protected Parser parse(){
        Parser parser = new Parser("planes.dat"); //on load le fichier planes.dat
        parser.addLexer("^\".*\"$"); //regle pour la colonne 0: x caracteres alphabetique ou numerique
        parser.addLexer("^\"\\w{1,3}\"$");//regle pour la colonne 1: IATA
        parser.addLexer("^\"\\w{1,4}\"$");//regle pour la colonne 2: ICAO
        return parser;
    }
}
