package it.interlogic.vimp.utils;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.Converter;

public class XmlCalendarToDateConverter implements Converter
{

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class classe, Object value)
	{
		if (value == null)
			return null;
		if (value instanceof XMLGregorianCalendar) {
			return ((XMLGregorianCalendar)value).toGregorianCalendar().getTime();
		}
		return null;

	}

}