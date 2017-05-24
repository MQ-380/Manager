package com.mydao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.model.Sign;
import com.mydao.myLeaveDAO;
import com.mydao.myLoginDAO;

public class myLoginDAOImpl implements myLoginDAO{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<Sign> consultLogData(String id,Date st,Date et)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Sign s where s.date>=? and s.date<=? and s.staid=?");
		System.out.println("here");
		 query.setParameter(0, st);  
		 query.setParameter(1, et);  
		 query.setParameter(2, id);
	     List<Sign> list = query.list();  
	     return list;  
	}
}
