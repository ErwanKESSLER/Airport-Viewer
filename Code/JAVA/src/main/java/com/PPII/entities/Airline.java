package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Airline {

	//attributs
	private Integer id;
	private String name;
	private String alias;
	private String iata;
	private String icao;
	private String callsign;
	private String countryId;
	private String active;
	
	//constructeurs
	public Airline() {};
	public Airline(Integer id, String name, String alias, String iata, String icao, String callsing, String countryId,String active) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.iata = iata;
		this.icao = icao;
		this.callsign = callsing;
		this.countryId = countryId;
		this.active = active;
	}
	
	//getters & setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if(id>99999) {
			DatabaseGui.println("taille de id trop longue");
		}else this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length()>200) {
			DatabaseGui.println("taille de name trop longue");
		}else this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		if(alias.length()>100) {
			DatabaseGui.println("taille de alias trop longue");
		}else this.alias = alias;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		if(iata.length()>2) {
			DatabaseGui.println("taille de iata trop longue");
		}else this.iata = iata;
	}
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		if(icao.length()>3) {
			DatabaseGui.println("taille de icao trop longue");
		}else this.icao = icao;
	}
	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		if(callsign.length()>200) {
			DatabaseGui.println("taille de callsign trop longue");
		}else this.callsign = callsign;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		if(countryId.length()>3) {
			DatabaseGui.println("taille de countryId trop longue");
		}else this.countryId = countryId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		if(active.length()>1) {
			DatabaseGui.println("taille de active trop longue");
		}else this.active = active;
	}

    @Override
    public String toString() {
        return this.id+"; "+this.name+"; "+this.alias+"; "+this.iata+"; "+this.icao+"; "+this.callsign +"; "+this.countryId+"; "+this.active;
    }
}
