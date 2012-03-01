package com.pori.WineBrewDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dates {
	public static Date convertedDate;

	public static String dateToString(Date date) {
		SimpleDateFormat dateYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
        String stringYYYYMMDD = dateYYYYMMDD.format(date);
        return(stringYYYYMMDD);
	}

	public static Date stringToDate(String stringtochange){ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			convertedDate = dateFormat.parse(stringtochange);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return(convertedDate);		
	}
	
	
	//assert: startDate must be before endDate
	public static String daysBetween(Date startDate, Date endDate) {
		System.out.println("Start: " + startDate);
		System.out.println("End: " + endDate);
	
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(startDate);
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarStartDate.setTime(endDate);
		
		System.out.println("calendarStart: " + calendarStartDate);
		System.out.println("calendarEnd: " + calendarEndDate);
		
		long daysBetween = 0;
		while (calendarStartDate.before(calendarEndDate)) {
			calendarStartDate.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
	  return String.valueOf(daysBetween);
	}

}