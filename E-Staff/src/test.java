
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
	    	mySignDAO dao =  (mySignDAO)ctx.getBean("mySignDAO");
	    	Date st=timeTool.GetNowDate();
	    	Date et=timeTool.GetNowDate();
	        List<Sign>list=dao.consultLogData(st, et);
	    	 System.out.println("dao"+list.get(0).getStaid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘Service≤„
	  */
	    public void testUserService() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	 	SignService ser =  (SignService)ctx.getBean("signService");	    	 
	    	 	Date st=timeTool.GetNowDate();
		    	Date et=timeTool.GetNowDate();
		        List<Sign>list=ser.consultLogData(st, et);
		    	 System.out.println("ser"+list.get(0).getStaid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘action
	  */
	    public void testUserAction() throws Exception{  
		
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 consultLogData ser = (consultLogData)ctx.getBean("consultLogData"); 
         ser.setId("170010001");
         ser.setSt(timeTool.GetNowDate());
         ser.setEt(timeTool.GetNowDate());
	   ser.execute();
	    }
	
	 
}
