package com.articTern.enums;

public enum BookingType {
	
	MOUNTAIN("Mountain"),
	CITY("City"),
	DESERT("Desert"),
	BEACH("Beach"),
	CULTURAL("Cultural"),
	WILDLIFE("Wildlife");
	
	private String type;
	 
    private BookingType(String type) {
		this.type = type;
	}
 
    public String getBookingType() {
        return type;
    }
	

}
