package com.articTern.enums;

public enum HotelStatus {
	
	OPEN("Open"),
	BOOKED("Booked"),
	CANCEL("Cancelled");
	
	private String type;
	 
    private HotelStatus(String type) {
		this.type = type;
	}
 
    public String getHotelStatusType() {
        return type;
    }
	
}
