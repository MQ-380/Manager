package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Admin;
import com.model.Department;
import com.model.Rewardandpunish;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.RePunService;
import com.tool.JSONUtils;

public class addBounsPunish extends ActionSupport {
private Rewardandpunish repun;
private RePunService rePunService;



@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 Department dep = new Department();

	 Map<String, Object> map = new HashMap<String, Object>();
	  try {
	  rePunService.save(repun);
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


public RePunService getRePunService() {
	return rePunService;
}
public void setRePunService(RePunService rePunService) {
	this.rePunService = rePunService;
}
public Rewardandpunish getRepun() {
	return repun;
}
public void setRepun(Rewardandpunish repun) {
	this.repun = repun;
}

}
