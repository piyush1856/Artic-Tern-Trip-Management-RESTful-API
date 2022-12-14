package com.articTern.enums;

public enum TicketStatus {
	
	WAITING("Waiting"),
	CONFIRMED("Confirmed"),
	CANCEL("Cancelled");
	
	private String type;
	 
    private TicketStatus(String type) {
		this.type = type;
	}
 
    public String getTicketStatusType() {
        return type;
    }

}
