package it.interlogic.vimp.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class UtilityNumeri
{

	public static BigDecimal parseNumeri(Object value) throws Exception
	{
		String stringValue = (String) value;
		if (StringUtils.isEmpty(stringValue))
			return null;
		// Provo a parsificarlo come Long
		try
		{
			long longValue = Long.parseLong(stringValue);
			return BigDecimal.valueOf(longValue);
		}
		catch (Exception e)
		{
			// provo a parsificarlo come decimale
			try
			{
				double doubleValue = Double.parseDouble(stringValue);
				return BigDecimal.valueOf(doubleValue);
			}
			catch (Exception e1)
			{
				
				try
				{
					stringValue = StringUtils.replace(stringValue, ".", "");
					stringValue = StringUtils.replace(stringValue, ",", ".");
					double doubleValue = Double.parseDouble(stringValue);
					return BigDecimal.valueOf(doubleValue);
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
					return null;
				}
			}
		}
	}

}
