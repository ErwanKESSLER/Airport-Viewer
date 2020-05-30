package com.PPII.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class EquipmentList {
	
	//Attributs
	private Integer equipmentId;
	private ArrayList<Integer> planeIds;
	private Integer range;
	
	//Constructeurs 
	public EquipmentList() {
	    this.planeIds=new ArrayList<>();
    };
	public EquipmentList(Integer equipmentId,ArrayList<Integer> planeIds, Integer range) {
		super();
		this.equipmentId = equipmentId;
		this.planeIds = planeIds;
		this.range = range;
	}
	
	//Getters & Setters
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public ArrayList<Integer> getPlaneIds() {
		return planeIds;
	}
	public void setPlaneId(ArrayList<Integer> planeIds) {
		this.planeIds = planeIds;
	}
	public void addPlaneId(Integer planeId) {
		this.planeIds.add(planeId);
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}


    @Override
    public String toString() {
        return this.equipmentId+"; "+ Arrays.toString(planeIds.toArray())+"; "+this.range;
    }
}
