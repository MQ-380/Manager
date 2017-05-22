
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
import com.mydao.mySignDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.*;
import com.tool.timeTool;




public class test {
	
	
	 @Test  
	 /*
	  * ≤‚ ‘DAO≤„
	  */
	    public void testUserDao() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	myLeaveDAO dao =  (myLeaveDAO)ctx.getBean("myLeaveDAO");
	    	
	    	List<Applyleave>list=dao.consultAllApply("170010002", "002",3);
	    	System.out.println("********"+list.size());
	    	for(int i=0;i<list.size();i++)
	    	System.out.println("********#####"+list.get(i).getLeaveid());
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
	  * ≤‚ ‘action
	  */
	    public void testUserAction() throws Exception{  
		
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 addBounsPunish ser = (addBounsPunish)ctx.getBean("addBounsPunish"); 
		 Rewardandpunish repun=new  Rewardandpunish();
		 repun.setAmount(12.1);
		ser.setRepun(repun);
	     ser.execute();
	    }
	
	 
}
