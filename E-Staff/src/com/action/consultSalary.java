package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.model.Rewardandpunish;
import com.model.Salary;
import com.model.Staff;
import com.opensymphony.xwork2.ActionSupport;
import com.service.SalaryService;
import com.tool.DateStringConvert;
import com.tool.JSONUtils;

public class consultSalary extends ActionSupport{
private SalaryService salaryService;
public SalaryService getSalaryService() {
	return salaryService;
}
public void setSalaryService(SalaryService salaryService) {
	this.salaryService = salaryService;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

private String id;
private String st;
private String et;
public String getSt() {
	return st;
}
public void setSt(String st) {
	this.st = st;
}
public String getEt() {
	return et;
}
public void setEt(String et) {
	this.et = et;
}
public String execute() throws Exception {
	 Map<String, Object> map = new HashMap<String, Object>();
	   try {
		   if(st==et)
		   {
			   int m=Integer.parseInt(et.substring(5, 7))%12+1;
			   String y=et.substring(0,4);
			   et=y+"-"+String.format("%02d",m);
		   }
		   st+="-01";
		   et+="-01";
		//  System.out.println("st="+st+"   "+"et="+et);
		  Date sst=DateStringConvert.convertStringToDate(st);
		  Date eet=DateStringConvert.convertStringToDate(et);
		  
          List<Salary> totalSalary=salaryService.consultSalary(id,sst,eet);
         //  for(int i=0;i<totalSalary.size();i++)
          // System.out.println("totalSalary****"+totalSalary.get(i).getStaid());
           
           List<Rewardandpunish> rePunSalary=salaryService.consultRePunSalary(id,sst,eet);
          // for(int i=0;i<totalSalary.size();i++)
          // System.out.println("rePunSalary****"+rePunSalary.get(i).getStaid());
           
           int rank=salaryService.findByStaid(id).get(0).getRank();
           double fundamental=salaryService.findByRank(rank).get(0).getAmount();
          // System.out.println("fundamental****"+fundamental);
           
           map.put("fundamental",fundamental);
           map.put("totalSalary",totalSalary);
           map.put("rePunSalary",rePunSalary);
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
