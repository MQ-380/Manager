package com.action;

import com.model.Department;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DepartmentService;

public class deleteDepartment extends ActionSupport{
	private String[] departmentID;
	private DepartmentService departmentService;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {		     
		         try {
		      
		             departmentService.deleteDepartment(departmentID);
		             return SUCCESS;
		         } catch (Exception e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		        return ERROR;
	}
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String[] getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String[] departmentID) {
		this.departmentID = departmentID;
	}

}
