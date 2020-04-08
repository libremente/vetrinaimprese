package it.interlogic.vimp.utils;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.Converter;

public class DateToXmlCalendarConverter implements Converter
{

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class classe, Object value)
	{
		if (value == null)
			return null;
		XMLGregorianCalendar calendario = null;
		if (value instanceof Date || value instanceof java.sql.Date) {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime((Date)value);
			try
			{
				calendario = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			}
			catch (DatatypeConfigurationException e)
			{
				LoggerUtility.error("Conversione xml", e);
			}
		}
		return calendario;
	}

}