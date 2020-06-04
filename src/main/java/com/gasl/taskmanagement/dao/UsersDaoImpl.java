package com.gasl.taskmanagement.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gasl.taskmanagement.dto.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users loadByUsername(String userName) {
		Query<?> userQuery = sessionFactory.getCurrentSession().createQuery("from Users u where u.userName =:userName");
		userQuery.setParameter("userName", userName);

		List<?> queryResult = userQuery.getResultList();
		if(queryResult != null && queryResult.size() > 0) {
			Users user = (Users) queryResult.get(0);
			return user;
		}
		return null;
	}

	@Override
	public Users createNewUser(Users user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

}
