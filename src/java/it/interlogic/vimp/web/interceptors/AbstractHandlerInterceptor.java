package it.interlogic.vimp.web.interceptors;

import it.interlogic.vimp.utils.LoggerUtility;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor generico
 * @author 1516
 *
 */
public abstract class AbstractHandlerInterceptor extends HandlerInterceptorAdapter
{
    
	@Autowired
	private MessageSource messageSource;
	
	protected final Logger logger = LoggerUtility.getLogger(this.getClass());

	public String getMessage(String key)
	{
		return getMessage(key, null);
	}
	
	public static String getCurrentAppContext(HttpServletRequest request)
	{
		String uri = request.getRequestURI();
		String appContext=StringUtils.substringBetween(uri, "/");
		return appContext;
		
	}

	public String getMessage(String key, Object[] args)
	{
		try
		{
			return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		return "nessun messaggio con chiave:" + key;
	}
	
}
