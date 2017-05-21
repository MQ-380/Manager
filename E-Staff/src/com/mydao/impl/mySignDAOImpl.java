package com.mydao.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Sign;
import com.mydao.mySignDAO;
import com.tool.DateStringConvert;
@Repository  
@SuppressWarnings("unchecked") 
public class mySignDAOImpl implements mySignDAO{
@Resource 
private SessionFactory sessionFactory;
public List<Sign> consultLogData(Date st,Date et)
{
	 Query query = sessionFactory.getCurrentSession().createQuery("from Sign s where s.date>=? and s.date<=?");

	 query.setParameter(0, st);  
	 query.setParameter(1, et);  
     List<Sign> list = query.list();  
     return list;  
	}
public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
}
