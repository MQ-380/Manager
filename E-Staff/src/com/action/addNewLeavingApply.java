package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Applyleave;
import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LeaveService;
import com.service.StaffService;
import com.tool.JSONUtils;

public class addNewLeavingApply extends ActionSupport {
private Applyleave leave;
public Applyleave getLeave() {
	return leave;
}

public void setLeave(Applyleave leave) {
	this.leave = leave;
}
private LeaveService leaveService;
private StaffService staffService;
public StaffService getStaffService() {
	return staffService;
}

public void setStaffService(StaffService staffService) {
	this.staffService = staffService;
}

@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	  try {
		  Staff staff=new Staff();
		  staff.setStaid(leave.getStaid());
		  staff=(Staff)staffService.findByExample(staff).get(0);
		  int leavenum=staff.getLeavenum()+1;
		  staff.setLeavenum(leavenum);
		  staffService.save(staff);
		  String leaveid=staff.getStaid()+String.valueOf(leavenum);
		  leave.setLeaveid(leaveid);
		  leave.setIsconfirm(0);
		  leaveService.save(leave);
		  map.put("status", true);
		  JSONUtils.toJson(ServletActionContext.getResponse(), map);
	       return SUCCESS;
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
         e.printStackTrace();
   	     map.put("status", false);
	     JSONUtils.toJson(ServletActionContext.getResponse(), map);
         return ERROR;
		 }
}


public LeaveService getLeaveService() {
	return leaveService;
}
public void setLeaveService(LeaveService leaveService) {
	this.leaveService = leaveService;
}
}
