package com.PPII.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class RouteListe {
	
	//Attributs
	private Integer routeId;
	private ArrayList<Integer> airportIds;
	private Integer range;
	
	//Constructeurs
	public RouteListe() {
	    this.airportIds=new ArrayList<>();
    };
	public RouteListe(Integer routeId, ArrayList<Integer> airportIds, Integer range) {
		super();
		this.routeId = routeId;
		this.airportIds = airportIds;
		this.range = range;
	}
	//Getters & Setters
	
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public ArrayList<Integer> getAirportId() {
		return airportIds;
	}
	
	public void setAirportId(ArrayList<Integer> airportIds) {
		this.airportIds.addAll(airportIds);
	}
    public void addAirportId(Integer airportId) {
        this.airportIds.add(airportId);
    }
	public Integer range() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}

    @Override
    public String toString() {
        return this.routeId+"; "+ Arrays.toString(this.airportIds.toArray())+"; "+this.range;
    }
}
