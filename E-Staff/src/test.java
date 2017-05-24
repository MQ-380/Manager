
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.*;
import com.model.*;
import com.mydao.myLeaveDAO;
import com.mydao.myLoginDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.*;
import com.tool.DateStringConvert;
import com.tool.timeTool;

public class test {
	 @Test  
	 /*
	  * 测试DAO层
	  */
	    public void testUserDao() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	myLoginDAO dao =  (myLoginDAO)ctx.getBean("myLoginDAO");
	    	Date st=DateStringConvert.convertStringToDate("2017-05-01");
	    	Date et=DateStringConvert.convertStringToDate("2017-05-31");
	    	List<Sign>list=dao.consultLogData("170010001",st,et);
	    	System.out.println("********"+list.size());
	    	for(int i=0;i<list.size();i++)
	    	System.out.println("********#####"+list.get(i).getStaid());
	    }
	 @Test  
	 /*
	  * 测试Service层
	  */
	    public void testUserService() throws Exception{  
	    	//ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	// 	LeaveService ser =  (LeaveService)ctx.getBean("leaveService");	    	 
	    }
	 @Test  
	 /*
	  * 测action
	  */
	    public void testUserAction() throws Exception{  
		
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 consultLogData ser = (consultLogData)ctx.getBean("consultLogData"); 
		ser.setId("170010001");
		ser.setSt("2017-05-01");
		ser.setEt("2017-05-30");
	     ser.execute();
	     
	    }
	
	 @Test
	 public void test() throws InterruptedException
	 {
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	      System.out.println("开始执行了...");
	        Thread.sleep(6000);
	        System.out.println("结束执行了...");
	 }
}
