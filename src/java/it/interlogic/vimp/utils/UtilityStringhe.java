package it.interlogic.vimp.utils;

import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

public class UtilityStringhe 
{
	
	public static Map<String,Object> escapeHtmlValues(Map<String,Object> object)
	{
		if (object==null)
			return null;
		for (String key : object.keySet()) 
		{
			Object value = object.get(key);
			if (value instanceof String) {
				String stringValue = (String) value;
				object.put(key, StringEscapeUtils.escapeHtml(stringValue));
			}
		}
		return object;
	}
}
