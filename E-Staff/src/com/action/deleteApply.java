package com.action;


import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Applyleave;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LeaveService;
import com.tool.JSONUtils;

public class deleteApply extends ActionSupport{
private String leaveid;
private LeaveService leaveService;

@SuppressWarnings("unchecked")
public String execute() throws Exception {	
	 Map<String, Object> map = new HashMap<String, Object>();
	         try {
	             Applyleave leave=leaveService.findByLeaveid(leaveid).get(0);
	        	 leaveService.delete(leave);
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
