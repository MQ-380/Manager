package com.action;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Sign;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SignService;
import com.tool.JSONUtils;
import com.tool.timeTool;

public class Logout extends ActionSupport{
	private String id;
	private SignService signService;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Date date=timeTool.GetNowDate();
			Time time=timeTool.GetNowTime();
			Sign sign=new Sign();
			sign.setStaid(id);
			sign.setDate(date);	
			Sign ss=(Sign)signService.findByExample(sign).get(0);
			sign.setId(ss.getId());
			sign.setSignin(ss.getSignin());
			sign.setSignout(time);
			signService.save(sign);
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
