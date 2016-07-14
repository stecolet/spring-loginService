package com.faac.colettaAssessment.service;

import java.util.List;

import com.faac.colettaAssessment.model.LoggedUser;
import com.faac.colettaAssessment.model.User;

public interface LoginService {

	/**
	 * This method login the user checking on the registered user. THe method fails when the user is already logged or it doesn't exist.
	 * 
	 * @param username
	 * @param password
	 * @return an Integer > 0 when login succesfully, -1 when the user us already logged, -2 when the user isn't registered in the system
	 */
	public int login(String username,String password);
	
	/**
	 * This method register a user with the specified credential. 
	 * @param username
	 * @param password
	 * @returnAn Integer > 0 when the operation succesfully. -3 when the user is already registered.
	 */
	public int registerUser(String username,String password);
	
	/**
	 * this method return the list of the Logged users in the system.
	 * @return
	 */
	public List<User> getLoggedUser();
	
	public static final int ALREADY_LOGGED_USER_ERRORCODE=-1;
	public static final int ALREADY_REGISTERED_USER_ERRORCODE=-3;
	public static final int NOT_EXISTING_USER_ERRORCODE=-2;
	public static final int WRONG_CREDENTIAL=0;
	
	
}
