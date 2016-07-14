package com.faac.colettaAssessment.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LoggedUser")
public class LoggedUser extends AbstractEntity {

	private User loggedUser;
	private Calendar loginDate;
	
    @ManyToOne
    @JoinColumn(name = "idLoggedUser" )
	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	public Calendar getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Calendar loginDate) {
		this.loginDate = loginDate;
	}
	
	
	@Override
	public String toString(){
		return "id="+super.getId()+", user:"+getLoggedUser().toString()+" at "+loginDate.toString();
	}
	
}
