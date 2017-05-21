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
import com.tool.timeTool;

public class isLogout extends ActionSupport{
	private String id;
	private SignService signService;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Date date=timeTool.GetNowDate();
			Sign sign=new Sign();
		    sign.setDate(date);
		    sign.setStaid(id);
		    List<Sign> signList=signService.findByExample(sign);
		    if(signList!=null)
		    {
		    	if(signList.get(0).getSignout()!=null)
		    	{//System.out.println("test1***********************");
		    	map.put("out",false);}
		    	else
		    	{//System.out.println("test2***********************");
		    	map.put("out",true);}
		    }
		    else
			{
		    	//System.out.println("test3***********************");
		    	map.put("out",false);
		    }		   
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
