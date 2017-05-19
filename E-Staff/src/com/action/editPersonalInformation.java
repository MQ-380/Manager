package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StaffService;
import com.tool.JSONUtils;

public class editPersonalInformation extends ActionSupport {
	private StaffService staffService;
	private Staff staff;
	public String execute() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			  String staid=staff.getStaid();
			  Staff prestaff=(Staff)staffService.findByStaid(staid).get(0);
			  staff.setId(prestaff.getId());
			  staff.setPassword(prestaff.getPassword());
			  staff.setTime(prestaff.getTime());
			  staff.setDeid(prestaff.getDeid());
			  staff.setRank(prestaff.getRank());
			  staff.setState(prestaff.getState());
			  staff.setLeavenum(prestaff.getLeavenum());
	          staffService.save(staff);
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
public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
}
