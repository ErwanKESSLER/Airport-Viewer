package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Route {
	
	//Attributs
	private Integer routeId;
	private Integer airlineId;
	private Integer sourceAirportId;
	private Integer destinationAirportId;
	private Integer escales;
	private String codeshare;
	private Integer equipementId;
	
	//Constructeurs
	public Route() {};
	public Route(Integer routeId, Integer airlineId, Integer sourceAirportId, Integer destinationAirportId, Integer escales,String codeshare, Integer equipementId) {
		super();
		this.routeId = routeId;
		this.airlineId = airlineId;
		this.sourceAirportId = sourceAirportId;
		this.destinationAirportId = destinationAirportId;
		this.escales = escales;
		this.codeshare = codeshare;
		this.equipementId = equipementId;
	}
	

	
	//Getters & Setters
	
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public Integer getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Integer airlineId) {
		if(airlineId>99999) {
			DatabaseGui.println("airlineId est trop grand");
		}else this.airlineId = airlineId;
	}
	public Integer getSourceAirportId() {
		return sourceAirportId;
	}
	public void setSourceAirportId(Integer sourceAirportId) {
		if(sourceAirportId>99999) {
			DatabaseGui.println("sourceAirportId est trop grand");
		}else this.sourceAirportId = sourceAirportId;
	}
	public Integer getDestinationAirportId() {
		return destinationAirportId;
	}
	public void setDestinationAirportId(Integer destinationAirportId) {
		if(destinationAirportId>99999) {
			DatabaseGui.println("destinationAirportId est trop grand");
		}else this.destinationAirportId = destinationAirportId;
	}
	public Integer getEscales() {
		return escales;
	}
	public void setEscales(Integer escales) {
		this.escales = escales;
	}
	public String getCodeshare() {
		return codeshare;
	}
	public void setCodeshare(String codeshare) {
		if(codeshare.length()>21) {
			DatabaseGui.println("codeshare est trop grand");
		}else this.codeshare = codeshare;
	}
	public Integer getEquipementId() {
		return equipementId;
	}
	public void setEquipementId(Integer equipementId) {
		this.equipementId = equipementId;
	}

    @Override
    public String toString() {
        return this.routeId+"; "+this.airlineId+"; "+this.sourceAirportId+"; "+this.destinationAirportId+"; "+this.escales+"; "+this.codeshare+"; "+this.equipementId;
    }
}
