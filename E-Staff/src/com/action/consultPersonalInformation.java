package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StaffService;
import com.tool.JSONUtils;

public class consultPersonalInformation extends ActionSupport{
	private StaffService staffService;
	private String id;
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	   try {
           List<Staff> list = staffService.findByStaid(id);
      //   for(int i=0;i<list.size();i++)
       	// System.out.println(list.get(i).getName());
        
              map.put("staff", (Staff)list.get(0));
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
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
