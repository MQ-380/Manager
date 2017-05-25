
import java.sql.Timestamp;
import java.text.ParseException;
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
import com.mydao.mySalaryDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.*;
import com.task.MyTask;
import com.tool.DateStringConvert;
import com.tool.timeTool;

public class test {
	 @Test  
	 /*
	  * ≤‚ ‘DAO≤„
	  */
	    public void testUserDao() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	mySalaryDAO dao =  (mySalaryDAO)ctx.getBean("mySalaryDAO");
	    	Date st=DateStringConvert.convertStringToDate("2017-05-01");
	    	Date et=DateStringConvert.convertStringToDate("2017-05-31");
	    	List<Salary>list=dao.consultSalary("170010001",st,et);
	    	System.out.println("********"+list.size());
	    	for(int i=0;i<list.size();i++)
	    	System.out.println("********#####"+list.get(i).getStaid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘Service≤„
	  */
	    public void testUserService() throws Exception{  
	    	//ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	// 	LeaveService ser =  (LeaveService)ctx.getBean("leaveService");	    	 
	    }
	 @Test  
	 /*
	  * ≤‚action
	  */
	    public void testUserAction() throws Exception{  
		
			ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
consultNameByStaid ser =  (consultNameByStaid)ctx.getBean("consultNameByStaid");	 
	
	   ser.setStaid("170010001");
	     //ser.execute();
	    }
	
	 @Test
	 public void test() throws InterruptedException, ParseException
	 {
	
	 }
}
