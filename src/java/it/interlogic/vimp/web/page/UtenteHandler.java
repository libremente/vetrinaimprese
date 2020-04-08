package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFTRuoloEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVUtenteEntity;
import it.interlogic.vimp.domain.AutenticazioneDto;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.exception.AuthenticationException;
import it.interlogic.vimp.service.exception.PasswordExpiredException;
import it.interlogic.vimp.service.exception.PasswordTemporanyException;
import it.interlogic.vimp.service.impl.IUtenteServiceImpl;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.AmbienteContext;
import it.interlogic.vimp.web.security.UserContext;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

@Controller
public class UtenteHandler extends AbstractHandler
{

	@Autowired
	protected IUtenteServiceImpl utenteService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	protected ServletContext context;

	@Autowired
	private JavaMailSender mailSender;

	private static final String VIEW_LOGIN = "mocklogin";

	private static final String VIEW_START_LOGIN = "startLogin";
	private static final String VIEW_MODIFICA_PROFILO = "modificaProfilo";

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "utente";
	}

	/**
	 * @param model
	 * @param currentCall
	 * @return
	 */
	@RequestMapping(value = "/secure/login", method = RequestMethod.POST)
	public String loginSecure(Model model, String currentCall)
	{
		return login(model, currentCall);
	}

	/**
	 * @param model
	 * @param currentCall
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String currentCall)
	{
		logger.debug("UtenteHandler:: login page BEGIN");

		String useSpid = utenteService.getParameter("SPID");
		if ("TRUE".equals(useSpid))
		{
			logger.debug("UtenteHandler:: login page END");
			return "redirect:/secure/home";
		}
		else
		{

			AutenticazioneDto auth = new AutenticazioneDto();
			auth.setCurrentCall(currentCall);

			
			List<PLFVUtenteEntity> lista = new ArrayList<PLFVUtenteEntity>();
			List<PLFTUtenteEntity> listaUtenti = utenteService.findAllUtenti();
			
			if (listaUtenti != null)
			{
				for (PLFTUtenteEntity utente :listaUtenti)
					lista.add(getUtente(utente));
			}
			

			PLFVUtenteEntity utenteVisitatoreLoggoato = getUtenteLoggatoVista();
			lista.add(utenteVisitatoreLoggoato);

			model.addAttribute("lista", lista);
			model.addAttribute("dettaglio", auth);
			logger.debug("UtenteHandler:: login page END");
			return VIEW_LOGIN;
		}
	}
	
	
	
	private PLFVUtenteEntity getUtente(PLFTUtenteEntity utente)
	{
		PLFVUtenteEntity utenteVisitatoreLoggoato = new PLFVUtenteEntity();
		PLFTRuoloEntity re = new PLFTRuoloEntity();
		re.setIdRuolo(utente.getRuolo().getIdRuolo());
		re.setDescRuolo(utente.getRuolo().getDescRuolo());
		utenteVisitatoreLoggoato.setIdUtente(utente.getIdUtente());
		utenteVisitatoreLoggoato.setEmail(utente.getEmail());
		utenteVisitatoreLoggoato.setRuolo(re);
		utenteVisitatoreLoggoato.setNome(utente.getNome());
		utenteVisitatoreLoggoato.setCognome(utente.getCognome());
		utenteVisitatoreLoggoato.setCodFiscale(utente.getCodiceFiscale());
		return utenteVisitatoreLoggoato;
	}

	/**
	 * @return
	 */
	private PLFVUtenteEntity getUtenteLoggatoVista()
	{
		PLFVUtenteEntity utenteVisitatoreLoggoato = new PLFVUtenteEntity();
		PLFTRuoloEntity re = new PLFTRuoloEntity();
		re.setIdRuolo(new BigDecimal(UtenteDto.RUOLO_VISITOR_LOGIN));
		re.setDescRuolo("Utente loggato non accreditato");
		utenteVisitatoreLoggoato.setIdUtente(new BigDecimal(99));
		utenteVisitatoreLoggoato.setEmail("hub2work@comune.genova.it");
		utenteVisitatoreLoggoato.setRuolo(re);
		utenteVisitatoreLoggoato.setNome(".");
		utenteVisitatoreLoggoato.setCognome(".");
		utenteVisitatoreLoggoato.setCodFiscale("ZMPGPP46M17B662K");

		return utenteVisitatoreLoggoato;
	}

	/**
	 * @return
	 */
	private PLFTUtenteEntity getUtenteLoggato()
	{
		PLFTUtenteEntity utenteVisitatoreLoggoato = new PLFTUtenteEntity();
		PLFTRuoloEntity re = new PLFTRuoloEntity();
		re.setIdRuolo(new BigDecimal(UtenteDto.RUOLO_VISITOR_LOGIN));
		re.setDescRuolo("Utente loggato non accreditato");
		utenteVisitatoreLoggoato.setIdUtente(new BigDecimal(99));
		utenteVisitatoreLoggoato.setEmail("hub2work@comune.genova.it");
		utenteVisitatoreLoggoato.setRuolo(re);
		utenteVisitatoreLoggoato.setNome(".");
		utenteVisitatoreLoggoato.setCognome(".");
		utenteVisitatoreLoggoato.setCodiceFiscale("RLLMHL81R11D969N");

		return utenteVisitatoreLoggoato;
	}

	/**
	 * @param session
	 * @param request
	 * @param numero
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mocklogin/{numero}", method = RequestMethod.GET)
	public String dettaglioUtente(HttpSession session, HttpServletRequest request, @PathVariable Integer numero, Model model)
	{
		PLFTUtenteEntity utente = null;

		if (numero == 99)
			utente = getUtenteLoggato();
		else
			utente = utenteService.findUtente(new BigDecimal(numero));

		WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, new UtenteDto(utente));
		return "redirect:/" + HomeHandler.VIEW_HOME;
	}

	/**
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/secure/logout", method = RequestMethod.GET)
	public String logoutSecure(HttpSession session, HttpServletResponse response)
	{
		return logout(session, response);
	}

	/**
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse response)
	{
		logger.debug("UtenteHandler:: logout utente BEGIN");
		UserContext.getInstance().clear();
		session.invalidate();

		String useSpid = utenteService.getParameter("SPID");
		if ("TRUE".equals(useSpid))
		{
			// TODO SPID
			response.setHeader("Location", AmbienteContext.getSpidLogoutUrl());
			response.setStatus(302);
			logger.debug("UtenteHandler:: logout utente END");
			return null;
		}

		logger.debug("UtenteHandler:: logout utente END");
		return "redirect:/" + HomeHandler.VIEW_HOME;
	}

	/**
	 * @param model
	 * @param request
	 * @param dettaglio
	 * @return
	 */
	@RequestMapping(value = "/autentica", method = RequestMethod.POST)
	public String autentica(Model model, HttpServletRequest request, AutenticazioneDto dettaglio)
	{
		logger.debug("UtenteHandler:: autentica utente BEGIN");
		String call = dettaglio.getCurrentCall();
		try
		{
			PLFTUtenteEntity utente = utenteService.login(dettaglio.getEmail(), dettaglio.getPassword());
			if (utente != null)
			{
				WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, new UtenteDto(utente));
			}

			logger.debug("UtenteHandler:: autentica utente END");

			if (call != null && call.startsWith("/vimp"))
				call = call.substring("/vimp".length());

			return "redirect:" + call;
		}
		catch (PasswordExpiredException ex)
		{
			// TODO DA FARE LOGIN
			WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, new UtenteDto(ex.getUtente()));
			WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY_TEMPORANY, true);
			model.addAttribute("errorMessage", getMessage("errorePasswordScaduta"));
			model.addAttribute("modifica", true);
			model.addAttribute("dettaglio", new AutenticazioneDto(ex.getUtente()));

			return VIEW_MODIFICA_PROFILO;
		}
		catch (PasswordTemporanyException ex)
		{
			// TODO DA FARE LOGIN
			WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, new UtenteDto(ex.getUtente()));
			WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY_TEMPORANY, true);
			model.addAttribute("errorMessage", getMessage("errorePasswordTemporanea"));
			model.addAttribute("modifica", true);
			model.addAttribute("dettaglio", new AutenticazioneDto(ex.getUtente()));

			return VIEW_MODIFICA_PROFILO;
		}
		catch (AuthenticationException ex)
		{
			model.addAttribute("errorMessage", getMessage("erroreLoginPassword"));
			model.addAttribute("dettaglio", dettaglio);

			if (call != null && call.endsWith("search"))
				return VIEW_START_LOGIN;

			return VIEW_LOGIN;
		}
	}

}
