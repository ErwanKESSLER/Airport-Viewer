package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Localization {
	
	//attributs
	private Integer cityId;
	private String cityName;
	private String countryId;
	private double timezone;
	
	//Constructeurs
	public Localization() {};
	public Localization(Integer cityId, String cityName, String countryId, double timezone) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.countryId = countryId;
		this.timezone = timezone;
	}
	
	//getters & setters
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		if(cityName.length()>200) {
			DatabaseGui.println("cityName trop long");
		}else this.cityName = cityName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		if(countryId.length()>3) {
			DatabaseGui.println("countryId trop long");
		}else this.countryId = countryId;
	}
	public double getTimezone() {
		return timezone;
	}
	public void setTimezone(double timezone) {
		this.timezone = timezone;
	}

    @Override
    public String toString() {
        return this.cityId+"; "+this.cityName+"; "+this.countryId+"; "+this.timezone;
    }
}
