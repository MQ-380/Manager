package com.service.impl;

import java.util.List;

import com.model.Admin;
import com.model.AdminDAO;
import com.service.AdminService;

public class AdminServiceImpl implements AdminService{
	private AdminDAO adminDAO;
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
public List findByExample(Admin admin){
		
		return adminDAO.findByExample(admin);
	}
public List findByAdid(String adid){
	
	return adminDAO.findByAdid(adid);
}

public void save(Admin admin) {
	// TODO Auto-generated method stub
	adminDAO.save(admin);
}
}
