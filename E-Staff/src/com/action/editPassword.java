package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StaffService;
import com.tool.JSONUtils;

public class editPassword  extends ActionSupport {
	private StaffService staffService;
	private String id;
	private String password;
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			  Staff staff=(Staff) staffService.findByStaid(id).get(0);
			  staff.setPassword(password);
			  staffService.save(staff);
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
	public StaffService getStaffService() {
		return staffService;
	}
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
