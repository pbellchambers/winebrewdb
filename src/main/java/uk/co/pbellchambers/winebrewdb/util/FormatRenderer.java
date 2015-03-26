package uk.co.pbellchambers.winebrewdb.util;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;
import java.text.Format;

/**
 * Use a formatter to format the cell Object in a table.
 */
public class FormatRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 8120820380110079664L;
	private Format formatter;

	/**
	 * Use the specified formatter to format the Object.
	 *   
	 * @param formatter The format required.
	 */
	public FormatRenderer(Format formatter){
		this.formatter = formatter;
	}

	/**
	 * Formats the specified object to the required value.
	 * 
	 * @param value The current value.
	 */
	public void setValue(Object value){
		try
		{
			if (value != null)
				value = formatter.format(value);
		}catch(IllegalArgumentException e){
			
		}

		super.setValue(value);
	}

	/**
	 * Use the default date/time formatter for the default locale.
	 */
	public static FormatRenderer getDateTimeRenderer(){
		return new FormatRenderer(DateFormat.getDateTimeInstance());
	}

	/**
	 * Use the default time formatter for the default locale.
	 */
	public static FormatRenderer getTimeRenderer(){
		return new FormatRenderer(DateFormat.getTimeInstance());
	}
}
