package com.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConvert {
	  public static String  convertDateToString(Date date)
	  {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  String strDate = format.format(date);
		  return strDate;
	  }
	
}
