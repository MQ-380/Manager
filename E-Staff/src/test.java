
import java.util.ArrayList;
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
    	 editDepartment ser =  (editDepartment)ctx.getBean("editDepartment"); 
	    	   	
	    	ser.setId("005");
	    	ser.setName("aaaaaaaaaaa");
	    	 ser.execute();
	    }

}
