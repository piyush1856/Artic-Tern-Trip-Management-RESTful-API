package com.articTern.enums;

public enum BusType {
	
	
	
	AC_SLEEPER("AC-Sleeper"),
	AC_SEAT("AC-Seat"),
	NON_AC_SEAT("Non_AC-Seat"),
	NON_AC_SLEEPER("Non_AC-Sleeper");
	
	
	private String type;
	 
    private BusType(String type) {
		this.type = type;
	}
 
    public String getBusType() {
        return type;
    }
}
