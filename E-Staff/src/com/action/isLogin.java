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

public class isLogin  extends ActionSupport{
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
	    {//System.out.println(signList.get(0).getStaid()+"*********************");
	    	map.put("in",false);}else
		  {//System.out.println("test1***********************");
	    	map.put("in",true);
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
