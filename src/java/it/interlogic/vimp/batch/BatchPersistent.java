package it.interlogic.vimp.batch;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.service.IArisService;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IImpresaService;
import it.interlogic.vimp.service.ILogService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.service.IUtenteService;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

public class BatchPersistent
{

	private String wsArisUiSearchUrl;
	private String wsArisRlMultiSearchUrl;
	private String wsArisAuthorization;

	private String addressMailFrom;
	private String addressMailTo;
	private String mailHost;
	private String mailPort;

	private AnnotationConfigApplicationContext context;

	private IImpresaService impresaService;
	private IDecodificheService decodificaService;
	private IArisService arisService;
	private ILogService logService;
	private IUtenteService utenteService;
	private IServiziService serviziService;

	public BatchPersistent()
	{
		context = new AnnotationConfigApplicationContext(AppConfig.class);

		impresaService = context.getBean(IImpresaService.class);
		decodificaService = context.getBean(IDecodificheService.class);
		arisService = context.getBean(IArisService.class);
		logService = context.getBean(ILogService.class);
		utenteService = context.getBean(IUtenteService.class);	
		serviziService = context.getBean(IServiziService.class);

		loadProperties();
	}

	public void closeContext()
	{
		context.close();
	}

	public IImpresaService getImpresaService()
	{
		return impresaService;
	}

	public IDecodificheService getDecodificaService()
	{
		return decodificaService;
	}

	public ILogService getLogService()
	{
		return logService;
	}

	public IUtenteService getUtenteService()
	{
		return utenteService;
	}

	public IServiziService getServiziService()
	{
		return serviziService;
	}


	public it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(String codiceFiscalePartitaIva)
	{
		return arisService.getMultiRL(wsArisRlMultiSearchUrl, wsArisAuthorization, codiceFiscalePartitaIva);
	}

	public it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult getUL(String codiceFiscalePartitaIva)
	{
		return arisService.getUL(wsArisUiSearchUrl, wsArisAuthorization, codiceFiscalePartitaIva);
	}

	public String getAddressMailFrom()
	{
		return addressMailFrom;
	}

	public void setAddressMailFrom(String addressMailFrom)
	{
		this.addressMailFrom = addressMailFrom;
	}

	public String getAddressMailTo()
	{
		return addressMailTo;
	}

	public void setAddressMailTo(String addressMailTo)
	{
		this.addressMailTo = addressMailTo;
	}

	public String getMailHost()
	{
		return mailHost;
	}

	public void setMailHost(String mailHost)
	{
		this.mailHost = mailHost;
	}

	public String getMailPort()
	{
		return mailPort;
	}

	public void setMailPort(String mailPort)
	{
		this.mailPort = mailPort;
	}

	private void loadProperties()
	{
		try
		{
			Properties prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconnection.properties"));
			wsArisUiSearchUrl = prop.getProperty("wsArisUiSearchUrl");
			wsArisRlMultiSearchUrl = prop.getProperty("wsArisRlMultiSearchUrl");
			wsArisAuthorization = prop.getProperty("wsArisAuthorization");

			addressMailFrom = prop.getProperty("addressMailFrom");
			addressMailTo = prop.getProperty("addressMailTo");
			mailHost = prop.getProperty("mailHost");
			mailPort = prop.getProperty("mailPort");

			LanguageContext.getInstance().setLanguageTables(Arrays.asList(I18nConstants.MULTILANGUAGE_TABLES));
			LanguageContext.getInstance().setLanguage(LocaleContextHolder.getLocale().toString());

		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
	}

}