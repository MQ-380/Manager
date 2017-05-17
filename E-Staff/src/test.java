
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.*;
import com.model.*;
import com.opensymphony.xwork2.ActionContext;
import com.service.*;




public class test {
	 @Test  
	 /*
	  * ≤‚ ‘DAO≤„
	  */
	    public void testUserDao() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	AdminDAO dao =  (AdminDAO)ctx.getBean("adminDAO");
	        List<Admin> adminlist= dao.findByAdid("123");   
	    	 System.out.println("dao"+adminlist.get(0).getAdid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘Service≤„
	  */
	    public void testUserService() throws Exception{  
	    	ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    	 	AdminService ser =  (AdminService)ctx.getBean("adminService");	    	 
	    	 	  List<Admin> adminlist= ser.findByAdid("123"); 
	    	 	 System.out.println("service"+adminlist.get(0).getAdid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘action
	  */
	    public void testUserAction() throws Exception{  
		
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    	 addStaff ser =  (addStaff)ctx.getBean("addStaff"); 
	     Staff sta=new Staff();
	     String str="2017-01-23";
	     SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
	     Date date=format.parse(str);
	     sta.setTime(date);
	     sta.setDeid("001");
	     sta.setRank(1);
	     sta.setName("zzzzzzzz");
	     ser.setStaff(sta);
	     ser.execute();
	    }

}
