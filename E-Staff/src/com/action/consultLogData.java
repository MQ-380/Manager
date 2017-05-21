package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Sign;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SignService;
import com.tool.JSONUtils;

public class consultLogData extends ActionSupport{
	private String id;
	private Date st;
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

	private Date et;
	private SignService signService;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			  List<Sign> list=signService.consultLogData(st,et);
		        //for(int i=0;i<list.size();i++)
		       //	 System.out.println(list.get(i).getStaid());
			  map.put("data",list);
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
	public SignService getSignService() {
		return signService;
	}
	public void setSignService(SignService signService) {
		this.signService = signService;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
