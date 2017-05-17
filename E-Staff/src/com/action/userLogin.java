package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminService;
import com.tool.JSONUtils;



public class userLogin extends ActionSupport{
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
private AdminService adminService;

public AdminService getAdminService() {
	return adminService;
}
public void setAdminService(AdminService adminService) {
	this.adminService = adminService;
}
@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();

	 Admin a = new Admin(); 
	 a.setAdid(userName);
	 a.setAdpwd(password);
	 List<Admin> adminList=adminService.findByExample(a);
	 if (adminList.size()>0){
		 Map session = (Map)ActionContext.getContext().getSession();
		 session.put("userId",userName);session.put("userPro",adminList.get(0).getAdpro());
		  map.put("status", true);
		  map.put("type",adminList.get(0).getAdpro());
		  map.put("id",userName);
  	     JSONUtils.toJson(ServletActionContext.getResponse(), map);
		return SUCCESS;
	}	else {
		map.put("status", false);
		addFieldError("msg","用户名或密码错误");
		 JSONUtils.toJson(ServletActionContext.getResponse(), map);
		return ERROR;
	}
}
}
