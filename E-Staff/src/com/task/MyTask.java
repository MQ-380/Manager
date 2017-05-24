package com.task;

import com.model.SignDAO;
import com.mydao.myLeaveDAO;
/** 
 * 定义任务 
 */  
public class MyTask {  
	private SignDAO signDAO;
	private myLeaveDAO myLeaveDAO;
    
    public SignDAO getSignDAO() {
		return signDAO;
	}
	public void setSignDAO(SignDAO signDAO) {
		this.signDAO = signDAO;
	}
	public myLeaveDAO getMyLeaveDAO() {
		return myLeaveDAO;
	}
	public void setMyLeaveDAO(myLeaveDAO myLeaveDAO) {
		this.myLeaveDAO = myLeaveDAO;
	}
	public void show() {  
      //  System.out.println("show method 1");  
    }  
    public void print() {  
     //  System.out.println("print method 1");  
    }  
    public void calSignStatus()
    {
    	
    }
    public void calSalary()
    {
    	
    }
}