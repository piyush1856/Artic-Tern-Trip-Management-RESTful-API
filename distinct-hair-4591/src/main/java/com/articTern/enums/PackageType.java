package com.articTern.enums;

public enum PackageType {
	
	SOLO("Solo"),
	FAMILY("Family"),
	FRIENDS("Friends"),
	COUPLE("Couple");
	
	private String type;
	 
    private PackageType(String type) {
		this.type = type;
	}
 
    public String getPackageType() {
        return type;
    }

}
