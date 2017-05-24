package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Applyleave;
import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LeaveService;
import com.tool.JSONUtils;

public class consultAllApply extends ActionSupport{
private String id;
private String deid;
private int rank;
public int getRank() {
	return rank;
}

public void setRank(int rank) {
	this.rank = rank;
}
private LeaveService leaveService;

@SuppressWarnings("unchecked")
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	         try {
	        	 List<Applyleave> list=leaveService.consultAllApply(id, deid, rank);
	               for(int i=0;i<list.size();i++)
                		 System.out.println(list.get(i).getLeaveid());
                 
	                   map.put("data", list);
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
public String getDeid() {
	return deid;
}
public void setDeid(String deid) {
	this.deid = deid;
}

public LeaveService getLeaveService() {
	return leaveService;
}
public void setLeaveService(LeaveService leaveService) {
	this.leaveService = leaveService;
}
}
