package it.interlogic.vimp.web.rest;

import it.interlogic.vimp.service.IToponomasticaService;
import it.interlogic.vimp.service.ws.toponomastica.Civico;
import it.interlogic.vimp.service.ws.toponomastica.Strada;
import it.interlogic.vimp.web.AbstractHandler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ToponomasticaHandler extends AbstractHandler
{
	@Autowired
	IToponomasticaService toponomasticaService;

	@Override
	public String getPageName()
	{
		return "toponomasticaHandler";
	}

	@RequestMapping(value = "/elencoStrade.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<Strada> getElencoStrade(HttpSession session, @RequestParam(value = "nome", required = true) String nomeVia)
	{
		try
		{
			return toponomasticaService.getStrade(nomeVia);

		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/elencoStrade.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<Strada> getElencoStradeSecure(HttpSession session, @RequestParam(value = "nome", required = true) String nomeVia)
	{
		return getElencoStrade(session, nomeVia);
	}

	@RequestMapping(value = "/elencoCivici.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<Civico> getElencoCivici(HttpSession session, @RequestParam(value = "codice", required = true) String codiceStrada)
	{
		try
		{
			return toponomasticaService.getCivici(codiceStrada);

		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/elencoCivici.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<Civico> getElencoCiviciSecure(HttpSession session, @RequestParam(value = "codice", required = true) String codiceStrada)
	{
		return getElencoCivici(session, codiceStrada);
	}

	@RequestMapping(value = "/civico.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Civico getCivico(HttpSession session, @RequestParam(value = "codice", required = true) String codiceStrada,
			@RequestParam(value = "numero", required = true) String numero, @RequestParam(value = "lettera", required = true) String lettera,
			@RequestParam(value = "colore", required = true) String colore)
	{
		try
		{
			return toponomasticaService.getCivico(codiceStrada, numero, lettera, colore);

		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/civico.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Civico getCivicoSecure(HttpSession session, @RequestParam(value = "codice", required = true) String codiceStrada,
			@RequestParam(value = "numero", required = true) String numero, @RequestParam(value = "lettera", required = true) String lettera,
			@RequestParam(value = "colore", required = true) String colore)
	{
		return getCivico(session, codiceStrada, numero, lettera, colore);
	}
	
	
	@RequestMapping(value = "/civicoQuery.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Civico getCivicoQuery(HttpSession session, @RequestParam(value = "query", required = true) String query)
	{
		try
		{
			return toponomasticaService.getCivico(query);

		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/civicoQuery.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public Civico getCivicoQuerySecure(HttpSession session, @RequestParam(value = "query", required = true) String query)
	{
		return getCivicoQuery(session,query);
	}
	
}
