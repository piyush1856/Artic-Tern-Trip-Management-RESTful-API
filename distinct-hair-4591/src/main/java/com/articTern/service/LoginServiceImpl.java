package com.articTern.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.dtoes.LoginDTO;
import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.CustomerException;
import com.articTern.model.User;
import com.articTern.model.UserSession;
import com.articTern.repository.AdminRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String logIntoAccount(LoginDTO dto) throws CredentialException {
		
		User existingUser = null;
		
		if(dto.getUserType().equals(UserType.Admin)) {		
		      existingUser = aRepo.findByAdminEmail(dto.getEmailId());		 
		      
		} else if(dto.getUserType().equals(UserType.Customer)) {
			 existingUser = cRepo.findByCustomerEmail(dto.getEmailId());			
		}
		
		
		if(existingUser == null) {
			 throw new CustomerException("Invalid Email Id");
		 }	
		 

		
		 Optional<UserSession> userSession = sRepo.findById(existingUser.getUserId());
		 
		 
		 if(userSession.isPresent()) {
			 throw new CredentialException("Customer is already logged in");
		 }
		 
		 
		 if(! existingUser.getPassword().equals(dto.getPassword())) {
			 throw new CredentialException("Invalid password");			 
		 }
		 
		 String key = RandomString.make(6);		 
		 UserSession session = new UserSession(existingUser.getUserId(), key, LocalDateTime.now(), dto.getUserType());
	
		 sRepo.save(session);

		 return "Logged In Successful.. " + session.toString();
	}

	
	
	@Override
	public String logOutFromAccount(String key) throws CredentialException {
		
		UserSession session = sRepo.findByUuid(key);
		
		if(session == null) {
			throw new CredentialException("User is not logged in with this account");
		}
		
		sRepo.delete(session);
		
		return "Logged Out Successfully... " + session.getLocalDateTime();
	}

}
