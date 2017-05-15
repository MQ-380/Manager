package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.tool.JSONUtils;
import com.model.Admin;
import com.model.Department;
import com.opensymphony.xwork2.ActionContext;
import com.service.DepartmentService;

public class consultDepartment {
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		 Map<String, Object> map = new HashMap<String, Object>();
		         String status = null;
		         try {
		                 List<Department> list = departmentService.findAll();
		                 if (list.size() > 0) {
		                     status = "1";
		                     map.put("department", list);
		                 } else {
		                     status = "null";
		                 }
		                 map.put("status", status);
		         	     JSONUtils.toJson(ServletActionContext.getResponse(), map);
		             return "true";
		         } catch (Exception e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		        return "false";
		
	}
}
