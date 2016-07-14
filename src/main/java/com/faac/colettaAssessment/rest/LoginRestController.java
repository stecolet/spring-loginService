package com.faac.colettaAssessment.rest;

import java.util.List;

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
	
	   @RequestMapping(value ="/register",method = RequestMethod.POST)
	    public int registerUser(@RequestBody User user) {//Welcome page, non-rest
		   
		    return loginService.registerUser(user.getUsername(), user.getPassword());
	    }
	   
	   @RequestMapping(value ="/login",method = RequestMethod.POST)
	    public int loginUser(@RequestBody User user) {//Welcome page, non-rest
		   
		    return loginService.login(user.getUsername(), user.getPassword());
	    }
	   
	   @RequestMapping(value ="/loggedUsers",method = RequestMethod.GET)
	    public List<User> getLoggedUsers() {//Welcome page, non-rest
		   
		    return loginService.getLoggedUser();
	    }
	 
}
