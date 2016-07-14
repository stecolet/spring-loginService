package com.faac.colettaAssessment.test.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.faac.colettaAssessment.dao.LoggedUserDAO;
import com.faac.colettaAssessment.dao.UserDAO;
import com.faac.colettaAssessment.model.LoggedUser;
import com.faac.colettaAssessment.model.User;

public class LoggedUserDAOTest {

	@Test
	public void testSaveLoggedUser() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		user=userDAO.save(user);
		
	
		LoggedUserDAO loggedUserDAO= context.getBean(LoggedUserDAO.class);
		LoggedUser lu=new LoggedUser();
		lu.setLoggedUser(user);
		lu.setLoginDate(Calendar.getInstance());
		
		Assert.assertNotNull(loggedUserDAO.save(lu).getId());
		
		context.close();	
		
	}
	

	@Test
	public void testFindByUsername() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		user=userDAO.save(user);
		
	
		LoggedUserDAO loggedUserDAO= context.getBean(LoggedUserDAO.class);
		LoggedUser lu=new LoggedUser();
		lu.setLoggedUser(user);
		lu.setLoginDate(Calendar.getInstance());
		
		Assert.assertNotNull(loggedUserDAO.save(lu).getId());
		
		Assert.assertEquals(loggedUserDAO.findByUsername("scoletta").getLoggedUser().getUsername(),"scoletta");
		
		context.close();	
		
	}

}
