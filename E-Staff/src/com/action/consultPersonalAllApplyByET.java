package com.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Applyleave;
import com.opensymphony.xwork2.ActionSupport;
import com.service.LeaveService;
import com.tool.JSONUtils;
import com.tool.timeTool;

public class consultPersonalAllApplyByET extends ActionSupport{
	private String id;
	private Date stime;
	private Date etime;
	private LeaveService leaveService;

	public LeaveService getLeaveService() {
		return leaveService;
	}

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
	          Timestamp st=timeTool.DateToDateTime(stime);
	          Timestamp et=timeTool.DateToDateTime(etime);
	          List<Applyleave> list=leaveService.consultPersonalAllApplyByET(st,et,id);
	         // for(int i=0;i<list.size();i++)
	  	    	//System.out.println("********@@@@@"+list.get(i).getLeaveid());
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
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	public Date getEtime() {
		return etime;
	}
	public void setEtime(Date etime) {
		this.etime = etime;
	}

	}
