package com.action;

import java.util.List;
import java.util.Map;

import com.model.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.*;

public class staffLogin extends ActionSupport{
	private Staff staff;
	
	private StaffService staffService;
	

	public StaffService getStaffService() {
		return staffService;
	}


	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}


	public Staff getStaff() {
		return staff;
	}


	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		 Staff s = new Staff(); 
		 s.setStaid(staff.getStaid());
		 s.setPassword(staff.getPassword());
		 
		 List<Staff> staffList=staffService.findByExample(s);
		 if (staffList.size()>0){
			int pro=staffList.get(0).getRank();
			Map session = (Map)ActionContext.getContext().getSession();
			session.put("staff", staff);
			session.put("userPro",pro);
			return SUCCESS;
		}	else {
			addFieldError("msg","用户名或密码错误");
			return INPUT;
		}
	}
}
