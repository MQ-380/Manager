package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.service.StaffService;
import com.tool.JSONUtils;

public class deleteStaff extends ActionSupport{
	private String[] staffID;
	private StaffService staffService;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {	
		 Map<String, Object> map = new HashMap<String, Object>();
		         try {
		        	 staffService.deleteStaff(staffID);
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
	public String[] getStaffID() {
		return staffID;
	}
	public void setStaffID(String[] staffID) {
		this.staffID = staffID;
	}
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
