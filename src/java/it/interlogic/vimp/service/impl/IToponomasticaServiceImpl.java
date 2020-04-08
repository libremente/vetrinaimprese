package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.service.IToponomasticaService;
import it.interlogic.vimp.service.ws.toponomastica.Civico;
import it.interlogic.vimp.service.ws.toponomastica.Strada;
import it.interlogic.vimp.service.ws.toponomastica.TopoResponse;
import it.interlogic.vimp.web.security.AmbienteContext;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("iToponomasticaService")
public class IToponomasticaServiceImpl implements IToponomasticaService
{
	private final static String METHOD_GET_STRADA = "rstGetStradaDescr";
	private final static String METHOD_GET_CIVICI_TOPO = "rstGetCiviciDBTopo";
	private final static String METHOD_GET_CIVICI = "rstGetCivici";
	private final static String PARAM_RESPONSE_TYPE = "&FORMATO_RISPOSTA=json";

	@Override
	public List<Strada> getStrade(String via)
	{
		List<Strada> ret = null;
		try
		{
			String url = AmbienteContext.getToponomasticaUrl() + METHOD_GET_STRADA + "?NOME_VIA=" + URLEncoder.encode(via, "UTF-8") + PARAM_RESPONSE_TYPE;
			HttpURLConnection conn = getUrlConnection(AmbienteContext.getToponomasticaAuthorization(), url);

			if (conn.getResponseCode() == 200)
			{
				TopoResponse res = getGson().fromJson(new InputStreamReader(conn.getInputStream()), TopoResponse.class);
				if (res != null && res.getRisposta() != null)
					ret = res.getRisposta().getStrade();
				conn.disconnect();
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();

		}

		return ret;
	}

	@Override
	public List<Civico> getCivici(String codiceStrada)
	{
		List<Civico> ret = null;
		try
		{
			String url = AmbienteContext.getToponomasticaUrl() + METHOD_GET_CIVICI + "?CODICE_STRADA=" + URLEncoder.encode(codiceStrada, "UTF-8") + PARAM_RESPONSE_TYPE;
			HttpURLConnection conn = getUrlConnection(AmbienteContext.getToponomasticaAuthorization(), url);

			if (conn.getResponseCode() == 200)
			{

				TopoResponse res = getGson().fromJson(new InputStreamReader(conn.getInputStream()), TopoResponse.class);
				if (res != null && res.getRisposta() != null)
					ret = res.getRisposta().getCivici();

				conn.disconnect();
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();

		}
		return ret;
	}

	@Override
	public Civico getCivico(String codiceStrada, String numero, String lettera, String colore)
	{
		Civico ret = null;
		try
		{
			String url = AmbienteContext.getToponomasticaUrl() + METHOD_GET_CIVICI_TOPO + "?" + "CODICE_STRADA=" + URLEncoder.encode(codiceStrada, "UTF-8") + "&NUMERO_CIVICO="
					+ URLEncoder.encode(numero, "UTF-8") + "&LETTERA_CIVICO=" + URLEncoder.encode(lettera, "UTF-8") + "&COLORE_CIVICO=" + URLEncoder.encode(colore, "UTF-8")
					+ PARAM_RESPONSE_TYPE;

			HttpURLConnection conn = getUrlConnection(AmbienteContext.getToponomasticaAuthorization(), url);

			if (conn.getResponseCode() == 200)
			{

				TopoResponse res = getGson().fromJson(new InputStreamReader(conn.getInputStream()), TopoResponse.class);
				if (res != null && res.getRisposta() != null)
				{
					List<Civico> civici = res.getRisposta().getCivici();
					if (civici != null && civici.size() > 0)
						ret = civici.get(0);
				}

				conn.disconnect();
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();

		}
		return ret;
	}
	
	@Override
	public Civico getCivico(String query)
	{
		Civico ret = null;
		try
		{
			String url = AmbienteContext.getToponomasticaUrl() + METHOD_GET_CIVICI_TOPO + "?" + query + PARAM_RESPONSE_TYPE;

			HttpURLConnection conn = getUrlConnection(AmbienteContext.getToponomasticaAuthorization(), url);

			if (conn.getResponseCode() == 200)
			{

				TopoResponse res = getGson().fromJson(new InputStreamReader(conn.getInputStream()), TopoResponse.class);
				if (res != null && res.getRisposta() != null)
				{
					List<Civico> civici = res.getRisposta().getCivici();
					if (civici != null && civici.size() > 0)
						ret = civici.get(0);
				}

				conn.disconnect();
			}
		}
		catch (Exception e)
		{

			e.printStackTrace();

		}
		return ret;
	}

	private Gson getGson()
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeSpecialFloatingPointValues();
		return gsonBuilder.setPrettyPrinting().create();
	}

	private HttpURLConnection getUrlConnection(String authorization, String urlString) throws Exception
	{
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", authorization);
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		return conn;
	}

}
