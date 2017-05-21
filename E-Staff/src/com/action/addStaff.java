package com.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Department;
import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DepartmentService;
import com.service.StaffService;
import com.tool.DateStringConvert;
import com.tool.JSONUtils;

public class addStaff extends ActionSupport{
private Staff staff;
private StaffService staffService;
private DepartmentService departmentService;

@SuppressWarnings("unchecked")
public String execute() throws Exception{
	 Map<String, Object> map = new HashMap<String, Object>();
	  try {
	String deid=staff.getDeid();
	Department dep=new Department();

	 
	dep=(Department)departmentService.findByDeid(deid).get(0);

	 
	int stanum=dep.getStanum()+1;
	dep.setStanum(stanum);
	departmentService.save(dep);
	
	String time= DateStringConvert.convertDateToString(staff.getTime());
	 	String staid=time.substring(2,4)+deid+String.format("%04d",stanum);
	staff.setStaid(staid);
	staff.setPassword(staid);
	staff.setLeavenum(0);
	staff.setState(1);
	staffService.save(staff);
	 map.put("status", true);
	  JSONUtils.toJson(ServletActionContext.getResponse(), map);
	return SUCCESS;
	  }  catch (Exception e) {
			 // TODO Auto-generated catch block
	         e.printStackTrace();	
	         map.put("status",false);
			  JSONUtils.toJson(ServletActionContext.getResponse(), map);
	         return ERROR;
	}
}

public DepartmentService getDepartmentService() {
	return departmentService;
}
public void setDepartmentService(DepartmentService departmentService) {
	this.departmentService = departmentService;
}

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


}
