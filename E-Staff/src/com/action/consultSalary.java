package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SalaryService;
import com.tool.JSONUtils;

public class consultSalary extends ActionSupport{
private SalaryService salaryService;
public SalaryService getSalaryService() {
	return salaryService;
}
public void setSalaryService(SalaryService salaryService) {
	this.salaryService = salaryService;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Date getSt() {
	return st;
}
public void setSt(Date st) {
	this.st = st;
}
public Date getEt() {
	return et;
}
public void setEt(Date et) {
	this.et = et;
}
private String id;
private Date st;
private Date et;
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	   try {
       
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
