package com.faac.colettaAssessment.ws;


import java.util.ArrayList;
import java.util.List;

import org.example.loginwebservice.GetLoggedUsersRequest;
import org.example.loginwebservice.GetLoggedUsersResponse;
import org.example.loginwebservice.User;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.faac.colettaAssessment.service.LoginService;

 
@Endpoint
public class LoginWsEndpoint {
	private static final String NAMESPACE_URI = "http://www.example.org/loginWebService";


	LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLoggedUsersRequest")
	@ResponsePayload
	  public GetLoggedUsersResponse getLoggedUsers(GetLoggedUsersRequest req) {//Welcome page, non-rest

		List<com.faac.colettaAssessment.model.User> usersFromService=loginService.getLoggedUser();
		
		User uW=new User();
		GetLoggedUsersResponse response = new GetLoggedUsersResponse();
		for(com.faac.colettaAssessment.model.User u : usersFromService)
		{
			uW.setUsername(u.getUsername());
			uW.setPassword(u.getPassword());
			response.getUsers().add(uW);
		}
		
		return response;
    }
  	 
}