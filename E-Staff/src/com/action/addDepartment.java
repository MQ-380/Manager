package com.action;

import java.util.List;
import java.util.Map;

import com.model.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.*;

public class addDepartment extends ActionSupport {
	private Admin admin;
    public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	private Department department;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		 Department dep = new Department(); 
		 Map session = (Map)ActionContext.getContext().getSession();
		 
		 String adid=((Admin)session.get("admin")).getAdid();
		 
		 Admin a=new Admin();
		 a.setAdid(adid);
		 List<Admin> adminList=adminService.findByExample(a);
		 a=adminList.get(0);
		 
		 int denum=a.getDenum()+1;
		 a.setDenum(denum);
		 adminService.save(a);
		 String id=String.format("%03d",denum);
		 dep.setName(department.getName());
		 dep.setDeid(id);
		 dep.setStanum(0);
		 departmentService.save(dep);
		 return SUCCESS;
		 }

}
