package it.interlogic.vimp.web.interceptors;

import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.IUtenteService;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.filter.CompressResponseFilter;
import it.interlogic.vimp.web.security.UtenteContext;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

@SuppressWarnings("rawtypes")
public class CheckUserInterceptor extends AbstractHandlerInterceptor
{
	@Autowired
	protected IUtenteService utenteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String uri = request.getRequestURI();

		// Controllo le url valide senza una sessione valida
		if (uri != null)
		{
			if (uri.contains("/vimp/assets/"))
				return true;
			if (uri.contains("/vimp/bootstrap/"))
				return true;
			if (uri.startsWith("/vimp/contact"))
				return true;
			if (uri.startsWith("/vimp/faq"))
				return true;
			if (uri.startsWith("/vimp/termini"))
				return true;
			if (uri.startsWith("/vimp/cookies"))
				return true;
		}

		logger.debug("URI= " + uri);
		logger.debug("Interceptor: CheckUserInterceptor");

		UtenteDto uc = (UtenteDto) WebUtils.getSessionAttribute(request, UtenteContext.USER_SESSION_KEY);

		
		
		
		String useSpid = utenteService.getParameter("SPID");
		
		// DEBUG
		//printHeader(request,useSpid);

		
		// TODO SPID
		if ("TRUE".equals(useSpid))
		{
			// CON SPID
			// ==========
			uc = loadUtente(uc, request);
			WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, uc);
			LoggerUtility.error("URI= " + uri);
			if (!uri.startsWith("/vimp/secure/"))
			{
				request.getRequestDispatcher("/secure/" + uri.substring("/vimp".length())).forward(request, response);
				return false;
			}
		}
		else
		{
			// SENZA SPID
			// ==========
			if (uc == null)
			{
				uc = new UtenteDto();
				uc.setRuolo(UtenteDto.RUOLO_VISITOR);
			}
			else
			{
				LoggerUtility.error("URI= " + uri);
				if (!uri.startsWith("/vimp/secure/"))
				{
					request.getRequestDispatcher("/secure/" + uri.substring("/vimp".length())).forward(request, response);
					return false;
				}
			}
		}

		UtenteContext.getInstance().setCurrentUser(uc);
		auditOperazioneUtente(uc, request);
		// auditOperazioneUtenteDB(uc, request);

		try
		{
			// TODO COMPRESSIONE
			Object obj = request.getSession().getAttribute(CompressResponseFilter.RESPONSE_COMPRESS);
			if (obj == null)
				WebUtils.setSessionAttribute(request, CompressResponseFilter.RESPONSE_COMPRESS, true);
		}
		catch (Exception err)
		{
		}

		return true;
	}

	protected final Logger loggerAudit = Logger.getLogger("vimp.audit");

	/**
	 * Metodo che si occupa di tracciare l'operazione dell'utente
	 * 
	 * @param uc
	 * @param request
	 */
	private void auditOperazioneUtente(UtenteDto uc, HttpServletRequest request)
	{
		try
		{
			String mail = null;
			if (uc != null)
				mail = uc.getEmail();

			String uri = request.getRequestURI();
			if (uri.endsWith(".json"))
				return;
			if (uri.startsWith("/vimp/local_resources/"))
				return;

			String ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null)
				ip = request.getRemoteAddr();

			if (mail != null && mail.trim().length() > 0)
				loggerAudit.info(mail + ";" + ip + ";" + uri);
			else
				loggerAudit.info("Visitatore" + ";" + ip + ";" + uri);
		}
		catch (Exception e)
		{
			logger.warn("Errore in audit", e);
		}

	}

	@SuppressWarnings("unused")
	private void auditOperazioneUtenteDB(UtenteDto uc, HttpServletRequest request)
	{
		try
		{
			if (uc != null && uc.getIdUtente() != null && uc.getIdUtente().intValue() > 0)
			{
				String uri = request.getRequestURI();
				if (uri.endsWith(".json"))
					return;
				if (uri.startsWith("/vimp/local_resources/"))
					return;

				PLFTLogAuditEntity log = new PLFTLogAuditEntity();
				String ip = request.getHeader("X-FORWARDED-FOR");
				if (ip == null)
					ip = request.getRemoteAddr();

				log.setIpAddress(ip);
				log.setIdUtente(uc.getIdUtente());
				log.setDataOra(new Date());
				log.setOperazione(uri);

				utenteService.lodAudit(log);
			}
		}
		catch (Exception e)
		{
			logger.warn("Errore in audit db", e);
		}

	}

	// TODO SPID
	@SuppressWarnings("unused")
	private void printHeader(HttpServletRequest request,String useSpid)
	{		
		System.out.println("==========================================");
		System.out.println("HTTP HEADER PARAMETERS");
		if ("TRUE".equals(useSpid))
			System.out.println("CONFIGURATO CON SPID");
		else
			System.out.println("CONFIGURATO CON LOGIN DI TEST");

		Map<String, String> map = new HashMap<String, String>();
		
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		System.out.println(map);
		System.out.println("==========================================");
	}

	private UtenteDto loadUtente(UtenteDto utente, HttpServletRequest request)
	{
		String codiceFiscale = request.getHeader("comge_codicefiscale");

		System.out.println("HEADER comge_codicefiscale:" + codiceFiscale);

		if (codiceFiscale == null || codiceFiscale.trim().length() <= 0)
		{
			if (utente != null && utente.getCodiceFiscale() != null && utente.getCodiceFiscale().trim().length() > 0)
			{
				//System.out.println("1- Utente:" + utente.toString());
				return utente;
			}

			utente = new UtenteDto();
			utente.setRuolo(UtenteDto.RUOLO_VISITOR);
			//System.out.println("2- Utente:" + utente.toString());
			return utente;
		}

		if (utente != null && codiceFiscale.equalsIgnoreCase(utente.getCodiceFiscale()))
		{
			//System.out.println("3- Utente:" + utente.toString());
			return utente;
		}

		utente = new UtenteDto();
		utente.setCodiceFiscale(codiceFiscale);
		utente.setNome(request.getHeader("comge_nome"));
		utente.setCognome(request.getHeader("comge_cognome"));
		utente.setEmail(request.getHeader("comge_emailAddress"));

		PLFTUtenteEntity db = utenteService.findUtenteByCodiceFiscale(codiceFiscale);

		if (db != null && db.getIdUtente() != null && db.getIdUtente().intValue() > 0)
		{
			utente.setIdUtente(db.getIdUtente());
			utente.setRuolo(db.getRuolo().getIdRuolo().intValue());
			utente.setPlfImpresas(db.getPlfImpresas());
		}
		else
		{
			utente.setRuolo(UtenteDto.RUOLO_VISITOR_LOGIN);
		}

		//System.out.println("4- Utente:" + utente.toString());
		return utente;
	}

}
