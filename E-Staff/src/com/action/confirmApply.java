package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Applyleave;
import com.model.Department;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LeaveService;
import com.tool.DateStringConvert;
import com.tool.JSONUtils;

public class confirmApply extends ActionSupport {
private String id;
private String leaveid;
private LeaveService leaveService;
@SuppressWarnings("unchecked")
public String execute() throws Exception{
	 Map<String, Object> map = new HashMap<String, Object>();
	  try {
	  Applyleave leave=leaveService.findByLeaveid(leaveid).get(0);
	  leave.setIsconfirm(1);
	  leave.setOperator(id);
	  leaveService.save(leave);
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

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLeaveid() {
	return leaveid;
}
public void setLeaveid(String leaveid) {
	this.leaveid = leaveid;
}
public LeaveService getLeaveService() {
	return leaveService;
}
public void setLeaveService(LeaveService leaveService) {
	this.leaveService = leaveService;
}
}
