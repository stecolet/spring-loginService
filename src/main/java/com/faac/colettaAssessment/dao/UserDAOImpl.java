package com.faac.colettaAssessment.dao;

import java.util.List;

import com.faac.colettaAssessment.model.LoggedUser;
import com.faac.colettaAssessment.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.*;
import org.springframework.util.CollectionUtils;

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = 
			LoggerFactory.getLogger(UserDAOImpl.class);
		 	

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public User save(User u) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(u);
		tx.commit();
		session.close();
		return u;
	}

	public List<User> listAll() {
		Session session = this.sessionFactory.openSession();
		List<User> personList = session.createQuery("from User").list();
		session.close();
		return personList;
	}

	public User findById(Long id) {
		Session session=this.sessionFactory.openSession();
		return (User) session.get(User.class, id);
		
	}

	public void delete(User user) {
		
		logger.info("UserDAO: deleting user with username: "+user.getUsername());
		if(!user.isValidID())
		{
			logger.info("User "+user.getUsername()+"cannot be deleted because it hasn't a valid Id");
			return;
		}
		
		Session session=this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}
	
	public User findByUsername(String username) {
		
		Session session=this.sessionFactory.openSession();
		Query query = session.createQuery("select u from User u where u.username=:username");
		query.setParameter("username", username);
		List<User> resultSet=query.list();
		if(!CollectionUtils.isEmpty(resultSet))
		{
			return (User)query.list().get(0);
		}
		return null;
	}

}
