
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
	    	 	StaffService ser =  (StaffService)ctx.getBean("staffService");	    	 
	    	 	  List<Staff> stafflist= ser.findByStaid("170010001"); 
	    	 	 System.out.println("service"+stafflist.get(0).getStaid());
	    }
	 @Test  
	 /*
	  * ≤‚ ‘action
	  */
	    public void testUserAction() throws Exception{  
		
		 ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		 editPassword ser = (editPassword)ctx.getBean("editPassword"); 
        ser.setId("170010001");
        ser.setPassword("123");
	    ser.execute();
	    }

}
