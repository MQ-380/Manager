package com.tool;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class timeTool {
	 public static Timestamp DateToDateTime(Date date)
	 {
		 SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
              
	      Timestamp stp=new java.sql.Timestamp(date.getTime()); 
	     
	      return stp.valueOf(convertDateToString2(date));
	 }
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
		    return time.valueOf(convertDateToString1(date)); 
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
		  public static String  convertDateToString1(Date date)
		  {
			  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			  String strDate = format.format(date);
			  return strDate;
		  }
		  public static String  convertDateToString2(Date date)
		  {
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  String strDate = format.format(date);
			  return strDate;
		  }
}
