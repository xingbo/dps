package com.dps.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final SimpleDateFormat simple_date_format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static String getCurrentDate(){
		return simple_date_format.format(new Date());
	}
	
	public static void main(String args[]){
		System.out.print(DateUtil.getCurrentDate());
	}
}
