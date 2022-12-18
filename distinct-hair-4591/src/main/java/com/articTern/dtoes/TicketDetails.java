package com.articTern.dtoes;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.articTern.enums.BookingType;
import com.articTern.enums.PackageType;
import com.articTern.enums.TicketStatus;
import com.articTern.model.PaymentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {
	
	private Integer ticketId;
	
	private String packageName;
	
	private PackageType pacakageType;
	
    @Enumerated(EnumType.STRING)
	private TicketStatus status;
    
    private BookingType bookingType;
    
    private Integer noOfPerson;
    
    private String customerName;
    
    private LocalDateTime bookingDateTime;
    
    private String customerEmail;
    
    private String customerMobile;
    
    private PaymentDetails payment;
	
	
}
