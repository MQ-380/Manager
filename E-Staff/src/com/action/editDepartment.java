package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Department;
import com.service.DepartmentService;
import com.tool.JSONUtils;

public class editDepartment {
private String id;
private String name;
private DepartmentService departmentService;

@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 
	         String status = null;
	         try {
	        	 Department dep=new Department();
	        	 dep.setDeid(id);dep.setName(name);
	             departmentService.save(dep);
	             return "true";
	         } catch (Exception e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	        return "false";
	
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public DepartmentService getDepartmentService() {
	return departmentService;
}
public void setDepartmentService(DepartmentService departmentService) {
	this.departmentService = departmentService;
}
}
