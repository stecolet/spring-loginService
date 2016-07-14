package com.faac.colettaAssessment.dao;

import java.util.List;

import com.faac.colettaAssessment.model.LoggedUser;

public interface LoggedUserDAO {


	public LoggedUser save(LoggedUser u);
	
	public List<LoggedUser> listAll();
	
	public LoggedUser findById(Long id);
	
	public LoggedUser findByUsername(String username);
		
	public void delete(LoggedUser loggedUser);
}
