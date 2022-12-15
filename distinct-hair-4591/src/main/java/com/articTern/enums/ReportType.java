package com.articTern.enums;

public enum ReportType {
	
	TRAVELS("Travels"),
	BUS("Bus"),
	ROUTE("Route"),
	CUSTOMER("Customer"),
	TICKET_DETAIL("Ticket Details"),
	PACKAGE("Package"),
	HOTEL("Hotel"),
	BOOKING("Booking"),
	FEEDBACK("Feedback");
	
	private String type;
	 
    private ReportType(String type) {
		this.type = type;
	}
 
    public String getReportType() {
        return type;
    }

}
