package com.service.impl;

import java.util.List;






import com.model.Staff;
import com.model.StaffDAO;
import com.service.*;

public class StaffServiceImpl implements StaffService{
	private StaffDAO staffDAO;
	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}
	public void save(Staff staff)
	{
		staffDAO.save(staff);
	}
public List findByExample(Staff staff){
		
		return staffDAO.findByExample(staff);
	}
public List findAll()
{
	return staffDAO.findAll();}
public List findByStaid(String staid)
{
	return staffDAO.findByStaid(staid);}
public void deleteStaff(String[] staffID)
{
	staffDAO.deleteStaff(staffID);
}
}
