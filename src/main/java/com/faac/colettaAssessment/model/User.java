package com.faac.colettaAssessment.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User extends AbstractEntity {


	private String username;
	
	private String password;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "id="+super.getId()+", username="+username+", password="+password;
	}
	
}
