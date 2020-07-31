package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFVInformazioneEntity;
import it.interlogic.vimp.service.IRicercaService;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.MailContattaci;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.dto.ParametriRicercaMyInfo;
import it.interlogic.vimp.web.security.AmbienteContext;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

@Controller
public class HomeHandler extends AbstractHandler
{

	public static final String VIEW_HOME = "home";
	public static final String VIEW_HOME_PERSONAL = "homePersonal";

	private static final String VIEW_CONTATTI = "contatti";
	private static final String VIEW_TERMINI = "termini";
	private static final String VIEW_COOKIES = "cookies";

	@Autowired
	private JavaMailSender mailSender;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	protected IRicercaService ricercaService;

	@Autowired
	protected ServletContext context;

	// ==========================================================================
	// HOME - SEARCH

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/homeSearch")
	public String homeSearch(ParametriRicerca parametri, Model model, HttpSession session)
	{
		logger.debug("HomeHandler::home: BEGIN");
		if (parametri == null)
			parametri = new ParametriRicerca();
		String view = internalSearch(parametri, model, session, true);
		logger.debug("HomeHandler::home: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/homeSearch")
	public String homeSearchSecure(ParametriRicerca parametri, Model model, HttpSession session)
	{
		return homeSearch(parametri, model, session);
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home(ParametriRicerca parametri, Model model, HttpSession session)
	{
		logger.debug("HomeHandler::home: BEGIN");

		if (parametri == null)
			parametri = new ParametriRicerca();

		String view = internalSearch(parametri, model, session);

		logger.debug("HomeHandler::home: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/home")
	public String homeSecure(ParametriRicerca parametri, Model model, HttpSession session)
	{
		return home(parametri, model, session);
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure")
	public String homeSecureVoid(ParametriRicerca parametri, Model model, HttpSession session)
	{
		return home(parametri, model, session);
	}

	/**
	 * @param parametri
	 * @param tipoInformazione
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/home/{tipoInformazione}", method = RequestMethod.GET)
	public String search(ParametriRicerca parametri, @PathVariable Integer tipoInformazione, Model model, HttpSession session)
	{
		logger.debug("search: BEGIN");

		parametri.setTipoInformazione(tipoInformazione);

		String view = internalSearch(parametri, model, session);

		logger.debug("search:  END");
		return view;
	}

	

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/home/{tipoInformazione}", method = RequestMethod.GET)
	public String searchSecure(ParametriRicerca parametri, Model model, HttpSession session)
	{
		return home(parametri, model, session);
	}
	
	
	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/homePersonal")
	public String homePersonal(ParametriRicercaMyInfo parametriRicerca, Model model, HttpSession session,HttpServletRequest request)
	{
		logger.debug("HomeHandler::homePersonal: BEGIN");

		if (parametriRicerca == null)
			parametriRicerca = new ParametriRicercaMyInfo();

		String view = internalPersonalSearch(parametriRicerca, model, session,request);
		
		
		model.addAttribute("parametriRicerca", parametriRicerca);
		
		logger.debug("HomeHandler::homePersonal: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	private String internalPersonalSearch(ParametriRicercaMyInfo parametriRicerca, Model model, HttpSession session, HttpServletRequest request)
	{
		String view = VIEW_HOME_PERSONAL;

		int numeroPagina = parametriRicerca.getValoreNumeroPagina() - 1;
		@SuppressWarnings("unchecked")
		List<PLFVInformazioneEntity> storedList = (List<PLFVInformazioneEntity>) WebUtils.getSessionAttribute(request, UtenteContext.USER_PERSONAL_LIST_KEY);
		ParametriRicercaMyInfo storedParam = (ParametriRicercaMyInfo) WebUtils.getSessionAttribute(request, UtenteContext.USER_PERSONAL_LIST_PARAMS_KEY);
		if (storedParam == null)
			storedParam = parametriRicerca;
		
		if (storedList == null || numeroPagina <= 0 || !storedParam.equals(parametriRicerca))
		{
			storedList = ricercaService.findInformazioniPersonal(parametriRicerca);
			WebUtils.setSessionAttribute(request, UtenteContext.USER_PERSONAL_LIST_KEY, storedList);
			WebUtils.setSessionAttribute(request, UtenteContext.USER_PERSONAL_LIST_PARAMS_KEY, parametriRicerca);
		}

		int numeroRecordPerPagina = Integer.parseInt(parametriRicerca.getNumeroRecord());
		int paginaCorrente = Integer.parseInt(parametriRicerca.getPaginaCorrente())-1;
		parametriRicerca.setPaginaCorrente(parametriRicerca.getPaginaCorrente());

		int fromIndex = numeroRecordPerPagina * paginaCorrente;
		
		int toIndex = fromIndex + numeroRecordPerPagina;
		if (fromIndex + numeroRecordPerPagina > storedList.size())
			toIndex = storedList.size();
		
		model.addAttribute("widgetPaginazione", storedList.size() > numeroRecordPerPagina);
		
		List<PLFVInformazioneEntity> pageResult = storedList.subList(fromIndex, toIndex);

		int totalPage = 1;
		if (storedList.size() > numeroRecordPerPagina)
		{
			totalPage = (int) (storedList.size() / numeroRecordPerPagina);
			if (storedList.size() % numeroRecordPerPagina > 0)
				totalPage += 1;
		}

		model.addAttribute("lista", pageResult);
		model.addAttribute("totRecord", storedList.size());
		model.addAttribute("totPagine", totalPage);
		model.addAttribute("paginaCorrente", paginaCorrente + 1);
		model.addAttribute("numeroRecordPerPagina", numeroRecordPerPagina);
		
		model.addAttribute("numeroRecordDa", (paginaCorrente * numeroRecordPerPagina) + 1);
		model.addAttribute("numeroRecordA", Math.min((paginaCorrente * numeroRecordPerPagina) + numeroRecordPerPagina, storedList.size()));

		setCounter(model);
		return view;
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	private String internalSearch(ParametriRicerca parametri, Model model, HttpSession session)
	{
		return internalSearch(parametri, model, session, false);
	}

	/**
	 * @param parametri
	 * @param model
	 * @param session
	 * @param forceSearch
	 * @return
	 */
	private String internalSearch(ParametriRicerca parametri, Model model, HttpSession session, boolean forceSearch)
	{
		String view = VIEW_HOME;
		model.addAttribute("widgetPaginazione", false);
		boolean onlyPublic = !UtenteContext.getCurrentUser().isBackoffice();

		if (forceSearch)
		{
			model.addAttribute("widgetPaginazione", true);
		}
		else if (parametri != null && parametri.getTextRicerca() != null && parametri.getTextRicerca().trim().length() > 0)
		{
			model.addAttribute("widgetPaginazione", true);
		}

		int numeroPagina = parametri.getValoreNumeroPagina() - 1;

		Page<PLFVInformazioneEntity> risultato = null;
		if (parametri.getTipoInformazione() > 0)
		{
			if (parametri.getTipoInformazione().intValue() == IAbstractServiceImpl.TIPO_INFO_IMPRESA)
			{
				BigDecimal[] stato = null;

				List<BigDecimal> stati = new ArrayList<BigDecimal>();
				if ("S".equals(parametri.getFindStartup()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA));
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_START_UP));
				}

				if ("S".equals(parametri.getFindPmi()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA));
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_PMI));
				}

				if ("S".equals(parametri.getFindSpinoff()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_SPIN_OFF_UNIVERSITARIA));
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_SPIN_OFF_AZIENDALE));
				}

				if ("S".equals(parametri.getFindGrandi()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_GRANDI_IMPRESE));
				}

				if (stati.size() > 0)
				{
					stato = new BigDecimal[stati.size()];
					for (int i = 0; i < stati.size(); i++)
						stato[i] = stati.get(i);
				}

				risultato = ricercaService.findInformazioniByStato(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
						parametri.getTextRicerca(), stato, onlyPublic);
			}
			else if (parametri.getTipoInformazione().intValue() == IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER)
			{
				if ("S".equals(parametri.getFindIncubatori()))
				{
					BigDecimal[] stato = new BigDecimal[] { new BigDecimal(IAbstractServiceImpl.STAKEHOLDER_INCUBATORE) };
					risultato = ricercaService.findInformazioniByStato(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
							parametri.getTextRicerca(), stato, onlyPublic);
				}
				else
				{
					risultato = ricercaService.findInformazioni(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
							parametri.getTextRicerca(), onlyPublic);
				}
			}
			else if (parametri.getTipoInformazione().intValue() == IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO)
			{
				BigDecimal[] stato = null;

				List<BigDecimal> stati = new ArrayList<BigDecimal>();
				if ("S".equals(parametri.getFindProgetti()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.TIPO_PROGETTO));
				}

				if ("S".equals(parametri.getFindProdotti()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.TIPO_PRODOTTO));
				}

				if ("S".equals(parametri.getFindTecnologie()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.TIPO_TECNOLOGIA));
				}

				if ("S".equals(parametri.getFindInnovazione()))
				{
					stati.add(new BigDecimal(IAbstractServiceImpl.TIPO_INNOVAZIONE));
				}

				if (stati.size() > 0)
				{
					stato = new BigDecimal[stati.size()];
					for (int i = 0; i < stati.size(); i++)
						stato[i] = stati.get(i);
				}

				risultato = ricercaService.findInformazioniByStato(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
						parametri.getTextRicerca(), stato, onlyPublic);
			}
			else if (parametri.getTipoInformazione().intValue() == IAbstractServiceImpl.TIPO_INFO_SERVIZIO)
			{
				BigDecimal[] tipo = null;
				List<BigDecimal> tipi = new ArrayList<BigDecimal>();

				if (parametri.getFindServizi().equals("S"))
					tipi.add(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO));

				if (parametri.getFindPacchettiServizi().equals("S"))
					tipi.add(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI));

				if (tipi.size() > 0)
				{
					tipo = new BigDecimal[tipi.size()];
					for (int i = 0; i < tipi.size(); i++)
						tipo[i] = tipi.get(i);
				}

				risultato = ricercaService.findInformazioniByTipo(numeroPagina, parametri.getValoreNumeroRecord(), tipo, parametri.getTextRicerca(), onlyPublic);
			}
			else if (parametri.getTipoInformazione().intValue() == IAbstractServiceImpl.TIPO_INFO_NEWS)
			{
				if (!"S".equals(parametri.getFindNewsEvidenza()))
					risultato = ricercaService.findInformazioni(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
							parametri.getTextRicerca(), onlyPublic);
				else
				{

					BigDecimal[] stato = { new BigDecimal(IAbstractServiceImpl.STATO_NEWS_EVIDENZA) };
					BigDecimal[] tipoInfo = { new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS) };

					risultato = ricercaService
							.findInformazioniByTipoStato(numeroPagina, parametri.getValoreNumeroRecord(), tipoInfo, parametri.getTextRicerca(), stato, onlyPublic);
				}

			}
			else
			{

				risultato = ricercaService.findInformazioni(numeroPagina, parametri.getValoreNumeroRecord(), new BigDecimal(parametri.getTipoInformazione()),
						parametri.getTextRicerca(), onlyPublic);
			}
		}
		else
			risultato = ricercaService.findInformazioni(numeroPagina, parametri.getValoreNumeroRecord(), parametri.getTextRicerca(), onlyPublic);

		if (risultato != null)
		{

			int numeroRecordPerPagina = Integer.parseInt(parametri.getNumeroRecord());
			
			model.addAttribute("lista", risultato.getContent());
			model.addAttribute("totRecord", risultato.getTotalElements());
			model.addAttribute("totPagine", risultato.getTotalPages());
			model.addAttribute("paginaCorrente", risultato.getNumber() + 1);
			model.addAttribute("numeroRecordDa", (risultato.getNumber() * numeroRecordPerPagina) + 1);
			model.addAttribute("numeroRecordA", Math.min((risultato.getNumber() * numeroRecordPerPagina) + numeroRecordPerPagina, risultato.getTotalElements()));
			
		}
		else
		{
			model.addAttribute("totRecord", 0);
			model.addAttribute("totPagine", 0);
			model.addAttribute("paginaCorrente", 0);
			model.addAttribute("numeroRecordDa", 0);
			model.addAttribute("numeroRecordA", 0);
		}

		setCounter(model);

		return view;
	}

	/**
	 * @param model
	 */
	private void setCounter(Model model)
	{
		model.addAttribute("prodottiRegistrati", ricercaService.countProdottiTecnologie());
		model.addAttribute("startupRegistrate", ricercaService.countStartup());
		model.addAttribute("pmiRegistrate", ricercaService.countPmi());
		model.addAttribute("progettiRegistrati", ricercaService.countProgetti());
		model.addAttribute("spinoffRegistrati", ricercaService.countSpinoff());
		model.addAttribute("grandiRegistrati", ricercaService.countGrandi());

	}

	// ==========================================================================
	// DETAIL

	/**
	 * @param session
	 * @param parametri
	 * @param tipoInformazione
	 * @param numero
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{tipoInformazione}/{numero}", method = RequestMethod.GET)
	public String detail(HttpSession session, ParametriRicerca parametri, @PathVariable Integer tipoInformazione, @PathVariable Integer numero, Model model)
	{
		logger.debug("HomeHandler::dettaglio: BEGIN");

		try
		{
			switch (tipoInformazione.intValue())
			{
				case IAbstractServiceImpl.TIPO_INFO_IMPRESA:
					return "redirect:/impresa/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER:
					return "redirect:/stakeholder/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_SERVIZIO:
					return "redirect:/servizi/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI:
					return "redirect:/pacchettoServizi/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO:
					return "redirect:/progetto/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA:
					return "redirect:/opportunita/" + numero.intValue();
				case IAbstractServiceImpl.TIPO_INFO_NEWS:
					return "redirect:/news/" + numero.intValue();
				default:
					return "redirect:/" + HomeHandler.VIEW_HOME;
			}
		}
		finally
		{
			logger.debug("HomeHandler::dettaglio: END");
		}
	}

	/**
	 * @param session
	 * @param parametri
	 * @param tipoInformazione
	 * @param numero
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/detail/{tipoInformazione}/{numero}", method = RequestMethod.GET)
	public String detailSecure(HttpSession session, ParametriRicerca parametri, @PathVariable Integer tipoInformazione, @PathVariable Integer numero, Model model)
	{
		return detail(session, parametri, tipoInformazione, numero, model);
	}

	/**
	 * @param session
	 * @param parametri
	 * @param tipoInformazione
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/newElement/{tipoInformazione}", method = RequestMethod.GET)
	public String newElement(HttpSession session, ParametriRicerca parametri, @PathVariable Integer tipoInformazione, Model model)
	{
		logger.debug("HomeHandler::newElement: BEGIN");
		try
		{
			return detail(session, parametri, tipoInformazione, new Integer(0), model);
		}
		finally
		{
			logger.debug("HomeHandler::newElement: END");
		}
	}

	// ==========================================================================
	// FOOTER PAGES

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mailContattaci", method = RequestMethod.POST)
	public String mailContattaci(HttpServletRequest request, HttpSession session, MailContattaci dettaglio, Model model)
	{
		logger.debug("HomeHandler::mailContattaci: BEGIN");

		if (dettaglio != null && dettaglio.getEmail() != null && dettaglio.getOggetto() != null && dettaglio.getMessaggio() != null && dettaglio.getCognome() != null)
		{
			Map<String, String> mailData = new HashMap<String, String>();

			String nomeCognome = dettaglio.getCognome();
			if (dettaglio.getNome() != null)
				nomeCognome = dettaglio.getNome() + " " + nomeCognome;

			mailData.put("nomeCognome", nomeCognome);
			mailData.put("data", dateFormat.format(new Date()));
			mailData.put("messaggio", dettaglio.getMessaggio());

			String body = null;

			try
			{
				body = fillMailBody("MailContattaci.txt", mailData);
				doSendEmail(dettaglio.getEmail(), AmbienteContext.getMailAssistenza(), dettaglio.getOggetto(), body);
			}
			catch (Exception err)
			{
				err.printStackTrace();
			}

		}

		logger.debug("HomeHandler::mailContattaci: END");
		return "redirect:/contact";
	}

	/**
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 */
	private void doSendEmail(String from, String to, String subject, String body)
	{
		LoggerUtility.error("----------------- Send mail begin");
		try
		{
			LoggerUtility.error("from:" + from);
			LoggerUtility.error("to:" + to);
			LoggerUtility.error("subject:" + subject);
			LoggerUtility.error("body:");
			LoggerUtility.error(body);

			// creates a simple e-mail object
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom(from);
			email.setTo(to);
			email.setSubject(subject);
			email.setText(body);

			// sends the e-mail
			// TODO SEND MAIL
			mailSender.send(email);
		}
		catch (Exception err)
		{
			LoggerUtility.error("Impostare il server SMPT in servlet.xml", err);
		}
		LoggerUtility.error("----------------- Send mail end");
	}

	/**
	 * @param mailFileName
	 * @param values
	 * @return
	 * @throws Exception
	 */
	private String fillMailBody(String mailFileName, Map<String, String> values) throws Exception
	{
		String textMail = null;
		String theString = IOUtils.toString(context.getResourceAsStream("/WEB-INF/classes/mail/" + mailFileName), "UTF-8");
		StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
		textMail = sub.replace(theString);
		return textMail;
	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/mailContattaci", method = RequestMethod.POST)
	public String mailContattaciSecure(HttpServletRequest request, HttpSession session, MailContattaci dettaglio, Model model)
	{
		return mailContattaci(request, session, dettaglio, model);
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request)
	{
		logger.debug("HomeHandler::contact: BEGIN");
		logger.debug("HomeHandler::contact:   END");
		return VIEW_CONTATTI;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/secure/contact", method = RequestMethod.GET)
	public String contactSecure(Model model, HttpServletRequest request)
	{
		return contact(model, request);
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/termini", method = RequestMethod.GET)
	public String termini(Model model, HttpServletRequest request)
	{
		logger.debug("HomeHandler::termini: BEGIN");

		logger.debug("HomeHandler::termini:   END");
		return VIEW_TERMINI;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/secure/termini", method = RequestMethod.GET)
	public String terminiSecure(Model model, HttpServletRequest request)
	{
		return termini(model, request);
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cookies", method = RequestMethod.GET)
	public String cookies(Model model, HttpServletRequest request)
	{
		logger.debug("HomeHandler::cookies: BEGIN");

		String returnView = VIEW_COOKIES;
		String language = LocaleContextHolder.getLocale().getLanguage();
		String region = LocaleContextHolder.getLocale().getCountry();
		if (language != null && language.trim().length() > 0 && region != null && region.trim().length() > 0)
			returnView = VIEW_COOKIES + "_" + language + "_" + region;

		logger.debug("HomeHandler::cookiesO:   END");
		return returnView;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/secure/cookies", method = RequestMethod.GET)
	public String cookiesSecure(Model model, HttpServletRequest request)
	{
		return cookies(model, request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return null;
	}
}
