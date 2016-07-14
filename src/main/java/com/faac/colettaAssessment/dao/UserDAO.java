package com.faac.colettaAssessment.dao;

import java.util.List;

import com.faac.colettaAssessment.model.User;

public interface UserDAO {

	public User save(User u);
	
	public List<User> listAll();
	
	public User findById(Long id);
	
	public User findByUsername(String username);
	
	public void delete(User user);
	
}
