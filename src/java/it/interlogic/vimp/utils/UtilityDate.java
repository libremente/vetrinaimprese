package it.interlogic.vimp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class UtilityDate
{
	private static final SimpleDateFormat formatEnglish = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat formatItalian = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat formatItalianExpanded = new SimpleDateFormat("dd/MM/yyyy - hh.mm");

	public static String formatItalianExpanded(Date value)
	{
		if (value == null)
			return "";
		return formatItalianExpanded.format(value);
	}

	public static Date parseEnglishDate(Object value)
	{
		if (value == null)
			return null;

		try
		{
			return formatEnglish.parse((String) value);
		}
		catch (ParseException e)
		{
		}
		return null;
	}

	public static Date parseItalianDate(Object value)
	{
		if (value == null)
			return null;

		try
		{
			return formatItalian.parse((String) value);
		}
		catch (ParseException e)
		{
		}
		return null;
	}

	// 04/09/2014 - 08/09/2014

	public static String[] formatDateRange(String range)
	{
		try
		{
			if (range != null && range.trim().length() > 0)
			{
				String[] ret = new String[2];
				StringTokenizer st = new StringTokenizer(range, "-");
				int i = 0;
				while (st.hasMoreTokens())
				{
					String d = st.nextToken().trim();

					ret[i++] = formatEnglish.format(formatItalian.parse(d));
				}
				return ret;
			}
		}
		catch (Exception err)
		{
		}

		return null;

	}

}
