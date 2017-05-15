package com.action;

import java.util.List;
import java.util.Map;

import com.model.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminService;



public class userLogin extends ActionSupport{
private Admin admin;
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
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
	 Admin a = new Admin(); 
	 a.setAdid(admin.getAdid());
	 a.setAdpwd(admin.getAdpwd());
	 
	 List<Admin> adminList=adminService.findByExample(a);
	 if (adminList.size()>0){
		Map session = (Map)ActionContext.getContext().getSession();
		session.put("admin", admin);
		return SUCCESS;
	}	else {
		addFieldError("msg","用户名或密码错误");
		return INPUT;
	}
}
}
