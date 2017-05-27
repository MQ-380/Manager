package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Sign;
import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StaffService;
import com.tool.DateStringConvert;
import com.tool.JSONUtils;

public class consultNameByStaid extends ActionSupport{
private StaffService staffService;
private String staid;
public StaffService getStaffService() {
	return staffService;
}
public void setStaffService(StaffService staffService) {
	this.staffService = staffService;
}
public String getStaid() {
	return staid;
}
public void setStaid(String staid) {
	this.staid = staid;
}
@SuppressWarnings("unchecked")
public String execute() throws Exception {
	Map<String, Object> map = new HashMap<String, Object>();
	try {
		String name=((Staff)staffService.findByStaid(staid).get(0)).getName();
		System.out.println("*******"+name);
		map.put("name",name);
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
