package it.interlogic.vimp.web.security;

public class AmbienteContext
{
	public final static String USER_SESSION_KEY = "UtenteContext.currentUser";

	private final static ThreadLocal<AmbienteContext> threadInstance = new ThreadLocal<AmbienteContext>()
	{
		@Override
		protected AmbienteContext initialValue()
		{
			return new AmbienteContext();
		}
	};

	public static AmbienteContext getInstance()
	{
		return threadInstance.get();
	}

	public static void remove()
	{
		AmbienteContext cf = threadInstance.get();
		cf.clear();
		threadInstance.remove();
	}

	AmbienteContext()
	{
		super();
	}

	public void clear()
	{
		mailAssistenza = null;
		mailNoReplay = null;
		wsArisUiSearchUrl = null;
		wsArisUiSearchAllUrl = null;
		wsArisRlMultiSearchUrl = null;
		wsArisAuthorization = null;
		spidLogoutUrl = null;
		toponomasticaAuthorization = null;
		toponomasticaUrl = null;
	}

	String mailAssistenza;
	String mailNoReplay;
	String wsArisUiSearchUrl;
	String wsArisUiSearchAllUrl;
	String wsArisRlMultiSearchUrl;
	String wsArisAuthorization;
	String spidLogoutUrl;
	String toponomasticaAuthorization;
	String toponomasticaUrl;

	public static String getToponomasticaUrl()
	{
		return getInstance().toponomasticaUrl;
	}

	public void setToponomasticaUrl(String _toponomasticaUrl)
	{
		toponomasticaUrl = _toponomasticaUrl;
	}

	public static String getToponomasticaAuthorization()
	{
		return getInstance().toponomasticaAuthorization;
	}

	public void setToponomasticaAuthorization(String _toponomasticaAuthorization)
	{
		toponomasticaAuthorization = _toponomasticaAuthorization;
	}

	public static String getMailAssistenza()
	{
		return getInstance().mailAssistenza;
	}

	public void setMailAssistenza(String _mailAssistenza)
	{
		mailAssistenza = _mailAssistenza;
	}

	public static String getMailNoReplay()
	{
		return getInstance().mailNoReplay;
	}

	public void setMailNoReplay(String _mailNoReplay)
	{
		mailNoReplay = _mailNoReplay;
	}

	public static String getWsArisUiSearchUrl()
	{
		return getInstance().wsArisUiSearchUrl;
	}

	public void setWsArisUiSearchUrl(String _wsArisUiSearchUrl)
	{
		wsArisUiSearchUrl = _wsArisUiSearchUrl;
	}
	
	public static String getWsArisUiSearchAllUrl()
	{
		return getInstance().wsArisUiSearchAllUrl;
	}

	public void setWsArisUiSearchAllUrl(String _wsArisUiSearchAllUrl)
	{
		wsArisUiSearchAllUrl = _wsArisUiSearchAllUrl;
	}

	public static String getWsArisRlMultiSearchUrl()
	{
		return getInstance().wsArisRlMultiSearchUrl;
	}

	public void setWsArisRlMultiSearchUrl(String _wsArisRlMultiSearchUrl)
	{
		wsArisRlMultiSearchUrl = _wsArisRlMultiSearchUrl;
	}

	public static String getWsArisAuthorization()
	{
		return getInstance().wsArisAuthorization;
	}

	public void setWsArisAuthorization(String _wsArisAuthorization)
	{
		wsArisAuthorization = _wsArisAuthorization;
	}

	public static String getSpidLogoutUrl()
	{
		return getInstance().spidLogoutUrl;
	}

	public void setSpidLogoutUrl(String _spidLogoutUrl)
	{
		spidLogoutUrl = _spidLogoutUrl;
	}

}
