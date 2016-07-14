package com.faac.colettaAssessment.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.faac.colettaAssessment.dao.LoggedUserDAO;
import com.faac.colettaAssessment.dao.UserDAO;
import com.faac.colettaAssessment.model.LoggedUser;
import com.faac.colettaAssessment.model.User;
import com.faac.colettaAssessment.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoggedUserDAO loggedUserDAO;
	
	private UserDAO userDAO;
	
	
	
	public void setLoggedUserDAO(LoggedUserDAO loggedUserDAO) {
		this.loggedUserDAO = loggedUserDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	private boolean isUserRegistered(String username)
	{
		User registeredUser=userDAO.findByUsername(username);
		
		return registeredUser!=null;
	}
	
	private boolean isUserLogged(String username)
	{
		LoggedUser loggedUser=loggedUserDAO.findByUsername(username);
		
		return loggedUser!=null;
	}

	public int login(String username, String password) {

				
		if(!isUserRegistered(username))
		{
			return LoginService.NOT_EXISTING_USER_ERRORCODE;
		}
		
		if(isUserLogged(username))
		{
			return LoginService.ALREADY_LOGGED_USER_ERRORCODE;
		}
		
		User registeredUser=userDAO.findByUsername(username);
		
		if(!registeredUser.getPassword().equals(password))
		{
			
			return LoginService.WRONG_CREDENTIAL;
		}
		
		LoggedUser lu=new LoggedUser();
		lu.setLoggedUser(registeredUser);
		lu.setLoginDate(Calendar.getInstance());
		
		loggedUserDAO.save(lu);
		
		return 1;
				
		
	}

	public int registerUser(String username, String password) {
		
		if(isUserRegistered(username))
		{
			return LoginService.ALREADY_REGISTERED_USER_ERRORCODE;
		}
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		userDAO.save(user);
		
		return 1;
	}

	public List<User> getLoggedUser() {

		List<LoggedUser> loggedUsers= loggedUserDAO.listAll();
		List<User> toBeReturned=new ArrayList<User>();
		
		User u=null;
		for (LoggedUser lu : loggedUsers)
		{
			u=new User();
			u.setUsername(lu.getLoggedUser().getUsername());
			u.setPassword(lu.getLoggedUser().getPassword());
			u.setId(lu.getLoggedUser().getId());
			toBeReturned.add(u);
		}
		
		return toBeReturned;
	}

}
