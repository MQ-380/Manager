package com.mydao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.model.Applyleave;
import com.model.Sign;
import com.mydao.myLeaveDAO;
@Repository  
@SuppressWarnings("unchecked") 
public class myLeaveDAOImpl implements myLeaveDAO{
	@Resource 
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<Applyleave> consultPersonalAllApplyByST(Timestamp st,Timestamp et,String staid)
	{
		 Query query = sessionFactory.getCurrentSession().createQuery("from Applyleave a where a.stime>=? and a.stime<=? and a.staid=?");

		 query.setParameter(0, st);  
		 query.setParameter(1, et);  
		 query.setParameter(2, staid);
	     List<Applyleave> list = query.list();  
	     return list;  
	}
	public List<Applyleave> consultPersonalAllApplyByET(Timestamp st,Timestamp et,String staid)
	{
		 Query query = sessionFactory.getCurrentSession().createQuery("from Applyleave a where a.etime>=? and a.etime<=? and a.staid=?");
		 query.setParameter(0, st);  
		 query.setParameter(1, et);  
		 query.setParameter(2, staid);
	     List<Applyleave> list = query.list();  
	     return list;  
	}
	public List<Applyleave> consultAllApply(String id,String deid,int rank)
	{
             if(rank==2)
             {
            	 Query query = sessionFactory.getCurrentSession().createQuery("select a from Applyleave a,Staff s where a.staid=s.staid and s.deid=? and s.staid!=?");
    			 query.setParameter(0, deid);  
    			 query.setParameter(1, id);  
    		     List<Applyleave> list = query.list();  
    		     return list; 
             }
             else if(rank==3||rank==0)
             {
            	 Query query = sessionFactory.getCurrentSession().createQuery("select a from Applyleave a,Staff s where a.staid=s.staid and s.deid=?");
    			 query.setParameter(0, deid);  
    		     List<Applyleave> list = query.list();  
    		     return list; 
             }
             else
             {
            	 List<Applyleave> list = null;
            	 return list;
             }
		 
	}
	public boolean isApply(String id,Date date)
	{

   	 Query query = sessionFactory.getCurrentSession().createQuery("from Applyleave a where a.staid=? and a.stime<=? and a.etime>=? and isconfirm = ?");
		 query.setParameter(0,id);  
		 query.setParameter(1,date);  
		 query.setParameter(2,date);  
		 query.setParameter(3,1);  
	     List<Applyleave> list = query.list();  
	     if(list.size()==0)
	    	 return false;
	    else
		return true;
	}
}
