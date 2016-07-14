package com.faac.colettaAssessment.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.faac.colettaAssessment.model.LoggedUser;

public class LoggedUserDAOImpl implements LoggedUserDAO {

	private static final Logger logger = 
			LoggerFactory.getLogger(UserDAOImpl.class);
		 	

	private SessionFactory sessionFactory;
	
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	 
	public LoggedUser save(LoggedUser u) {
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(u);
		tx.commit();
		session.close();
		return u;
	}

	public List<LoggedUser> listAll() {
		
		Session session = this.sessionFactory.openSession();
		List<LoggedUser> personList = session.createQuery("from LoggedUser").list();
		session.close();
		return personList;
	}

	public LoggedUser findById(Long id) {
		Session session=this.sessionFactory.openSession();
		return (LoggedUser) session.get(LoggedUser.class, id);
	}

	public LoggedUser findByUsername(String username) {
		
		Session session=this.sessionFactory.openSession();
		Query query = session.createQuery("select lu from LoggedUser lu join fetch lu.loggedUser user where user.username=:username");
		query.setParameter("username", username);
		List<LoggedUser> resultSet=query.list();
		if(!CollectionUtils.isEmpty(resultSet))
		{
			return (LoggedUser)query.list().get(0);
		}
		return null;
	}

	public void delete(LoggedUser loggedUser) {
		
		if(!loggedUser.isValidID())
		{
			logger.info("User "+loggedUser.getLoggedUser().getUsername()+"cannot be deleted because it hasn't a valid Id");
			return;
		}
		
		Session session=this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(loggedUser);
		tx.commit();
		session.close();
	}

}
