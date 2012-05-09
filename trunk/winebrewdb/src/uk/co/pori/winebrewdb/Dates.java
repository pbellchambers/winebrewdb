package uk.co.pori.winebrewdb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Contains some date converting/calculating functions.
 * 
 * @author paul.bellchambers
 *
 */
public class Dates {
	
	/**
	 * Takes a date and converts it to a string.
	 * 
	 * @param date The date to be converted.
	 * @return Returns the string value of the date in "yyyy/MM/dd" format. 
	 */
	public static String dateToString(Date date) {
		if(date == null){
			return("");
		}else {
			SimpleDateFormat dateYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
	        String stringYYYYMMDD = dateYYYYMMDD.format(date);
	        return(stringYYYYMMDD);
		}
	}

	/**
	 * Takes a string and converts it to a date.
	 * 
	 * @param stringtochange The string to be converted in "yyyy/MM/dd" format. 
	 * @return Returns the date.
	 */
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
	
	/**
	 * Calculates the days between a specified start date and end date.
	 * 
	 * @param startDate The start date.
	 * @param endDate The end date.
	 * @return Returns the string value of number of days between the dates. 
	 */
	public static String daysBetween(Date startDate, Date endDate) {
		DateTime startDateTime = new DateTime(startDate);
		DateTime endDateTime = new DateTime(endDate);
		
		Days d = Days.daysBetween(startDateTime, endDateTime);
		String dstring = d.toString();
		String cleandstring = dstring.replaceAll("P", "").replaceAll("D", "");
			
		return cleandstring;
	}

}