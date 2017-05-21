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

public class addDepartment extends ActionSupport {
	private Admin admin;
    public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	private String departmentname;
	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	private DepartmentService departmentService;
	private AdminService adminService;
    public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		 Department dep = new Department();

		 Map<String, Object> map = new HashMap<String, Object>();
		  try {
		 Map session = (Map)ActionContext.getContext().getSession();
		 String adid=((String)session.get("userId"));
		 
		 Admin a=new Admin();
		 a.setAdid(adid);
		 List<Admin> adminList=adminService.findByExample(a);
		 a=adminList.get(0);
		 
		 int denum=a.getDenum()+1;
		 a.setDenum(denum);
		 adminService.save(a);
		 String id=String.format("%03d",denum);
		 dep.setName(departmentname);
		 dep.setDeid(id);
		 dep.setStanum(0);
		 departmentService.save(dep);
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

}
