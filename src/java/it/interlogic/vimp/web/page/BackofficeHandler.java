package it.interlogic.vimp.web.page;

import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTProvinciaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRuoloEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFVImpresaEntity;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.service.ConfigurazioneAmbiente;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IRicercaService;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.utils.UtilityStringhe;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.Colonna;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.dto.ParametriRicercaImpresa;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackofficeHandler extends AbstractHandler
{

	public static final String VIEW_RICERCA_IMPRESA = "ricercaImpresa";
	public static final String VIEW_BATCH_LOG = "batchlog";
	public static final String VIEW_TRANSLATE_CODIFICHE = "translateCodifiche";
	public static final String VIEW_TRANSLATE_ATECO = "translateAteco";
	public static final String VIEW_GESTIONE_RUOLI = "gestioneRuoli";

	private static final String COLONNE_SESSION_KEY = "COLONNE_SESSION_KEY";

	@Autowired
	protected IRicercaService ricercaService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	ConfigurazioneAmbiente ca;

	// ==========================================================================

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/ricercaImpresa")
	public String ricercaImpresa(HttpServletRequest request, ParametriRicercaImpresa parametri, Model model, HttpSession session)
	{
		logger.debug("BackofficeHandler::ricercaImpresa: BEGIN");
		prepareModelToSearchImpresa(request, parametri, model, session);
		logger.debug("BackofficeHandler::ricercaImpresa: END");
		return VIEW_RICERCA_IMPRESA;
	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/batchlog")
	public String batchlog(HttpServletRequest request, ParametriRicercaImpresa parametri, Model model, HttpSession session)
	{
		logger.debug("BackofficeHandler::batchlog: BEGIN");

		if (parametri == null)
			parametri = new ParametriRicercaImpresa();

		int numeroPagina = parametri.getValoreNumeroPagina() - 1;
		model.addAttribute("parametriRicerca", parametri);

		Page<PLFLogEntity> risultato = ricercaService.elencoBatchLog(numeroPagina, parametri.getValoreNumeroRecord());
		List<PLFLogEntity> lista = risultato.getContent();
		ArrayList<Map<String, Object>> elenco = new ArrayList<Map<String, Object>>();
		for (PLFLogEntity entity : lista)
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> oggettoMappa = new org.apache.commons.beanutils.BeanMap(entity);
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

		logger.debug("BackofficeHandler::batchlog: END");
		return VIEW_BATCH_LOG;
	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/translateCodifiche")
	public String translateCodifiche(HttpServletRequest request, ParametriRicerca parametri, Model model, HttpSession session)
	{

		logger.debug("BackofficeHandler::translateCodifiche: BEGIN");

		String defaultLocale = I18nConstants.DEFAULT_LANGUAGE_CODE;

		// trasformo i locale in upperCase
		Set<String> langs = new HashSet<String>();
		for (String l : ca.getSelectableLanguages().keySet())
		{
			langs.add(l.toUpperCase());
		}

		if (parametri == null)
		{
			parametri = new ParametriRicerca();
		}

		List<Map<String, String>> showTables = new ArrayList<Map<String, String>>();

		String[] tabelleCodifiche = I18nConstants.CODIFICA_TABLES;
		if (UtenteContext.getCurrentUser().isImpresa())
			tabelleCodifiche = I18nConstants.CODIFICA_TABLES_IMPRESE;
		else if (UtenteContext.getCurrentUser().isStakeholder())
			tabelleCodifiche = I18nConstants.CODIFICA_TABLES_STAKEHOLDER;

		String[] fieldCodes = I18nConstants.CODE_FIELD;

		for (String t : tabelleCodifiche)
		{
			Map<String, String> singleTable = new HashMap<String, String>();
			singleTable.put("originalName", t);
			singleTable.put("showName", t.replace("PLF_T_", "").replace("_", " "));
			singleTable.put("isFieldCodeVisible", isFieldCodeVisible(fieldCodes, t));
			showTables.add(singleTable);
		}
		model.addAttribute("tables", showTables);
		model.addAttribute("languages", langs);
		model.addAttribute("defaultLocale", defaultLocale.toUpperCase());

		String selectedLocale = LocaleContextHolder.getLocale().getLanguage().toUpperCase() + "_" + LocaleContextHolder.getLocale().getCountry();

		model.addAttribute("selectedLocale", selectedLocale);

		logger.debug("BackofficeHandler::translateCodifiche: END");
		return VIEW_TRANSLATE_CODIFICHE;
	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/translateAteco")
	public String translateAteco(HttpServletRequest request, ParametriRicerca parametri, Model model, HttpSession session)
	{
		logger.debug("BackofficeHandler::translateAteco: BEGIN");

		String defaultLocale = I18nConstants.DEFAULT_LANGUAGE_CODE;

		// trasformo i locale in upperCase
		Set<String> langs = new HashSet<String>();
		for (String l : ca.getSelectableLanguages().keySet())
		{
			langs.add(l.toUpperCase());
		}

		model.addAttribute("tableName", "PLF_T_ATECO");
		model.addAttribute("languages", langs);
		model.addAttribute("defaultLocale", defaultLocale.toUpperCase());

		String selectedLocale = LocaleContextHolder.getLocale().getLanguage().toUpperCase() + "_" + LocaleContextHolder.getLocale().getCountry();

		model.addAttribute("selectedLocale", selectedLocale);

		logger.debug("BackofficeHandler::translateCodifiche: END");
		return VIEW_TRANSLATE_ATECO;
	}

	/**
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/gestioneRuoli")
	public String gestioneRuoli(HttpServletRequest request, Model model, HttpSession session)
	{

		logger.debug("BackofficeHandler::gestioneRuoli: BEGIN");

		List<PLFTRuoloEntity> roles = decodificheService.getRuolo();

		model.addAttribute("roles", roles);

		logger.debug("BackofficeHandler::gestioneRuoli: END");

		return VIEW_GESTIONE_RUOLI;
	}

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return null;
	}

	/**
	 * @param request
	 * @param parametri
	 * @param model
	 * @param session
	 * @return
	 */
	public Model prepareModelToSearchImpresa(HttpServletRequest request, ParametriRicercaImpresa parametri, Model model, HttpSession session)
	{
		if (parametri == null)
			parametri = new ParametriRicercaImpresa();

		int numeroPagina = parametri.getValoreNumeroPagina() - 1;

		parametri.setColonne(getElencoColonneImpresa(session, parametri.getColonneResult()));
		model.addAttribute("parametriRicerca", parametri);

		CriteriQuery filtri = estraiFiltroDiRicercaImpresa(parametri);
		Page<PLFVImpresaEntity> risultato = ricercaService.elencoImprese(numeroPagina, parametri.getValoreNumeroRecord(), filtri);
		List<PLFVImpresaEntity> lista = risultato.getContent();
		ArrayList<Map<String, Object>> elenco = new ArrayList<Map<String, Object>>();
		for (PLFVImpresaEntity plfVImpresaEntity : lista)
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> oggettoMappa = new org.apache.commons.beanutils.BeanMap(plfVImpresaEntity);
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

		// DECODIFICHE
		model.addAttribute("statoImpresaList", IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, false));
		model.addAttribute("settoreImpresaList",
				IDecodificheServiceImpl.toMap(PLFTSettoreImpresaEntity.class, decodificheService.getSettoreImpresa(), "id", "descrizione", null, false));
		model.addAttribute("innovazioneList", IDecodificheServiceImpl.toMap(PLFTInnovazioneEntity.class, decodificheService.getInnovazione(), "id", "descrizione", null, false));
		model.addAttribute("mercatiList", IDecodificheServiceImpl.toMap(PLFTMercatiEntity.class, decodificheService.getMercati(), "id", "descrizione", null, false));
		model.addAttribute("stakeholderList",
				IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, decodificheService.getStakeholder(), "idPlfImpresa", "descImpresa", null, false));

		model.addAttribute("provinciaList", IDecodificheServiceImpl.toMap(PLFTProvinciaEntity.class,
				decodificheService.getProvinciaByCodRegione(IAbstractServiceImpl.COD_REGIONE_LIGURIA), "codProvincia", "descProvincia", null, false));
		if (StringUtils.isNotBlank(parametri.getProvincia()))
		{
			String codProvincia = parametri.getProvincia().trim();
			model.addAttribute("comuneList",
					IDecodificheServiceImpl.toMap(PLFTComuneEntity.class, decodificheService.getComuneByCodProvincia(codProvincia), "idComune", "descComune", null, false));
		}
		else
			model.addAttribute("comuneList", new TreeMap<String, String>());

		return model;
	}

	/**
	 * @param session
	 * @param colonneResult
	 * @return
	 */
	protected List<Colonna> getElencoColonneImpresa(HttpSession session, List<String> colonneResult)
	{
		try
		{
			@SuppressWarnings("unchecked")
			List<Colonna> colonneSession = (List<Colonna>) session.getAttribute(COLONNE_SESSION_KEY);
			if (colonneSession == null)
			{

				colonneSession = new ArrayList<Colonna>();
				colonneSession.add(Colonna.getInstance("ragioneSocialeColumn", "ragioneSociale", true, true));
				colonneSession.add(Colonna.getInstance("codiceFiscaleColumn", "codiceFiscale", false, false));
				colonneSession.add(Colonna.getInstance("partitaIvaColumn", "partitaIva", false, false));
				colonneSession.add(Colonna.getInstance("areaColumn", "settore", true, true));
				colonneSession.add(Colonna.getInstance("districtColumn", "provincia", true, true));
				colonneSession.add(Colonna.getInstance("cityColumn", "comune", true, true));
				colonneSession.add(Colonna.getInstance("descStatoImpresaColumn", "statoImpresa", true, true));

				session.setAttribute(COLONNE_SESSION_KEY, colonneSession);
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

			session.setAttribute(COLONNE_SESSION_KEY, colonneSession);
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
	protected CriteriQuery estraiFiltroDiRicercaImpresa(ParametriRicercaImpresa parametri)
	{
		CriteriQuery cq = new CriteriQuery();
		if (parametri != null)
		{
			cq.addParametroLike("ragioneSociale", toString(parametri.getRagioneSociale()));
			cq.addParametroEqual("idSettore", toNumber(parametri.getSettore()));

			if (parametri.getProvincia() != null && parametri.getProvincia().trim().length() > 0)
				cq.addParametroEqual("idProvincia", parametri.getProvincia().trim());
			cq.addParametroEqual("idComune", toNumber(parametri.getComune()));
			cq.addParametroEqual("idStatoImpresa", toNumber(parametri.getStatoImpresa()));

			if (parametri.getBrevetti() != null && parametri.getBrevetti().trim().length() > 0)
				cq.addParametroEqual("brevetto", parametri.getBrevetti().trim());
			if (parametri.getIscrizioneRegistro() != null && parametri.getIscrizioneRegistro().trim().length() > 0)
				cq.addParametroEqual("iscrizioneRegistro", parametri.getIscrizioneRegistro().trim());
			if (parametri.getIscrizioneSpeciale() != null && parametri.getIscrizioneSpeciale().trim().length() > 0)
				cq.addParametroEqual("iscrizioneSpeciale", parametri.getIscrizioneSpeciale().trim());

			if (parametri.getStakeholder() != null && parametri.getStakeholder().trim().length() > 0)
				cq.addParametroLike("stakeholder", toString(parametri.getStakeholder()));
			if (parametri.getElementiInnovazione() != null && parametri.getElementiInnovazione().trim().length() > 0)
				cq.addParametroLike("innovazione", toString(parametri.getElementiInnovazione()));
			if (parametri.getMercatiRiferimento() != null && parametri.getMercatiRiferimento().trim().length() > 0)
				cq.addParametroLike("mercati", toString(parametri.getMercatiRiferimento()));

		}
		return cq;
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

	/**
	 * @param valore
	 * @return
	 */
	protected BigDecimal toNumber(String valore)
	{
		if (StringUtils.isEmpty(valore))
			return null;
		try
		{
			long longValue = Long.parseLong(valore);
			return BigDecimal.valueOf(longValue);
		}
		catch (Exception e)
		{
			logger.error("Errore di conversione di un parametro numerico");
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param fieldCodes
	 * @param table
	 * @return
	 */
	private String isFieldCodeVisible(String[] fieldCodes, String table)
	{
		for (String field : fieldCodes)
		{
			if (field.equalsIgnoreCase(table))
			{
				return "TRUE";
			}
		}
		return "FALSE";
	}

}
