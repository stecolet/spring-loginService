package com.faac.colettaAssessment.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.faac.colettaAssessment.dao.UserDAO;
import com.faac.colettaAssessment.model.User;

public class UserDAOTest {

	@Test
	public void testSaveUser() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		Assert.assertNotNull(userDAO.save(user).getId());
		
		User loadedUser=userDAO.findById(user.getId());
		Assert.assertEquals(loadedUser.getId(),user.getId());
		
		context.close();	
		
	}
	

	@Test
	public void testDeleteUser() {
		

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		user=userDAO.save(user);
		userDAO.delete(user);
		
		User loadedUser=userDAO.findById(user.getId());
		Assert.assertNull(loadedUser);
		
		context.close();	
		
	}
	
	@Test
	public void testUserByUsername(){

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean(UserDAO.class);
		
		Assert.assertNotNull(userDAO);
		
		User user = new User();
		user.setUsername("scoletta"); user.setPassword("123Abc");
		
		user=userDAO.save(user);
		
		User loadedUser=userDAO.findByUsername("scoletta");
		Assert.assertNotNull(loadedUser);
		
		Assert.assertEquals(loadedUser.getUsername(), "scoletta");
		Assert.assertEquals(loadedUser.getId(), user.getId());
		
		context.close();	
	}
}
