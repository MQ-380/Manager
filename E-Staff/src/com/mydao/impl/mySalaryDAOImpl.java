package com.mydao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.model.Rewardandpunish;
import com.model.Salary;
import com.model.Sign;
import com.mydao.mySalaryDAO;

public class mySalaryDAOImpl implements mySalaryDAO{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<Salary> consultSalary(String id,Date st,Date et)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Salary s where s.date>=? and s.date<=? and s.staid=?");

		 query.setParameter(0, st);  
		 query.setParameter(1, et);  
		 query.setParameter(2, id);
	     List<Salary> list = query.list();  
	     return list;
	}
	public List<Rewardandpunish> consultRePunSalary(String id,Date st,Date et)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("from Rewardandpunish r where r.date>=? and r.date<=? and r.staid=?");

		 query.setParameter(0, st);  
		 query.setParameter(1, et);  
		 query.setParameter(2, id);
	     List<Rewardandpunish> list = query.list();  
	     return list;
	}
}
