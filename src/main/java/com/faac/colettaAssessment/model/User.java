package com.faac.colettaAssessment.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "username",
    "id",
    "password"
})
@Entity
@Table(name="User")
public class User extends AbstractEntity {


	@XmlElement(required = true)
	private String username;
	
	@XmlElement(required = true)
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
