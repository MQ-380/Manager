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
public List findByExample(Staff staff){
		
		return staffDAO.findByExample(staff);
	}
}
