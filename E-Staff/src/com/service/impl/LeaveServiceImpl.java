package com.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.model.Applyleave;
import com.model.ApplyleaveDAO;
import com.service.LeaveService;
import com.mydao.*;
public class LeaveServiceImpl implements LeaveService {
private myLeaveDAO myLeaveDAO;
public myLeaveDAO getMyLeaveDAO() {
	return myLeaveDAO;
}
public void setMyLeaveDAO(myLeaveDAO myLeaveDAO) {
	this.myLeaveDAO = myLeaveDAO;
}
private ApplyleaveDAO applyleaveDAO;
public ApplyleaveDAO getApplyleaveDAO() {
	return applyleaveDAO;
}
public void setApplyleaveDAO(ApplyleaveDAO applyleaveDAO) {
	this.applyleaveDAO = applyleaveDAO;
}
public void save(Applyleave leave)
{
	applyleaveDAO.save(leave);
	}
public void delete(Applyleave leave)
{
	applyleaveDAO.delete(leave);
	}
public List<Applyleave> findByLeaveid(String leaveid)
{
	return applyleaveDAO.findByLeaveid(leaveid);
	}
public List<Applyleave>consultPersonalAllApplyByST(Timestamp st,Timestamp et,String staid)
{
	return myLeaveDAO.consultPersonalAllApplyByST(st, et,staid);}
public List<Applyleave>consultPersonalAllApplyByET(Timestamp st,Timestamp et,String staid)
{
	return myLeaveDAO.consultPersonalAllApplyByET(st, et,staid);}
public List<Applyleave> consultAllApply(String id,String deid,int rank)
{
	return myLeaveDAO.consultAllApply(id, deid, rank);}
}
