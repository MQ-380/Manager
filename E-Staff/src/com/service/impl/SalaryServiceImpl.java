package com.service.impl;

import java.util.Date;
import java.util.List;

import com.model.Fundamentalsalary;
import com.model.FundamentalsalaryDAO;
import com.model.Rewardandpunish;
import com.model.Salary;
import com.model.SalaryDAO;
import com.model.Staff;
import com.model.StaffDAO;
import com.service.SalaryService;

public class SalaryServiceImpl implements SalaryService{
private SalaryDAO salaryDAO;
private com.mydao.mySalaryDAO mySalaryDAO;
private FundamentalsalaryDAO fundamentalsalaryDAO;
public FundamentalsalaryDAO getFundamentalsalaryDAO() {
	return fundamentalsalaryDAO;
}

public void setFundamentalsalaryDAO(FundamentalsalaryDAO fundamentalsalaryDAO) {
	this.fundamentalsalaryDAO = fundamentalsalaryDAO;
}

public StaffDAO getStaffDAO() {
	return staffDAO;
}

public void setStaffDAO(StaffDAO staffDAO) {
	this.staffDAO = staffDAO;
}
private StaffDAO staffDAO;
public com.mydao.mySalaryDAO getMySalaryDAO() {
	return mySalaryDAO;
}

public void setMySalaryDAO(com.mydao.mySalaryDAO mySalaryDAO) {
	this.mySalaryDAO = mySalaryDAO;
}

public SalaryDAO getSalaryDAO() {
	return salaryDAO;
}

public void setSalaryDAO(SalaryDAO salaryDAO) {
	this.salaryDAO = salaryDAO;
}
public List<Salary> consultSalary(String id,Date st,Date et)
{
	return mySalaryDAO.consultSalary(id, st, et);
	}
public List<Rewardandpunish> consultRePunSalary(String id,Date st,Date et)
{
	return mySalaryDAO.consultRePunSalary(id, st, et);
	}
public List<Fundamentalsalary> findByRank(int rank)
{
	return fundamentalsalaryDAO.findByRank(rank); }
public List<Staff> findByStaid(String id)
{
	return staffDAO.findByStaid(id);
	}
}
