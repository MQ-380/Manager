package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Admin;
import com.model.Department;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DepartmentService;
import com.tool.JSONUtils;

public class editDepartment extends ActionSupport{
private String id;
private String name;
private DepartmentService departmentService;

@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	         String status = null;
	         try {
	        	 Department dep=new Department();
	        	 List<Department> depList=departmentService.findByDeid(id);
	    		 dep=depList.get(0);
	        	 dep.setName(name);
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
