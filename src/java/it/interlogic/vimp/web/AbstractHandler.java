package it.interlogic.vimp.web;

import it.interlogic.vimp.data.jpa.model.TagEntityInterface;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.interceptors.ConfigurazioneAmbienteInterceptor;

import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;

public abstract class AbstractHandler
{
	
	/*
	@Autowired
	ConfigurazioneAmbiente configurazioneAmbiente;

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Model model, Exception ex)
	{
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		ex.printStackTrace();

		ModelAndView modelAndView = new ModelAndView();
		String locale = LocaleContextHolder.getLocale().toString();
		if (locale.equalsIgnoreCase(I18nConstants.DEFAULT_LANGUAGE_CODE_SMALL))
			locale = I18nConstants.DEFAULT_LANGUAGE_CODE;
		
		modelAndView.getModelMap().addAttribute(ConfigurazioneAmbienteInterceptor.APP_BASE_CONTEXT, configurazioneAmbiente.getApplicazioneBaseContext())
				.addAttribute(ConfigurazioneAmbienteInterceptor.URL_RISORSE_STATICHE, configurazioneAmbiente.getUrlRisorseStatiche())
				.addAttribute(ConfigurazioneAmbienteInterceptor.TITOLO_APPLICAZIONE, configurazioneAmbiente.getTitoloApplicazione())
				.addAttribute(ConfigurazioneAmbienteInterceptor.TITOLO_APPLICAZIONE_SMALL, configurazioneAmbiente.getTitoloApplicazioneSmall()).addAttribute("versione", configurazioneAmbiente.getVersione())
				.addAttribute(ConfigurazioneAmbienteInterceptor.MAPS_API_KEY, configurazioneAmbiente.getMapsApiKey()).addAttribute("buildDate", configurazioneAmbiente.getBuildDate())
				.addAttribute(ConfigurazioneAmbienteInterceptor.SPID_LOGOUT_URL, configurazioneAmbiente.getSpidLogoutUrl()).addAttribute(ConfigurazioneAmbienteInterceptor.LOCALES_OPTIONS, configurazioneAmbiente.getSelectableLanguages())
				.addAttribute(ConfigurazioneAmbienteInterceptor.LOCALE, locale);

		
		
		modelAndView.setViewName("errorPage");
		
		return modelAndView;
	}
	*/

	@Autowired
	private MessageSource messageSource;

	protected final Logger logger = LoggerUtility.getLogger(this.getClass());

	public abstract String getPageName();

	protected void modelTags(TagEntityInterface tagEntity, Model model)
	{
		if (tagEntity != null && model != null)
		{
			String tag = tagEntity.getDescTag();
			if (tag != null && tag.trim().length() > 0)
			{
				model.addAttribute(ConfigurazioneAmbienteInterceptor.META_KEYWORD, tag);
				model.addAttribute(ConfigurazioneAmbienteInterceptor.META_ROBOTS, tag);
				model.addAttribute(ConfigurazioneAmbienteInterceptor.META_GOOGLEBOT, tag);
			}
		}
	}

	public String getMessage(String key)
	{
		return getMessage(key, null);
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

	public static String getJsonErrorMsg(String theErrorMessage)
	{
		return getJsonMsg(theErrorMessage, false);
	}

	public static String getJsonSuccessMsg(String msg)
	{
		return getJsonMsg(msg, true);
	}

	public static String getJsonMsg(String msg, boolean success)
	{
		final ObjectNode builder = JsonNodeFactory.instance.objectNode();
		builder.put("success", success);
		builder.put("msg", msg);
		return toJsonString(builder);
	}

	public static String toJsonString(JsonNode model)
	{
		final StringWriter stWriter = new StringWriter();
		try
		{
			getMapper().writeValue(stWriter, model);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return stWriter.toString();
	}

	protected JsonNode parseJsonObject(String jsonString)
	{
		JsonNode node;
		try
		{
			node = getMapper().readTree(jsonString);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return node;
	}

	protected RuntimeException manageThrowable(Throwable t)
	{
		return new RuntimeException(t);
	}

	static ObjectMapper mapper = null;

	protected static ObjectMapper getMapper()
	{
		if (mapper == null)
		{

			mapper = new ObjectMapper();
		}
		return mapper;
	}
}
