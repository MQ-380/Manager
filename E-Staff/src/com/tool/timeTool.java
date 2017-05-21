package com.tool;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeTool {
	 public static Time GetNowTime(){   
		 Date date=new Date();   
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");   
		    try {
				date= sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    Time time=new Time(date.getTime());
		  //  System.out.println("***********"+ time.valueOf(convertDateToString(date))+"*******");
		    return time.valueOf(convertDateToString(date)); 
		} 
		public  static Date GetNowDate(){   
			 Date date=new Date();   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		    try {
				date= sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    return date;   
		}
		  public static String  convertDateToString(Date date)
		  {
			  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			  String strDate = format.format(date);
			  return strDate;
		  }
}
