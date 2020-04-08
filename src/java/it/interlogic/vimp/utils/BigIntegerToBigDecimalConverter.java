package it.interlogic.vimp.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.beanutils.Converter;

public class BigIntegerToBigDecimalConverter implements Converter
{

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class classe, Object value)
	{
		if (value == null)
			return null;
		if (value instanceof BigInteger) {
			return new BigDecimal(((BigInteger)value));
		}
		return null;
	}

}