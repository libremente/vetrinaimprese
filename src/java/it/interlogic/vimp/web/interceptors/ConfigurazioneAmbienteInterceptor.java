package it.interlogic.vimp.web.interceptors;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.ConfigurazioneAmbiente;
import it.interlogic.vimp.web.Utils;
import it.interlogic.vimp.web.security.AmbienteContext;
import it.interlogic.vimp.web.security.UtenteContext;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class ConfigurazioneAmbienteInterceptor extends AbstractHandlerInterceptor
{

	/**
	 * The name under which the version is added to the model map.
	 */
	public static final String URL_RISORSE_STATICHE = "evn_urlRisorseStatiche";

	public static final String APP_BASE_CONTEXT = "evn_applicazioneBaseContext";

	public static final String TITOLO_APPLICAZIONE = "titoloApplicazione";
	public static final String TITOLO_APPLICAZIONE_SMALL = "titoloApplicazioneSmall";

	public static final String UTENTE = "utente";
	public static final String CURRENT_CALL = "currentCall";

	public static final String UTILS = "utils";

	public static final String META_KEYWORD = "keywordApplicazione";
	public static final String META_ROBOTS = "robotsApplicazione";
	public static final String META_GOOGLEBOT = "googlebotApplicazione";

	public static final String MAIL_ASSISTENZA = "mailAssistenza";
	public static final String MAIL_NOREPLAY = "mailNoReplay";

	public static final String MAPS_API_KEY = "mapsApiKey";

	public static final String WS_ARIS_UI_SEARCH_URL = "wsArisUiSearchUrl";
	public static final String WS_ARIS_RL_MULTI_SEARCH_URL = "wsArisRlMultiSearchUrl";
	public static final String WS_ARIS_AUTHORIZATION = "wsArisAuthorization";

	public static final String TOPONOMASTICA_AUTHORIZATION = "toponomasticaAuthorization";
	public static final String TOPONOMASTICA_URL = "toponomasticaUrl";

	public static final String SPID_LOGOUT_URL = "spidLogoutUrl";

	public static final String LOCALE = "env_locale";
	public static final String LOCALES_OPTIONS = "env_locales_opt";

	private ConfigurazioneAmbiente configurazioneAmbiente;

	public ConfigurazioneAmbienteInterceptor(final ConfigurazioneAmbiente configurazioneAmbiente)
	{
		this.configurazioneAmbiente = configurazioneAmbiente;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		AmbienteContext.getInstance().setMailAssistenza(configurazioneAmbiente.getMailAssistenza());
		AmbienteContext.getInstance().setMailNoReplay(configurazioneAmbiente.getMailNoReplay());

		AmbienteContext.getInstance().setWsArisAuthorization(configurazioneAmbiente.getWsArisAuthorization());
		AmbienteContext.getInstance().setWsArisRlMultiSearchUrl(configurazioneAmbiente.getWsArisRlMultiSearchUrl());
		AmbienteContext.getInstance().setWsArisUiSearchUrl(configurazioneAmbiente.getWsArisUiSearchUrl());
		AmbienteContext.getInstance().setSpidLogoutUrl(configurazioneAmbiente.getSpidLogoutUrl());

		AmbienteContext.getInstance().setToponomasticaAuthorization(configurazioneAmbiente.getToponomasticaAuthorization());
		AmbienteContext.getInstance().setToponomasticaUrl(configurazioneAmbiente.getToponomasticaUrl());

		LanguageContext.getInstance().setLanguageTables(Arrays.asList(I18nConstants.MULTILANGUAGE_TABLES));

		LanguageContext.getInstance().setLanguage(LocaleContextHolder.getLocale().toString());

		return true;
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception
	{

		if (modelAndView != null)
		{
			String locale = LocaleContextHolder.getLocale().toString();
			if (locale.equalsIgnoreCase(I18nConstants.DEFAULT_LANGUAGE_CODE_SMALL))
				locale = I18nConstants.DEFAULT_LANGUAGE_CODE;
			
			modelAndView.getModelMap().addAttribute(APP_BASE_CONTEXT, configurazioneAmbiente.getApplicazioneBaseContext())
					.addAttribute(URL_RISORSE_STATICHE, configurazioneAmbiente.getUrlRisorseStatiche())
					.addAttribute(TITOLO_APPLICAZIONE, configurazioneAmbiente.getTitoloApplicazione())
					.addAttribute(TITOLO_APPLICAZIONE_SMALL, configurazioneAmbiente.getTitoloApplicazioneSmall()).addAttribute("versione", configurazioneAmbiente.getVersione())
					.addAttribute(MAPS_API_KEY, configurazioneAmbiente.getMapsApiKey()).addAttribute("buildDate", configurazioneAmbiente.getBuildDate())
					.addAttribute(SPID_LOGOUT_URL, configurazioneAmbiente.getSpidLogoutUrl()).addAttribute(LOCALES_OPTIONS, configurazioneAmbiente.getSelectableLanguages())
					//.addAttribute(LOCALE, LocaleContextHolder.getLocale());
					.addAttribute(LOCALE, locale);

			try
			{
				String metaKeyword = (String) modelAndView.getModelMap().get(META_KEYWORD);
				if (metaKeyword == null || metaKeyword.trim().length() <= 0)
					modelAndView.getModelMap().addAttribute(META_KEYWORD, getMessage(META_KEYWORD));

				String metaRobots = (String) modelAndView.getModelMap().get(META_ROBOTS);
				if (metaRobots == null || metaRobots.trim().length() <= 0)
					modelAndView.getModelMap().addAttribute(META_ROBOTS, getMessage(META_ROBOTS));

				String metaGooglebot = (String) modelAndView.getModelMap().get(META_GOOGLEBOT);
				if (metaGooglebot == null || metaGooglebot.trim().length() <= 0)
					modelAndView.getModelMap().addAttribute(META_GOOGLEBOT, getMessage(META_GOOGLEBOT));
			}
			catch (Exception err)
			{
				err.printStackTrace();
			}

			UtenteDto utenteCorrente = UtenteContext.getCurrentUser();
			modelAndView.getModelMap().addAttribute(UTENTE, utenteCorrente);

			modelAndView.getModelMap().addAttribute(CURRENT_CALL, request.getRequestURI());

			modelAndView.getModelMap().addAttribute(UTILS, new Utils());

		}
	}
}
