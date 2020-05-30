package com.PPII.entities;

import com.PPII.GUI.DatabaseGui;

public class Countries {
	
	//attributs
	private String countryId;
	private String countryName;
	private String countryRealName;
	private Double latitude;
    private Double longitude;

    public String getCountryRealName() {
        return countryRealName;
    }

    public void setCountryRealName(String countryRealName) {
        this.countryRealName = countryRealName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


	//constructeurs
	public Countries() {};
	public Countries(String countryId, String countryName,String countryRealName,Double latitude, Double longitude) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryName=countryRealName;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	
	//getters & setters
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		if(countryId.length()>200) {
			DatabaseGui.println("countryId trop long");
		}else this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		if(countryName.length()>200) {
			DatabaseGui.println("countryName trop long");
		}else this.countryName = countryName;
	}

    @Override
    public String toString() {
        return this.countryId+"; "+this.countryName+"; "+this.countryRealName+"; "+this.latitude+"; "+this.longitude;
    }
}
