package com.pori.WineBrewDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Dates {

	public static String dateToString(Date date) {
		SimpleDateFormat dateYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
        String stringYYYYMMDD = dateYYYYMMDD.format(date);
        return(stringYYYYMMDD);
	}

	public static Date stringToDate(String stringtochange){ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date convertedDate = null;
		try {
			convertedDate = dateFormat.parse(stringtochange);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return(convertedDate);		
	}
	
	
	public static String daysBetween(Date startDate, Date endDate) {
		DateTime startDateTime = new DateTime(startDate);
		DateTime endDateTime = new DateTime(endDate);
		
		Days d = Days.daysBetween(startDateTime, endDateTime);
		String dstring = d.toString();
		String cleandstring = dstring.replaceAll("P", "").replaceAll("D", "");
			
		return cleandstring;
	}

}