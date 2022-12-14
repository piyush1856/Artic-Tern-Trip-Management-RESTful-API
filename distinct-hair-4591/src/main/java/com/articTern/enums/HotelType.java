package com.articTern.enums;

public enum HotelType {
	
	DOWNTOWN("Downtown"),
	SUB_URBAN("Sub-Urban"),
	MOTELS("Motels"),
	AIRPORTS_HOTELS("Airports Hotels"),
	RESORT("Resort"),
	FOREST_HOTEL("Forest Hotels");
	
	private String type;
	 
    private HotelType(String type) {
		this.type = type;
	}
 
    public String getHotelType() {
        return type;
    }
	
	
}
