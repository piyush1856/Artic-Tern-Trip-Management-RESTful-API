package com.articTern.enums;

public enum PaymentMode {
	
	
	CREDIT_CARD("Credit_Card"),
	DEBIT_CARD("Debit_Card"),
	BANK_TRANSFER("Bank_Transfer"),	
	UPI_PAYMENT("UPI_Payment");
	
	private String type;
	 
    private PaymentMode(String type) {
		this.type = type;
	}
 
    public String getPaymentModeType() {
        return type;
    }
	

}
