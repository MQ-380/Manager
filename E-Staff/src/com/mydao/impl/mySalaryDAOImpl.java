package com.mydao.impl;

import org.hibernate.SessionFactory;

import com.mydao.mySalaryDAO;

public class mySalaryDAOImpl implements mySalaryDAO{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
