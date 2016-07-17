package com.faac.colettaAssessment.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faac.colettaAssessment.model.User;
import com.faac.colettaAssessment.service.LoginService;

@RestController
public class LoginRestController {
	
	LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	   @RequestMapping(value ="/register",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public Integer registerUser(@RequestBody User user) {//Welcome page, non-rest
		   
		    return new Integer(loginService.registerUser(user.getUsername(), user.getPassword()));
	    }
	   
	   @RequestMapping(value ="/login",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public Integer loginUser(@RequestBody User user) {//Welcome page, non-rest
		   
		    return new Integer(loginService.login(user.getUsername(), user.getPassword()));
	    }
	   
	   @RequestMapping(value ="/loggedUsers",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<User> getLoggedUsers() {//Welcome page, non-rest
		   
		    return loginService.getLoggedUser();
	    }
	  	 
}
