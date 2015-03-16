package uk.co.pbellchambers.winebrewdb;

import java.text.NumberFormat;

/**
 * Some number formatters
 */
public class NumberRenderer extends FormatRenderer
{

	private static final long serialVersionUID = -6668503155258434249L;

	/**
	 * Use the specified number formatter and right align the text.
	 * 
	 * @param formatter The format required
	 */
	public NumberRenderer(NumberFormat formatter)
	{
		super(formatter);
		setHorizontalAlignment(RIGHT);
	}

	/**
	 * Use the default currency formatter for the default locale.
	 */
	public static NumberRenderer getCurrencyRenderer()
	{
		return new NumberRenderer( NumberFormat.getCurrencyInstance() );
	}

	/**
	 * Use the default integer formatter for the default locale.
	 */
	public static NumberRenderer getIntegerRenderer()
	{
		return new NumberRenderer( NumberFormat.getIntegerInstance() );
	}

	/**
	 * Use the default percent formatter for the default locale.
	 */
	public static NumberRenderer getPercentRenderer()
	{
		return new NumberRenderer( NumberFormat.getPercentInstance() );
	}
}
