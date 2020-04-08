package it.interlogic.vimp.amulti;

import it.interlogic.vimp.constants.I18nConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageContext
{

	public final static String LANGUAGE_SESSION_KEY = "LanguageContext.language";
	public final static String LANGUAGE_SESSION_KEY_TEMPORANY = "LanguageContext.language.temporany";

	private final static ThreadLocal<LanguageContext> threadInstance = new ThreadLocal<LanguageContext>()
	{
		@Override
		protected LanguageContext initialValue()
		{
			return new LanguageContext();
		}
	};

	public static LanguageContext getInstance()
	{
		return threadInstance.get();
	}

	public static void remove()
	{
		LanguageContext cf = threadInstance.get();
		cf.clear();
		threadInstance.remove();
	}

	public void clear()
	{
		tables = null;
	}

	private List<String> tables;
	private String language;
	private Pattern pattern;
	private boolean ready = false;

	/**
	 * Se le tabelle non vengono impostate la sostituzione modifica comunque il
	 * codice sql. Utilizzare l'attributo ready per verifica se questo passaggio
	 * è stato eseguito correttamente. Se le tabelle non vengono impostate il
	 * codice sql non viene modificato.
	 * */
	public void setLanguageTables(List<String> ts)
	{
		if (ts != null && !ts.isEmpty())
		{
			tables = ts;
			ready = true;
		}
		compilePatterns();
	}

	public static String getLanguage()
	{
		return getInstance().language;
	}

	public static Pattern getPattern()
	{
		return getInstance().pattern;
	}

	/**
	 * Imposta il linguaggio per le logiche di internazionalizzazione interne.
	 * Se il codice è {@link I18nConstants#DEFAULT_LANGUAGE_CODE} le logiche di
	 * traduzione sono impostate alla modalità default.
	 * */
	public void setLanguage(String lan)
	{
		if (lan != null)
		{
			if (I18nConstants.DEFAULT_LANGUAGE_CODE.equals(lan) || I18nConstants.DEFAULT_LANGUAGE_CODE_SMALL.equals(lan.toUpperCase()))
				language = null;
			else
				language = lan;
		}
		else
			language = null;

		if (language != null)
			language = language.toUpperCase();
	}

	public void setDefaultLanguage()
	{
		setLanguage(null);
	}

	public boolean isReady()
	{
		return ready;
	}

	private void compilePatterns()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("(?<=\\W)(");
		if (tables != null)
		{
			boolean first = true;
			for (String table : tables)
			{
				if (first)
					first = false;
				else
					sb.append("|");
				sb.append(table);
			}
		}
		sb.append(")(?=\\W)");
		pattern = Pattern.compile(sb.toString());
	}

	public static String getLanguageSql(String sql)
	{
		if (LanguageContext.getInstance().isReady() && LanguageContext.getLanguage() != null)
		{
			final StringBuffer stringBuffer = new StringBuffer();
			final Matcher matcher = getPattern().matcher(sql);
			while (matcher.find())
				matcher.appendReplacement(stringBuffer, matcher.group(1) + "_" + getLanguage());
			matcher.appendTail(stringBuffer);
			sql = stringBuffer.toString();
		}
		return sql;
	}

}
