package it.interlogic.vimp.amulti;

import org.hibernate.EmptyInterceptor;

public class MultilanguageInterceptor extends EmptyInterceptor
{
	private static final long serialVersionUID = 1L;

	@Override
	public String onPrepareStatement(String sql)
	{
		return LanguageContext.getLanguageSql(sql);
	}

}