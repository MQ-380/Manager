package com.task;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

















import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.Fundamentalsalary;
import com.model.FundamentalsalaryDAO;
import com.model.Rewardandpunish;
import com.model.Salary;
import com.model.SalaryDAO;
import com.model.Sign;
import com.model.SignDAO;
import com.model.Staff;
import com.model.StaffDAO;
import com.model.RewardandpunishDAO;
import com.mydao.myLeaveDAO;
import com.tool.timeTool;
import com.mydao.myLoginDAO;
import com.mydao.mySalaryDAO;
/** 
 * 定义任务 
 */  
public class MyTask {  
	
	private SignDAO signDAO;
	private myLeaveDAO myLeaveDAO;
    private StaffDAO staffDAO;
    private RewardandpunishDAO rewardandpunishDAO;
    private myLoginDAO myLoginDAO;
    private SalaryDAO salaryDAO;
    private mySalaryDAO mySalaryDAO;
    private FundamentalsalaryDAO fundamentalsalaryDAO;
    public FundamentalsalaryDAO getFundamentalsalaryDAO() {
		return fundamentalsalaryDAO;
	}
	public void setFundamentalsalaryDAO(FundamentalsalaryDAO fundamentalsalaryDAO) {
		this.fundamentalsalaryDAO = fundamentalsalaryDAO;
	}
	public mySalaryDAO getMySalaryDAO() {
		return mySalaryDAO;
	}
	public void setMySalaryDAO(mySalaryDAO mySalaryDAO) {
		this.mySalaryDAO = mySalaryDAO;
	}
	public SalaryDAO getSalaryDAO() {
		return salaryDAO;
	}
	public void setSalaryDAO(SalaryDAO salaryDAO) {
		this.salaryDAO = salaryDAO;
	}
	public myLoginDAO getMyLoginDAO() {
		return myLoginDAO;
	}
	public void setMyLoginDAO(myLoginDAO myLoginDAO) {
		this.myLoginDAO = myLoginDAO;
	}
	public RewardandpunishDAO getRewardandpunishDAO() {
		return rewardandpunishDAO;
	}
	public void setRewardandpunishDAO(RewardandpunishDAO rewardandpunishDAO) {
		this.rewardandpunishDAO = rewardandpunishDAO;
	}
	public StaffDAO getStaffDAO() {
		return staffDAO;
	}
	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}
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
	
	
    }  
    public void print() {  
     //  System.out.println("print method 1");  
    }  
    public void calSignStatus() throws ParseException
    {
    	if(timeTool.isTodayWeekDay())
    	{  
    		Time st=timeTool.GetStartTime();
    	    Time et=timeTool.GetEndTime();
    		Date date=new Date();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   		    date=format.parse(format.format(date));
    		List<Staff> stafflist=staffDAO.findAll();
    		for(int i=0;i<stafflist.size();i++)
    		{
    			String id=stafflist.get(i).getStaid();
    			Sign sign=new Sign();
    			sign.setStaid(id);
    			sign.setDate(date);
    			List<Sign> signlist=myLoginDAO.findByStaidDate(id, date);
    			boolean isapply=myLeaveDAO.isApply(id, date);
    			if(isapply)
    			{
    				if(signlist.size()==0)
    					{sign.setStatus("请假");signDAO.save(sign);}
    				else
    				{
    					sign=(Sign)signlist.get(0);
    					sign.setStatus("请假");
    					signDAO.save(sign);
    				}
    			}
    			else
    			{
    				if(signlist.size()==0)
    				{
        				this.addRePun(id,0,date,320.00,"考勤-未按时上班");
        				sign.setStatus("旷班");signDAO.save(sign);
        			}
    				else
    				{
    					Time in=((Sign)signlist.get(0)).getSignin();
    					Time out=((Sign)signlist.get(0)).getSignout();
    					sign=(Sign)signlist.get(0);
    					if(in.getTime()>st.getTime())
    					{
    						double between = (double)(in.getTime() - st.getTime());
    						if(out.getTime()<et.getTime())
    						{
    							between += (double)(et.getTime() - out.getTime());
    							double hour = between / (60 * 60 * 1000);
    							this.addRePun(id,0,date,40*hour,"考勤-迟到和早退共计"+String.format("%.2f",hour)+"小时");
    							sign.setStatus("迟到/早退");signDAO.save(sign);
    						}
    						else
    						{
    							double hour = between / (60 * 60 * 1000);
    							this.addRePun(id,0,date,40*hour,"考勤-迟到"+hour+"小时");
    							sign.setStatus("迟到");signDAO.save(sign);
    						}
    					}
    					else
    					{
    						if(out.getTime()<et.getTime())
    						{
    							double between = (double)(et.getTime() - out.getTime());
    							double hour = between / (60 * 60 * 1000);
    							this.addRePun(id,0,date,40*hour,"考勤-早退"+String.format("%.2f",hour)+"小时");
    							sign.setStatus("早退");signDAO.save(sign);
    						}
    						else
    						{
    							sign.setStatus("按时上班");signDAO.save(sign);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    public void addRePun(String id,int type,Date date,double amount,String description)
    {
    	Rewardandpunish rePun =new Rewardandpunish();	
		rePun.setStaid(id);
		rePun.setAmount(amount);
		rePun.setType(type);
		rePun.setDescription(description);
		rePun.setDate(date);
		rewardandpunishDAO.save(rePun);
    }
    public void calSalary() throws ParseException
    {
    	Date et=new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str=format.format(et);
		et=format.parse(str);
		int m=Integer.parseInt(str.substring(5,7));
		int y=Integer.parseInt(str.substring(0,4));
		if(m==1) y-=1;
		m=(m-1)%12;
		str=String.valueOf(y)+"-"+String.format("%02d",m)+"-01";
		Date st=format.parse(str);
		
		System.out.println("st="+st+" et="+et);
		
    	List<Staff> stafflist=staffDAO.findAll();
		for(int i=0;i<stafflist.size();i++)
		{
			String id=stafflist.get(i).getStaid();
			int rank=stafflist.get(i).getRank();
			double re=mySalaryDAO.findRe(id, st, et);
			double pun=mySalaryDAO.findPun(id, st, et);
			double fun=((Fundamentalsalary)fundamentalsalaryDAO.findByRank(rank).get(0)).getAmount();
			Salary salary=new Salary();
			salary.setPunish(pun);
			salary.setReward(re);
			salary.setTotal(re+fun-pun);
			salary.setStaid(id);
			salary.setDate(st);
			salaryDAO.save(salary);
		}
    }
}