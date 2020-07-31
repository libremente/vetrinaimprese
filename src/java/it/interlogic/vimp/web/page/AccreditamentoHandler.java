package it.interlogic.vimp.web.page;

import it.interlogic.vimp.batch.Constants;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.model.PLFRichiestaAccreditamentoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTControlliRichiestaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoRichiestaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVRichiestaAccreditamentoEntity;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.IAccreditamentoService;
import it.interlogic.vimp.service.IArisService;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IImpresaService;
import it.interlogic.vimp.service.ILogService;
import it.interlogic.vimp.service.IRicercaService;
import it.interlogic.vimp.service.IUtenteService;
import it.interlogic.vimp.service.exception.AccreditamentoException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult;
import it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.utils.UtilityDate;
import it.interlogic.vimp.utils.UtilityStringhe;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.Colonna;
import it.interlogic.vimp.web.dto.ParametriRicercaAccreditamenti;
import it.interlogic.vimp.web.dto.RichiestaAccreditamento;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
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
public class AccreditamentoHandler extends AbstractHandler
{

	private static final String VIEW_RICHIESTA = "richiestaAccreditamento";
	private static final String VIEW_RICERCA_ACCREDITAMENTI = "ricercaAccreditamenti";

	private static final String VIEW_DETTAGLIO_RICHIESTA_ACCREDITAMENTO = "dettaglioRichiestaAccreditamento";

	private static final String COLONNE_ACCRDITAMENTO_SESSION_KEY = "COLONNE_ACCRDITAMENTO_SESSION_KEY";

	@Autowired
	protected IArisService arisService;

	@Autowired
	protected IImpresaService impresaService;

	@Autowired
	protected IRicercaService ricercaService;

	@Autowired
	protected IAccreditamentoService accreditamentoService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	protected IUtenteService utenteService;

	@Autowired
	protected ServletContext context;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	protected ILogService logService;


	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
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
		return "richiestaAccreditamento";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/dettaglioAccreditamento/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicercaAccreditamenti parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("AccreditamentoHandler::detail: BEGIN");
		PLFRichiestaAccreditamentoEntity dettaglio = accreditamentoService.find(new BigDecimal(numero));

		if (dettaglio != null && dettaglio.getIdRichiestaAccreditamento() != null && dettaglio.getIdRichiestaAccreditamento().intValue() > 0)
		{
			if (dettaglio.getIdControlliRichiesta() != null && dettaglio.getIdControlliRichiesta().intValue() > 0)
			{
				PLFTControlliRichiestaEntity controlliRichiesta = decodificheService.getControlliRichiesta(dettaglio.getIdControlliRichiesta());
				if (controlliRichiesta != null)
					dettaglio.setDescControlliRichiesta(controlliRichiesta.getDescrizione());
			}
			if (dettaglio.getIdStatoRichiesta() != null && dettaglio.getIdStatoRichiesta().intValue() > 0)
			{
				PLFTStatoRichiestaEntity statoRichiesta = decodificheService.getStatoRichiesta(dettaglio.getIdStatoRichiesta());
				if (statoRichiesta != null)
					dettaglio.setDescStatoRichiesta(statoRichiesta.getDescrizione());
			}
		}

		model.addAttribute("modifica", false);
		if (dettaglio.getIdStatoRichiesta() != null && dettaglio.getIdStatoRichiesta().intValue() == IAbstractServiceImpl.STATO_RICHIESTA_ATTESA)
			model.addAttribute("modifica", true);

		model.addAttribute("dettaglio", dettaglio);

		model.addAttribute("cambiaStatoImpresa", true);
		if (dettaglio.getPlfImpresa()!=null&& dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue()>0)
		{
			PLFLogEntity log = logService.getLogImpresa(dettaglio.getPlfImpresa().getIdPlfImpresa());
			if (log != null && log.getIdLog() != null && log.getIdLog().intValue() > 0)
			{
				int idMessaggioLog = log.getLogMessaggi().getId().intValue();
				if (idMessaggioLog == IAbstractServiceImpl.LOG_IMPRESA_PRESENTE_VERTINA_CANCELLATA_ARIS
						|| idMessaggioLog == IAbstractServiceImpl.LOG_IMPRESA_PRESENTE_ARIS_NON_ISCRITTA
						|| idMessaggioLog == IAbstractServiceImpl.LOG_IMPRESA_PRSENTE_VETRINA_NO_ARIS)
				{
					model.addAttribute("cambiaStatoImpresa", true);
				}
			}
		}
		
		model.addAttribute("statoImpresaList", IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, true));

		parametri.setTipoInformazione(0);
		model.addAttribute("parametriRicerca", parametri);
		logger.debug("AccreditamentoHandler::detail: END");
		return VIEW_DETTAGLIO_RICHIESTA_ACCREDITAMENTO;
	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/accreditamento")
	public String accreditamento(HttpServletRequest request, ParametriRicercaAccreditamenti parametri, Model model, HttpSession session)
	{
		logger.debug("AccreditamentoHandler::accreditamento: BEGIN");
		prepareModelToSearchAccreditamenti(request, parametri, model, session);
		logger.debug("AccreditamentoHandler::accreditamento: END");
		return VIEW_RICERCA_ACCREDITAMENTI;
	}

	/**
	 * @param dettaglio
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/richiestaAccreditamento", method = RequestMethod.GET)
	public String richiestaAccreditamento(RichiestaAccreditamento dettaglio, Model model, HttpSession session)
	{
		logger.debug("AccreditamentoHandler::richiestaAccreditamento: BEGIN");

		dettaglio = new RichiestaAccreditamento();

		model.addAttribute("refreshRelativeUrl", "/secure/richiestaAccreditamento");

		model.addAttribute("privacyCheck", "N");

		model.addAttribute("dettaglio", dettaglio);

		model.addAttribute("statoImpresaList", IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, false));

		logger.debug("AccreditamentoHandler::richiestaAccreditamento: END");
		return VIEW_RICHIESTA;
	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/inviaRichiesta", method = RequestMethod.POST)
	public String inviaRichiesta(HttpServletRequest request, HttpSession session, RichiestaAccreditamento dettaglio, Model model)
	{
		logger.debug("AccreditamentoHandler::inviaRichiesta: BEGIN");
		try
		{
			accredita(dettaglio, request);
			return "redirect:/home";
		}
		catch (AccreditamentoException err)
		{
			err.printStackTrace();
			if (err.isInfo())
				model.addAttribute("infoMessage", err.getMessage());
			else if (err.isWarning())
				model.addAttribute("warningMessage", err.getMessage());
			else
				model.addAttribute("errorMessage", err.getMessage());

			model.addAttribute("refreshRelativeUrl", "/secure/richiestaAccreditamento");
			model.addAttribute("dettaglio", dettaglio);
			model.addAttribute("statoImpresaList",
					IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, false));
			model.addAttribute("privacyCheck", "S");

			return VIEW_RICHIESTA;
		}
		finally
		{
			logger.debug("AccreditamentoHandler::inviaRichiesta: END");
		}

	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	public Model prepareModelToSearchAccreditamenti(HttpServletRequest request, ParametriRicercaAccreditamenti parametri, Model model, HttpSession session)
	{
		if (parametri == null)
			parametri = new ParametriRicercaAccreditamenti();

		int numeroPagina = parametri.getValoreNumeroPagina() - 1;

		parametri.setColonne(getElencoColonneAccreditamenti(session, parametri.getColonneResult()));
		model.addAttribute("parametriRicerca", parametri);

		CriteriQuery filtri = estraiFiltroDiRicercaAccreditamenti(parametri);

		Page<PLFVRichiestaAccreditamentoEntity> risultato = ricercaService.elencoRichiesteAccreditamento(numeroPagina, parametri.getValoreNumeroRecord(), filtri);
		List<PLFVRichiestaAccreditamentoEntity> lista = risultato.getContent();

		ArrayList<Map<String, Object>> elenco = new ArrayList<Map<String, Object>>();
		for (PLFVRichiestaAccreditamentoEntity plfVRichiestaAccreditamentoEntity : lista)
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> oggettoMappa = new org.apache.commons.beanutils.BeanMap(plfVRichiestaAccreditamentoEntity);
			oggettoMappa = UtilityStringhe.escapeHtmlValues(oggettoMappa);
			elenco.add(oggettoMappa);
		}
		model.addAttribute("lista", elenco);

		model.addAttribute("totRecord", risultato.getTotalElements());
		model.addAttribute("totPagine", risultato.getTotalPages());
		model.addAttribute("paginaCorrente", risultato.getNumber() + 1);
		int numeroRecordPerPagina = Integer.parseInt(parametri.getNumeroRecord());
		model.addAttribute("numeroRecordDa", (risultato.getNumber() * numeroRecordPerPagina) + 1);
		model.addAttribute("numeroRecordA", Math.min((risultato.getNumber() * numeroRecordPerPagina) + numeroRecordPerPagina, risultato.getTotalElements()));

		return model;
	}

	/**
	 * @param session
	 * @param colonneResult
	 * @return
	 */
	protected List<Colonna> getElencoColonneAccreditamenti(HttpSession session, List<String> colonneResult)
	{
		try
		{
			@SuppressWarnings("unchecked")
			List<Colonna> colonneSession = (List<Colonna>) session.getAttribute(COLONNE_ACCRDITAMENTO_SESSION_KEY);
			if (colonneSession == null)
			{

				colonneSession = new ArrayList<Colonna>();
				colonneSession.add(Colonna.getInstance("dataRichiestaColumn", "dataRichiesta", true, true));
				colonneSession.add(Colonna.getInstance("ragioneSocialeColumn", "ragioneSociale", true, true));
				colonneSession.add(Colonna.getInstance("codiceFiscaleColumn", "codiceFiscale", false, false));
				colonneSession.add(Colonna.getInstance("partitaIvaColumn", "partitaIva", false, false));
				colonneSession.add(Colonna.getInstance("descStatoImpresaColumn", "descStatoImpresa", true, true));
				colonneSession.add(Colonna.getInstance("descControlliRichiestaColumn", "descControlliRichiesta", true, true));
				colonneSession.add(Colonna.getInstance("descStatoRichiestaColumn", "descStatoRichiesta", true, true));
				session.setAttribute(COLONNE_ACCRDITAMENTO_SESSION_KEY, colonneSession);
			}

			if (colonneResult != null)
			{
				for (Colonna colonnaSession : colonneSession)
					colonnaSession.setChecked(false);

				for (String colonnaResult : colonneResult)
				{
					for (Colonna colonnaSession : colonneSession)
					{
						if (colonnaSession.getNomeDato().equals(colonnaResult))
						{
							colonnaSession.setChecked(true);
							break;
						}
					}
				}
			}

			session.setAttribute(COLONNE_ACCRDITAMENTO_SESSION_KEY, colonneSession);
			return colonneSession;

		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	/**
	 * @param parametri
	 * @return
	 */
	protected CriteriQuery estraiFiltroDiRicercaAccreditamenti(ParametriRicercaAccreditamenti parametri)
	{
		CriteriQuery cq = new CriteriQuery();
		if (parametri != null)
		{
			cq.addParametroLike("testo", toString(parametri.getTesto()));

			List<BigDecimal> statoRichiestaList = new ArrayList<BigDecimal>();
			if ("S".equalsIgnoreCase(parametri.getFindValidateAuto()))
				statoRichiestaList.add(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_VALIDATA_AUTO));
			if ("S".equalsIgnoreCase(parametri.getFindAttesa()))
				statoRichiestaList.add(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_ATTESA));
			if ("S".equalsIgnoreCase(parametri.getFindNonValidate()))
				statoRichiestaList.add(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_NON_VALIDATO));
			if ("S".equalsIgnoreCase(parametri.getFindValidate()))
				statoRichiestaList.add(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_VALIDATA));

			if (statoRichiestaList.size() > 0)
			{
				BigDecimal[] statoRichiestaArray = new BigDecimal[statoRichiestaList.size()];
				for (int i = 0; i < statoRichiestaList.size(); i++)
					statoRichiestaArray[i] = statoRichiestaList.get(i);
				cq.addParametroIn("idStatoRichiesta", statoRichiestaArray);
			}

			cq.addParametroBetween("dataRichiesta", intervalloDateDa(parametri.getDateRange()), intervalloDateA(parametri.getDateRange()));

		}
		return cq;
	}

	/**
	 * @param dateRange
	 * @return
	 */
	protected java.util.Date intervalloDateA(String dateRange)
	{
		if (StringUtils.isEmpty(dateRange))
			return null;
		String a = StringUtils.trim(StringUtils.substringAfter(dateRange, "-"));
		Date dateA = UtilityDate.parseItalianDate(a);
		return dateA;

	}

	/**
	 * @param dateRange
	 * @return
	 */
	protected java.util.Date intervalloDateDa(String dateRange)
	{
		if (StringUtils.isEmpty(dateRange))
			return null;
		String da = StringUtils.trim(StringUtils.substringBefore(dateRange, "-"));
		Date dateDa = UtilityDate.parseItalianDate(da);
		return dateDa;
	}

	/**
	 * @param valore
	 * @return
	 */
	protected String toString(String valore)
	{
		if (StringUtils.isEmpty(valore))
			return null;

		return "%" + valore.toUpperCase() + "%";

	}

	// =====================================================================================================
	// =====================================================================================================
	// ACCREDITAMENTO

	/**
	 * @param dettaglio
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/secure/validazioneAccreditamento", method = RequestMethod.POST)
	public String validazioneAccreditamento(PLFRichiestaAccreditamentoEntity dettaglio, Model model, HttpSession session, HttpServletRequest request)
	{
		LoggerUtility.info(dettaglio.toString());

		PLFRichiestaAccreditamentoEntity entity = accreditamentoService.find(dettaglio.getIdRichiestaAccreditamento());
		int oldState = 0;
		int newState = 0;
		boolean sendMailCambiaStato = false;
		if (entity != null && entity.getPlfTStatoImpresa() != null && entity.getPlfTStatoImpresa().getId() != null && entity.getPlfTStatoImpresa().getId().intValue() > 0)
		{
			oldState = entity.getPlfTStatoImpresa().getId().intValue();
			newState = (dettaglio.getPlfTStatoImpresa() != null && dettaglio.getPlfTStatoImpresa().getId() != null) ? dettaglio.getPlfTStatoImpresa().getId().intValue(): 0;
			if (oldState != newState)
				sendMailCambiaStato = true;
		}
		
		
		if ("valida".equalsIgnoreCase(dettaglio.getAzione()))
		{

			PLFImpresaEntity impresa = new PLFImpresaEntity();

			if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
			{
				PLFImpresaEntity tmp = impresaService.find(dettaglio.getPlfImpresa().getIdPlfImpresa());
				if (tmp != null && tmp.getIdPlfImpresa() != null && tmp.getIdPlfImpresa().intValue() > 0)
					impresa = tmp;
			}
			else
			{
				PLFImpresaEntity tmp = impresaService.find(dettaglio.getPartitaIva(), dettaglio.getCodFiscale());
				if (tmp != null && tmp.getIdPlfImpresa() != null && tmp.getIdPlfImpresa().intValue() > 0)
					impresa = tmp;
			}

			if (impresa == null || impresa.getIdPlfImpresa() == null || impresa.getIdPlfImpresa().intValue() <= 0)
			{
				// TODO mandare un messaggio impresa non presente in archivio
				// throw new
				// AccreditamentoException(getMessage("accreditamentoArisNonPresente",
				// new Object[] { "" }));
			}

			if (impresa.getImpresaTranslation() == null)
				impresa.setImpresaTranslation(new PLFImpresaTranslationEntity());

			impresa.getImpresaTranslation().setDescFonte(Constants.DESC_FONTE_ACCREDITAMENTO);

			impresa.getImpresaTranslation().setDescImpresa(dettaglio.getRagioneSociale());

			if (impresa.getImpresaTranslation() == null || impresa.getImpresaTranslation().getDescBreveImpresa() == null
					|| impresa.getImpresaTranslation().getDescBreveImpresa().length() <= 0)
			{
				impresa.getImpresaTranslation().setDescBreveImpresa(dettaglio.getDescImpresa());
			}
			

			impresa.setCodFiscale(dettaglio.getCodFiscale());
			impresa.setPartitaIva(dettaglio.getPartitaIva());
			impresa.setDescIndirizzo(dettaglio.getDescIndirizzo());
			impresa.setNumeroCivico(dettaglio.getNumeroCivico());
			impresa.setCodCap(dettaglio.getCodCap());
			impresa.setEmailContatto(dettaglio.getEmailContatto());
			impresa.setDescTelefono(dettaglio.getTelContatto());
			impresa.setPlfTComune(dettaglio.getPlfTComune());
			impresa.setPlfTStatoImpresa(dettaglio.getPlfTStatoImpresa());
			impresa.setDataAccreditamento(new Date());

			PLFTTipoImpresaEntity tipo = new PLFTTipoImpresaEntity();
			tipo.setId(new BigDecimal(IAbstractServiceImpl.TIPO_IMPRESA_AZIENDA));
			impresa.setPlfTTipoImpresa(tipo);

			PLFImpresaEntity impresaEntity = impresaService.updateImpresa(impresa);

			if (impresaEntity != null && impresaEntity.getIdPlfImpresa() != null && impresaEntity.getIdPlfImpresa().intValue() > 0)
			{
				PLFImpresaEntity impresaRichiesta = new PLFImpresaEntity();
				impresaRichiesta.setIdPlfImpresa(impresaEntity.getIdPlfImpresa());
				entity.setPlfImpresa(impresaRichiesta);

				entity.setIdStatoRichiesta(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_VALIDATA));
				entity.setFlagAccreditamento("S");
				entity.setDataRichiesta(new Date());
				entity.setParereAccreditamento(dettaglio.getParereAccreditamento());
				
				
				entity.setPlfTStatoImpresa(dettaglio.getPlfTStatoImpresa());
				accreditamentoService.update(entity);

				utenteService.accreditaUtente(dettaglio.getCodFiscaleRichiedente(), dettaglio.getEmailContatto(), impresaEntity.getIdPlfImpresa(), null, null);
				// WebUtils.setSessionAttribute(request,
				// UtenteContext.USER_SESSION_KEY, new UtenteDto(utente));
			}

			sendMailRichiestaValidata(dettaglio.getEmailContatto(),sendMailCambiaStato,oldState,newState);
		}
		else if ("invalida".equalsIgnoreCase(dettaglio.getAzione()))
		{
			entity.setParereAccreditamento(dettaglio.getParereAccreditamento());
			entity.setIdStatoRichiesta(new BigDecimal(IAbstractServiceImpl.STATO_RICHIESTA_NON_VALIDATO));
			entity.setFlagAccreditamento("N");
			accreditamentoService.update(entity);

			sendMailRichiestaInvalidata(dettaglio.getEmailContatto(), dettaglio.getRagioneSociale(), dettaglio.getParereAccreditamento(),sendMailCambiaStato,oldState,newState);
		}

		return "redirect:/secure/dettaglioAccreditamento/" + dettaglio.getIdRichiestaAccreditamento().intValue();
	}

	/**
	 * @param dettaglio
	 * @param request
	 * @throws AccreditamentoException
	 */
	private void accredita(RichiestaAccreditamento dettaglio, HttpServletRequest request) throws AccreditamentoException
	{
		try
		{
			String codiceFiscaleUtente = UtenteContext.getCurrentUser().getCodiceFiscale();

			PLFRichiestaAccreditamentoEntity accreditamento = accreditamentoService.findRichiesta(dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput());

			if (accreditamento != null)
			{
				// richiesta accreditamento presente
				logger.debug("AccreditamentoHandler::accredita: RICHIESTA PRESENTE");

				LoggerUtility.info("RICHIESTA PRESENTE");
				if (accreditamento.getIdStatoRichiesta() != null)
				{
					if (codiceFiscaleUtente.equalsIgnoreCase(accreditamento.getCodFiscaleRichiedente()))
					{
						if (IAbstractServiceImpl.STATO_RICHIESTA_ATTESA == accreditamento.getIdStatoRichiesta().intValue())
							throw new AccreditamentoException(getMessage("accreditamentoEsistenteInAttesa"));
						else if (IAbstractServiceImpl.STATO_RICHIESTA_NON_VALIDATO == accreditamento.getIdStatoRichiesta().intValue())
						{
							String motivo = accreditamento.getParereAccreditamento();
							if (motivo == null || motivo.trim().length() <= 0)
								motivo = getMessage("no_specify");
							throw new AccreditamentoException(getMessage("accreditamentoNonValitato", new Object[] { motivo }));
						}
						else
							throw new AccreditamentoException(getMessage("accreditamentoValitato"));
					}
					else
					{
						String descr = accreditamento.getDescImpresa();
						if (descr == null || descr.trim().length()<=0)
							descr = accreditamento.getPartitaIva();
						if (descr == null || descr.trim().length()<=0)
							descr = accreditamento.getCodFiscale();
						throw new AccreditamentoException(getMessage("accreditamentoPresenteAltro",new Object[] { descr }));
					}
					
				}
				LoggerUtility.info("PASSO 1");
				throw new AccreditamentoException(getMessage("accreditamentoErroreGenerico"));
			}

			logger.debug("AccreditamentoHandler::accredita: RICHIESTA NON PRESENTE");
			// richiesta accreditamento non presente

			ArisSearch aziendaERappresentanteLegale = null;

			PLFImpresaEntity impresa = impresaService.find(dettaglio.getPartitaIvaInput(), dettaglio.getCodiceFiscaleInput());
			LoggerUtility.info("PASSO 3");

			if (impresa != null && impresa.getIdPlfImpresa() != null && impresa.getIdPlfImpresa().intValue() > 0)
			{
				// AZIENDA PRESENTE SULLA VETRINA

				LoggerUtility.info("PASSO 31");
				if (codiceFiscaleUtente.equalsIgnoreCase(impresa.getCodiceFiscaleLegaleRappresentante()))
				{
					// CODICE FISCALE COINCIDENTE CON IL LEGALE RAPPRESENTANTE
					// SULLA VETRINA

					LoggerUtility.info("PASSO 32");
					accreditaAuto(dettaglio, impresa, codiceFiscaleUtente, request);
				}
				else
				{
					// CODICE FISCALE NON COINCIDENTE CON IL LEGALE
					// RAPPRESENTANTE SULLA VETRINA
					LoggerUtility.info("PASSO 41");
					aziendaERappresentanteLegale = getAziendaERappresentanteLegale(codiceFiscaleUtente, dettaglio);
					LoggerUtility.info("PASSO 42");
					if (aziendaERappresentanteLegale != null && aziendaERappresentanteLegale.hasLegaleRappresentante)
					{
						// CODICE FISCALE RICHIEDENTE PRESENTE SU ARIS
						impresa.setNomeLegaleRappresentante(aziendaERappresentanteLegale.nomeRappresentanteLegale);
						impresa.setCognomeLegaleRappresentante(aziendaERappresentanteLegale.cognomeRappresentanteLegale);
						impresa.setCodiceFiscaleLegaleRappresentante(codiceFiscaleUtente);
						impresaService.update(impresa);
						accreditaAuto(dettaglio, impresa, codiceFiscaleUtente, request);
					}
					else
					{
						// CODICE FISCALE RICHIEDENTE NON PRESENTE SU ARIS
						// XXX
						LoggerUtility.info("PASSO 5");

						String descImpresa = "";
						if (impresa != null)
							descImpresa = impresa.getImpresaTranslation().getDescImpresa();

						int statoImpresa = dettaglio.getIdStatoImpresaInput().intValue();
						String message = "";

						if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA)
							message = getMessage("accreditamentoImpresaUtenteEsistenteArisInnovativa", new Object[] { descImpresa });
						else if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP)
							message = getMessage("accreditamentoImpresaUtenteEsistenteAris", new Object[] { descImpresa });

						else
							message = getMessage("accreditamentoImpresaUtenteEsistenteArisAltro", new Object[] { descImpresa });

						PLFImpresaEntity arisImpresa = arisService.arisToImpresa(aziendaERappresentanteLegale.arisAzienda);
						if (impresa != null && impresa.getIdPlfImpresa() != null && impresa.getIdPlfImpresa().intValue() > 0)
							arisImpresa.setIdPlfImpresa(impresa.getIdPlfImpresa());

						accreditamentoService.salvaRichiesta(codiceFiscaleUtente, dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput(), dettaglio.getEmailInput(),
								dettaglio.getIdStatoImpresaInput(), arisImpresa, IAbstractServiceImpl.STATO_RICHIESTA_ATTESA,
								IAbstractServiceImpl.RICHIESTA_CONTROLLO_PRESENTE_ARIS, false);

						throw new AccreditamentoException(message, true, false);
					}
				}
			}
			else
			{
				// AZIENDA NON PRESENTE SULLA VETRINA

				LoggerUtility.info("PASSO 5");

				if (aziendaERappresentanteLegale == null)
					aziendaERappresentanteLegale = getAziendaERappresentanteLegale(codiceFiscaleUtente, dettaglio);
				if (aziendaERappresentanteLegale != null)
				{
					// AZIENDA PRESENTE SU ARIS

					String descImpresa = "";
					if (impresa != null)
						descImpresa = impresa.getImpresaTranslation().getDescImpresa();
					else if (aziendaERappresentanteLegale.arisAzienda != null)
						descImpresa = aziendaERappresentanteLegale.arisAzienda.getDenominazione_sede();

					int statoImpresa = dettaglio.getIdStatoImpresaInput().intValue();
					String message = "";

					if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA)
						message = getMessage("accreditamentoImpresaUtenteEsistenteArisInnovativa", new Object[] { descImpresa });
					else if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP)
						message = getMessage("accreditamentoImpresaUtenteEsistenteAris", new Object[] { descImpresa });

					else
						message = getMessage("accreditamentoImpresaUtenteEsistenteArisAltro", new Object[] { descImpresa });

					PLFImpresaEntity arisImpresa = arisService.arisToImpresa(aziendaERappresentanteLegale.arisAzienda);
					if (impresa != null && impresa.getIdPlfImpresa() != null && impresa.getIdPlfImpresa().intValue() > 0)
						arisImpresa.setIdPlfImpresa(impresa.getIdPlfImpresa());

					accreditamentoService.salvaRichiesta(codiceFiscaleUtente, dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput(), dettaglio.getEmailInput(),
							dettaglio.getIdStatoImpresaInput(), arisImpresa, IAbstractServiceImpl.STATO_RICHIESTA_ATTESA, IAbstractServiceImpl.RICHIESTA_CONTROLLO_PRESENTE_ARIS,
							false);

					throw new AccreditamentoException(message, true, false);

				}
				else
				{
					// AZIENDA SOLO SU ARIS
					UlSearchResult azienda = getAzienda(dettaglio);
					if (azienda != null)
					{
						// AZIENDA PRESENTE SU ARIS MA NON C'E' IL
						// RAPPRESENTANTE

						int statoImpresa = dettaglio.getIdStatoImpresaInput().intValue();
						String message = "";

						if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA)
							message = getMessage("accreditamentoImpresaUtenteEsistenteArisInnovativa", new Object[] { impresa.getImpresaTranslation().getDescImpresa() });
						else if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP)
							message = getMessage("accreditamentoImpresaUtenteEsistenteAris", new Object[] { impresa.getImpresaTranslation().getDescImpresa() });

						else
							message = getMessage("accreditamentoImpresaUtenteEsistenteArisAltro", new Object[] { impresa.getImpresaTranslation().getDescImpresa() });

						impresa = arisService.arisToImpresa(azienda);

						accreditamentoService.salvaRichiesta(codiceFiscaleUtente, dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput(), dettaglio.getEmailInput(),
								dettaglio.getIdStatoImpresaInput(), impresa, IAbstractServiceImpl.STATO_RICHIESTA_ATTESA, IAbstractServiceImpl.RICHIESTA_CONTROLLO_PRESENTE_ARIS,
								false);

						throw new AccreditamentoException(message, true, false);
					}
					else
					{
						// AZIENDA NON PRESENTE
						nonPresente(dettaglio, codiceFiscaleUtente);
					}
				}
			}
		}
		catch (AccreditamentoException err)
		{
			err.printStackTrace();
			throw err;
		}
		catch (Exception err)
		{
			err.printStackTrace();
			throw new AccreditamentoException(getMessage("accreditamentoErroreGenerico"));
		}

		// NESSUN CASO
		LoggerUtility.info("PASSO 2");
		throw new AccreditamentoException(getMessage("accreditamentoErroreGenerico"));
	}

	/**
	 * @param dettaglio
	 * @param codiceFiscaleUtente
	 * @throws AccreditamentoException
	 */
	private void nonPresente(RichiestaAccreditamento dettaglio, String codiceFiscaleUtente) throws AccreditamentoException
	{
		accreditamentoService.salvaRichiesta(codiceFiscaleUtente, dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput(), dettaglio.getEmailInput(),
				dettaglio.getIdStatoImpresaInput(), null, IAbstractServiceImpl.STATO_RICHIESTA_ATTESA, IAbstractServiceImpl.RICHIESTA_CONTROLLO_NON_PRESENTE, false);

		String param = "";
		if (dettaglio.getPartitaIvaInput() != null && dettaglio.getPartitaIvaInput().trim().length() > 0)
			param = dettaglio.getPartitaIvaInput();

		if (dettaglio.getCodiceFiscaleInput() != null && dettaglio.getCodiceFiscaleInput().trim().length() > 0)
		{
			if (param.length() > 0)
				param += " - ";
			param += dettaglio.getCodiceFiscaleInput();
		}
		throw new AccreditamentoException(getMessage("accreditamentoArisNonPresente", new Object[] { param }));
	}

	/**
	 * @param dettaglio
	 * @param impresa
	 * @param codiceFiscaleUtente
	 * @param request
	 * @throws AccreditamentoException
	 */
	private void accreditaAuto(RichiestaAccreditamento dettaglio, PLFImpresaEntity impresa, String codiceFiscaleUtente, HttpServletRequest request) throws AccreditamentoException
	{
		int statoImpresa = impresa.getPlfTStatoImpresa().getId().intValue();
		String message = "";

		if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA)
			message = getMessage("accreditamentoImpresaUtenteEsistenteInnovativa", new Object[] { impresa.getImpresaTranslation().getDescImpresa() });
		else if (statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_PMI || statoImpresa == IAbstractServiceImpl.STATO_IMPRESA_START_UP)
			message = getMessage("accreditamentoImpresaUtenteEsistente", new Object[] { impresa.getImpresaTranslation().getDescImpresa() });

		else
			message = getMessage("accreditamentoImpresaUtenteEsistenteAltro");

		accreditamentoService.salvaRichiesta(codiceFiscaleUtente, dettaglio.getCodiceFiscaleInput(), dettaglio.getPartitaIvaInput(), dettaglio.getEmailInput(),
				dettaglio.getIdStatoImpresaInput(), impresa, IAbstractServiceImpl.STATO_RICHIESTA_VALIDATA_AUTO, IAbstractServiceImpl.RICHIESTA_CONTROLLO_PRESENTE_VIMP, true);

		impresa.setDataAccreditamento(new Date());
		impresa = impresaService.updateImpresa(impresa);

		sendMailRichiestaAccreditamento(dettaglio.getEmailInput(), codiceFiscaleUtente, impresa);

		PLFTUtenteEntity utente = utenteService.accreditaUtente(codiceFiscaleUtente, dettaglio.getEmailInput(), impresa.getIdPlfImpresa(), impresa.getNomeLegaleRappresentante(),
				impresa.getCognomeLegaleRappresentante());
		WebUtils.setSessionAttribute(request, UtenteContext.USER_SESSION_KEY, new UtenteDto(utente));

		throw new AccreditamentoException(message, false, true);
	}

	// =====================================================================================================
	// =====================================================================================================
	// ARIS WS

	/**
	 * @param dettaglio
	 * @return
	 */
	private UlSearchResult getAzienda(RichiestaAccreditamento dettaglio)
	{
		String search = dettaglio.getPartitaIvaInput();
		if (search == null || search.trim().length() <= 0)
			search = dettaglio.getCodiceFiscaleInput();

		UlSearchResult ret = arisService.getUL(search);

		if ((ret.getC_fiscale_impresa() != null && ret.getC_fiscale_impresa().trim().length() > 0) || ret.getPartita_iva() != null && ret.getPartita_iva().trim().length() > 0)
			return ret;

		return null;
	}

	/**
	 * @param codiceFiscaleUtente
	 * @param impresa
	 * @return
	 */
	@SuppressWarnings("unused")
	private UlSearchResult getRappresentanteLegale(String codiceFiscaleUtente, PLFImpresaEntity impresa)
	{
		RLSearchResult rlSearchResult = arisService.getMultiRL(codiceFiscaleUtente);
		if (rlSearchResult != null && rlSearchResult.getResultRapprLegali() != null && rlSearchResult.getResultRapprLegali().length > 0)
		{
			String search = null;
			for (it.interlogic.vimp.service.ws.aris.rlmultisearch.ResultElement elem : rlSearchResult.getResultRapprLegali())
			{
				if (impresa.getCodFiscale() != null && impresa.getCodFiscale().equalsIgnoreCase(elem.getC_fiscale_impresa()))
				{
					search = elem.getC_fiscale_impresa();
					break;
				}
				else if (impresa.getPartitaIva() != null && impresa.getPartitaIva().equalsIgnoreCase(elem.getPartita_iva_impresa()))
				{
					search = elem.getPartita_iva_impresa();
					break;
				}
			}
			if (search != null)
			{
				LoggerUtility.info("ARIS-2 RAPPRESENTANTE LEGALE");
				return arisService.getUL(search);
			}
		}

		LoggerUtility.info("ARIS-2 NULL");
		return null;
	}

	private class ArisSearch
	{
		UlSearchResult arisAzienda;
		String cognomeRappresentanteLegale;
		String nomeRappresentanteLegale;
		boolean hasLegaleRappresentante = false;
	}

	/**
	 * @param codiceFiscaleUtente
	 * @param dettaglio
	 * @return
	 */
	private ArisSearch getAziendaERappresentanteLegale(String codiceFiscaleUtente, RichiestaAccreditamento dettaglio)
	{

		ArisSearch ret = new ArisSearch();

		String search = dettaglio.getPartitaIvaInput();
		if (search == null || search.trim().length() <= 0)
			search = dettaglio.getCodiceFiscaleInput();

		RLSearchResult rlSearchResult = arisService.getMultiRL(search);

		if (rlSearchResult != null && rlSearchResult.getResultRapprLegali() != null && rlSearchResult.getResultRapprLegali().length > 0)
		{
			for (it.interlogic.vimp.service.ws.aris.rlmultisearch.ResultElement elem : rlSearchResult.getResultRapprLegali())
			{
				if (codiceFiscaleUtente.equalsIgnoreCase(elem.getCodice_fiscale()))
				{
					search = elem.getC_fiscale_impresa();
					ret.cognomeRappresentanteLegale = elem.getCognome();
					ret.nomeRappresentanteLegale = elem.getNome();
					ret.hasLegaleRappresentante = true;
					break;
				}
			}

			UlSearchResult arisAzienda = arisService.getUL(search);
			if (arisAzienda != null)
			{
				ret.arisAzienda = arisAzienda;

				LoggerUtility.info("ARIS-1 AZIENDA");
				return ret;
			}

		}

		System.out.println("ARIS-1 NULL");
		return null;
	}

	// =====================================================================================================
	// =====================================================================================================
	// MAIL

	/**
	 * @param email
	 */
	private void sendMailRichiestaValidata(String email,boolean sendMailCambiaStato,int vecchioStato,int nuovoStato)
	{
		Map<String, String> mailData = new HashMap<String, String>();
		String from = AmbienteContext.getMailNoReplay();
		String to = email;
		String subject = "Vetrina imprese: richiesta accreditamento";
		String body = null;

		try
		{
			if (sendMailCambiaStato)
			{
				PLFTStatoImpresaEntity oldStateEntity = decodificheService.getStatoImpresa(new BigDecimal(vecchioStato));
				PLFTStatoImpresaEntity newStateEntity = decodificheService.getStatoImpresa(new BigDecimal(nuovoStato));
				mailData.put("nuovoStato", newStateEntity.getDescrizione());
				mailData.put("vecchioStato", oldStateEntity.getDescrizione());
				body = fillMailBody("MailValidaAccreditamentoCambioStato.txt", mailData);
			}
			else
				body = fillMailBody("MailValidaAccreditamento.txt", mailData);
			doSendEmail(mailSender,from, to, subject, body);
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}

	}

	/**
	 * @param email
	 * @param denominazioneImpresa
	 * @param parere
	 */
	private void sendMailRichiestaInvalidata(String email, String denominazioneImpresa, String parere, boolean sendMailCambiaStato,int vecchioStato,int nuovoStato)
	{
		Map<String, String> mailData = new HashMap<String, String>();
		if (denominazioneImpresa == null || denominazioneImpresa.trim().length() < 0)
			denominazioneImpresa = "<non specificata>";
		mailData.put("denominazioneImpresa", denominazioneImpresa);
		mailData.put("parere", parere);

		String from = AmbienteContext.getMailNoReplay();
		String to = email;
		String subject = "Vetrina imprese: nuova richiesta accreditamento";
		String body = null;

		try
		{
			if (sendMailCambiaStato)
			{
				PLFTStatoImpresaEntity oldStateEntity = decodificheService.getStatoImpresa(new BigDecimal(vecchioStato));
				PLFTStatoImpresaEntity newStateEntity = decodificheService.getStatoImpresa(new BigDecimal(nuovoStato));
				mailData.put("nuovoStato", newStateEntity.getDescrizione());
				mailData.put("vecchioStato", oldStateEntity.getDescrizione());
				body = fillMailBody("MailInvalidaAccreditamentoCambioStato.txt", mailData);
			}
			else body = fillMailBody("MailInvalidaAccreditamento.txt", mailData);
			doSendEmail(mailSender,from, to, subject, body);
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}

	}

	/**
	 * @param email
	 * @param codiceFiscaleUtente
	 * @param impresa
	 */
	private void sendMailRichiestaAccreditamento(String email, String codiceFiscaleUtente, PLFImpresaEntity impresa)
	{
		Map<String, String> mailData = new HashMap<String, String>();

		mailData.put("codiceFiscale", codiceFiscaleUtente);
		mailData.put("data", dateFormat.format(new Date()));
		mailData.put("ragioneSociale", impresa.getImpresaTranslation().getDescImpresa());
		if (impresa.getPartitaIva() != null)
			mailData.put("partitaIva", impresa.getPartitaIva().trim());
		else
			mailData.put("partitaIva", "");
		if (impresa.getCodFiscale() != null)
			mailData.put("codiceFiscale", impresa.getCodFiscale().trim());
		else
			mailData.put("codiceFiscale", "");

		if (impresa.getPlfTStatoImpresa() != null)
			mailData.put("stato", impresa.getPlfTStatoImpresa().getId().intValue() + " - " + impresa.getPlfTStatoImpresa().getDescrizione());
		else
			mailData.put("stato", "");

		if (impresa.getPlfTComune() != null)
			mailData.put("comune", impresa.getPlfTComune().getCodComune() + " - " + impresa.getPlfTComune().getDescComune());
		else
			mailData.put("comune", "");

		/*
		 * String from = AmbienteContext.getMailNoReplay(); String to = email;
		 */

		String from = AmbienteContext.getMailNoReplay();
		String to = AmbienteContext.getMailAssistenza();

		String subject = "Vetrina imprese: nuova richiesta accreditamento";
		String body = null;

		try
		{
			body = fillMailBody("MailRichiestaAccreditamento.txt", mailData);
			doSendEmail(mailSender,from, to, subject, body);
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}

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


}
