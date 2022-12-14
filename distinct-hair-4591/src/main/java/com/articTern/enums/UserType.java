package com.articTern.enums;

public enum UserType {
	
	Admin("Admin"),
	Customer("Customer");
	
	private String type;
	 
    private UserType(String type) {
		this.type = type;
	}
 
    public String getUserType() {
        return type;
    }

}
