package it.interlogic.vimp.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("serial")
public class ConfigurazioneAmbiente implements Serializable
{

	@Value("${applicazioneBaseContext}")
	String applicazioneBaseContext;

	@Value("${urlRisorseStatiche}")
	String urlRisorseStatiche;

	@Value("${titoloApplicazione}")
	String titoloApplicazione;

	@Value("${titoloApplicazioneSmall}")
	String titoloApplicazioneSmall;

	@Value("${versione}")
	String versione;

	@Value("${buildDate}")
	String buildDate;

	@Value("${mailAssistenza}")
	String mailAssistenza;

	@Value("${mailNoReplay}")
	String mailNoReplay;

	@Value("${mapsApiKey}")
	String mapsApiKey;

	@Value("${wsArisUiSearchUrl}")
	String wsArisUiSearchUrl;

	@Value("${wsArisRlMultiSearchUrl}")
	String wsArisRlMultiSearchUrl;

	@Value("${wsArisAuthorization}")
	String wsArisAuthorization;

	@Value("${spidLogoutUrl}")
	String spidLogoutUrl;

	@Value("${locales}")
	String[] locales;

	@Value("${toponomasticaAuthorization}")
	String toponomasticaAuthorization;

	@Value("${toponomasticaUrl}")
	String toponomasticaUrl;

	public String getUrlRisorseStatiche()
	{
		return urlRisorseStatiche;
	}

	public void setUrlRisorseStatiche(String urlRisorseStatiche)
	{
		this.urlRisorseStatiche = urlRisorseStatiche;
	}

	public String getTitoloApplicazione()
	{
		return titoloApplicazione;
	}

	public void setTitoloApplicazione(String titoloApplicazione)
	{
		this.titoloApplicazione = titoloApplicazione;
	}

	public String getApplicazioneBaseContext()
	{
		return applicazioneBaseContext;
	}

	public void setApplicazioneBaseContext(String applicazioneBaseContext)
	{
		this.applicazioneBaseContext = applicazioneBaseContext;
	}

	public String getTitoloApplicazioneSmall()
	{
		return titoloApplicazioneSmall;
	}

	public void setTitoloApplicazioneSmall(String titoloApplicazioneSmall)
	{
		this.titoloApplicazioneSmall = titoloApplicazioneSmall;
	}

	public String getVersione()
	{
		return versione;
	}

	public void setVersione(String versione)
	{
		this.versione = versione;
	}

	public String getBuildDate()
	{
		return buildDate;
	}

	public void setBuildDate(String buildDate)
	{
		this.buildDate = buildDate;
	}

	public String getMailAssistenza()
	{
		return mailAssistenza;
	}

	public void setMailAssistenza(String mailAssistenza)
	{
		this.mailAssistenza = mailAssistenza;
	}

	public String getMailNoReplay()
	{
		return mailNoReplay;
	}

	public void setMailNoReplay(String mailNoReplay)
	{
		this.mailNoReplay = mailNoReplay;
	}

	public String getMapsApiKey()
	{
		return mapsApiKey;
	}

	public void setMapsApiKey(String mapsApiKey)
	{
		this.mapsApiKey = mapsApiKey;
	}

	public String getWsArisAuthorization()
	{
		return wsArisAuthorization;
	}

	public void setWsArisAuthorization(String wsArisAuthorization)
	{
		this.wsArisAuthorization = wsArisAuthorization;
	}

	public String getWsArisUiSearchUrl()
	{
		return wsArisUiSearchUrl;
	}

	public void setWsArisUiSearchUrl(String wsArisUiSearchUrl)
	{
		this.wsArisUiSearchUrl = wsArisUiSearchUrl;
	}

	public String getWsArisRlMultiSearchUrl()
	{
		return wsArisRlMultiSearchUrl;
	}

	public void setWsArisRlMultiSearchUrl(String wsArisRlMultiSearchUrl)
	{
		this.wsArisRlMultiSearchUrl = wsArisRlMultiSearchUrl;
	}

	public String getSpidLogoutUrl()
	{
		return spidLogoutUrl;
	}

	public void setSpidLogoutUrl(String spidLogoutUrl)
	{
		this.spidLogoutUrl = spidLogoutUrl;
	}

	public String getToponomasticaAuthorization()
	{
		return toponomasticaAuthorization;
	}

	public void setToponomasticaAuthorization(String toponomasticaAuthorization)
	{
		this.toponomasticaAuthorization = toponomasticaAuthorization;
	}

	public String getToponomasticaUrl()
	{
		return toponomasticaUrl;
	}

	public void setToponomasticaUrl(String toponomasticaUrl)
	{
		this.toponomasticaUrl = toponomasticaUrl;
	}

	public Map<String, String> getSelectableLanguages()
	{

		Map<String, String> localesMap = new HashMap<String, String>();
		String assetsUrl = this.urlRisorseStatiche + "/vimp/assets/img/flags/";

		for (String loc : this.locales)
		{
			localesMap.put(loc, assetsUrl + loc + ".png");
		}

		return localesMap;
	}

}
