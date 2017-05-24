package com.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConvert {
	  public static String  convertDateToString(Date date)
	  {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  String strDate = format.format(date);
		  return strDate;
	  }
	 
	  public static Date convertStringToDate(String str) throws ParseException
	  {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		  Date date = sdf.parse(str); 
		  return date;
	  }
}
