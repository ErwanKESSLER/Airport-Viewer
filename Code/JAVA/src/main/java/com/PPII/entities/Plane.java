package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Plane {
	
	//Attributs
	
	private Integer planeId;
	private String name;
	private String iata;
	private String icao;
	
	//Constructeurs
	public Plane() {};
	public Plane(Integer planeId, String iata, String icao,String name) {
		this.planeId = planeId;
		this.name = name;
		this.iata = iata;
		this.icao = icao;	}
	
	//Getters & Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length()>200) {
			DatabaseGui.println("name trop grand");
		}else this.name = name;
	}
	
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		if(iata.length()>3) {
			DatabaseGui.println("iata trop grand");
		}else this.iata = iata;
	}
	
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		if(icao.length()>4) {
			DatabaseGui.println("icao trop grand");
		}else this.icao = icao;
	}
	public Integer getPlaneId() {
		return planeId;
	}
	public void setPlaneId(Integer planeId) {
		this.planeId = planeId;
	}

    @Override
    public String toString() {
        return this.planeId+"; "+this.iata+"; "+this.icao+"; "+this.name;
    }
}
