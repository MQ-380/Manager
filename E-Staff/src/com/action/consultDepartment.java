package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.tool.JSONUtils;
import com.model.Admin;
import com.model.Department;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.DepartmentService;

public class consultDepartment extends ActionSupport{
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
		         try {
		                 List<Department> list = departmentService.findAll();
		                	 //for(int i=0;i<list.size();i++)
		                		// System.out.println(list.get(i).getDeid()+" "+list.get(i).getName());
		                 
		                     map.put("department", list);
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
