import java.text.ParseException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.task.MyTask;


public class testmain {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    	 	MyTask ser =  (MyTask)ctx.getBean("myTask");	

	     	ser.calSalary();
	}

}
