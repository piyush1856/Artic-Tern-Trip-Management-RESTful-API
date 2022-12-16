package com.articTern.service;

import com.articTern.dtoes.LoginDTO;
import com.articTern.exceptions.CredentialException;


public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto) throws CredentialException;
	
	public String logOutFromAccount(String key) throws CredentialException;

}
