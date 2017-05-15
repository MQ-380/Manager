package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.*;
import com.tool.JSONUtils;

public class staffLogin extends ActionSupport{
	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
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
		 String status="1";
		 Staff s = new Staff(); 
		 s.setStaid(userName);
		 s.setPassword(password);
		 
		 List<Staff> staffList=staffService.findByExample(s);
		 if (staffList.size()>0){
			 Map session = (Map)ActionContext.getContext().getSession();
			 session.put("userId",userName);session.put("userPro",staffList.get(0).getRank());
			 
			  map.put("status", status);
			  map.put("type",staffList.get(0).getRank());
			  map.put("id",userName);
	  	     JSONUtils.toJson(ServletActionContext.getResponse(), map);
			return SUCCESS;
		}	else {
			status="0";
			map.put("status", status);
			addFieldError("msg","用户名或密码错误");
			JSONUtils.toJson(ServletActionContext.getResponse(), map);
			return INPUT;
		}
	}
}
