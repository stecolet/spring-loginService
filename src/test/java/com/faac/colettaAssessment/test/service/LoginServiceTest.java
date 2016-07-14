package com.faac.colettaAssessment.test.service;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.faac.colettaAssessment.dao.UserDAO;
import com.faac.colettaAssessment.model.LoggedUser;
import com.faac.colettaAssessment.model.User;
import com.faac.colettaAssessment.service.LoginService;

public class LoginServiceTest {

	@Test
	public void testSuccesfullyLoginUser() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		Assert.assertNotNull(userDAO.save(user).getId());
		
		Assert.assertTrue(loginService.login("scoletta","123Abc")>0);
		
		context.close();	
		
	}
	
	@Test
	public void testFailedLoginUser_WhenWrongCredential() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		Assert.assertNotNull(userDAO.save(user).getId());
		
		Assert.assertEquals(loginService.login("scoletta","Ab.."),LoginService.WRONG_CREDENTIAL);
		
		context.close();	
		
	}
	
	@Test
	public void testFailedLoginUser_WhenAlreadyLogged() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		Assert.assertNotNull(userDAO.save(user).getId());
		
		Assert.assertTrue(loginService.login("scoletta","123Abc")>0);
		
		Assert.assertEquals(loginService.login("scoletta","123Abc"),LoginService.ALREADY_LOGGED_USER_ERRORCODE);
		
		context.close();	
		
	}
	
	@Test
	public void testFailedLoginUser_WhenNotRegistered() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
				
		Assert.assertEquals(loginService.login("scoletta","123Abc"),LoginService.NOT_EXISTING_USER_ERRORCODE);
		
		context.close();	
		
	}
	
	@Test
	public void testSuccesfullyRegisterUser()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
				
		Assert.assertTrue(loginService.registerUser("scoletta","123Abc")>0);
		
		context.close();	
	}
	
	@Test
	public void testFailedRegisterUser_WhenAlradyRegistered()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
				
		Assert.assertTrue(loginService.registerUser("scoletta","123Abc")>0);
		
		Assert.assertEquals(loginService.registerUser("scoletta","123Abc"),LoginService.ALREADY_REGISTERED_USER_ERRORCODE);
		
		
		context.close();	
	}
	
	@Test
	public void testGetLoggedUsers()
	{

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		LoginService loginService=context.getBean(LoginService.class);
		
		Assert.assertNotNull(loginService);
		
		loginService.registerUser("scoletta", "Abc123");
		loginService.registerUser("mrossi", "Xyz456");
		
		loginService.login("scoletta", "Abc123");
		loginService.login("mrossi", "Xyz456");
		
		List<User> loggedUsers=loginService.getLoggedUser();
		Assert.assertEquals(loggedUsers.size(),2);
		
		//NB: A very usefull way to test a collection.. thanks hamcrest
	
	    assertThat( loggedUsers, hasItem(
    		    Matchers.<User>hasProperty("username", equalTo(("scoletta"))
    		)));
	    
	    assertThat( loggedUsers, hasItem(
    		    Matchers.<User>hasProperty("username", equalTo(("scoletta"))
    		)));
	}


}
