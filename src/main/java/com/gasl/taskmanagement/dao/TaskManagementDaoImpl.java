package com.gasl.taskmanagement.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gasl.taskmanagement.dto.Tasks;

@Repository
public class TaskManagementDaoImpl implements TaskManagementDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Tasks saveTask(Tasks task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return task;
	}

}
