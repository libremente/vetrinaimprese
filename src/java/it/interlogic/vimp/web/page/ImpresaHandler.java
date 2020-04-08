package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAtecoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseAddettiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseCapitaleEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseProduzioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTNaturaGiuridicaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTOrigineImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTPrevalenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTProvinciaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRuoloAziendaleEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSezioneSpecialeImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoAllegatoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaStakeholderEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntity;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IDelegatoService;
import it.interlogic.vimp.service.IImpresaService;
import it.interlogic.vimp.service.INewsImpresaService;
import it.interlogic.vimp.service.IProgettoService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.service.ITagService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.EditableResult;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.UtenteContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImpresaHandler extends AbstractHandler
{

	private static final String VIEW_DETTAGLIO = "dettaglioImpresa";
	private static final String VIEW_NUOVA = "nuovaImpresa";

	@Autowired
	protected IImpresaService impresaService;

	@Autowired
	protected IServiziService serviziService;

	@Autowired
	protected INewsImpresaService newsImpresaService;

	@Autowired
	protected IProgettoService progettoService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	protected IDelegatoService delegatoService;

	@Autowired
	protected ITagService tagService;

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		DecimalFormat numberFormatter = new DecimalFormat("###,###,###,###,##0.00");
		binder.registerCustomEditor(BigDecimal.class, "numUltimoFatturato", new CustomNumberEditor(BigDecimal.class, numberFormatter, true));
	}

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "impresa";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/impresa/{numero}", method = RequestMethod.GET)
	public String detailImpresa(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("ImpresaHandler::detailImpresa: BEGIN");
		model.addAttribute("language", LocaleContextHolder.getLocale().getLanguage());
		model.addAttribute("region", LocaleContextHolder.getLocale().getCountry());

		try
		{
			return detail(parametri, numero, model, session, IAbstractServiceImpl.TIPO_INFO_IMPRESA);
		}
		finally
		{
			logger.debug("ImpresaHandler::detailImpresa: END");
		}
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/impresa/{numero}", method = RequestMethod.GET)
	public String detailSecureImpresa(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		model.addAttribute("language", LocaleContextHolder.getLocale().getLanguage());
		model.addAttribute("region", LocaleContextHolder.getLocale().getCountry());
		return detail(parametri, numero, model, session, IAbstractServiceImpl.TIPO_INFO_IMPRESA);
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/stakeholder/{numero}", method = RequestMethod.GET)
	public String detailStakeholder(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("ImpresaHandler::detailStakeholder: BEGIN");
		// model.addAttribute("refreshRelativeUrl", "/stakeholder/" + numero);
		try
		{
			return detail(parametri, numero, model, session, IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER);
		}
		finally
		{
			logger.debug("ImpresaHandler::detailStakeholder: END");
		}
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/stakeholder/{numero}", method = RequestMethod.GET)
	public String detailSecureStakeholder(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		return detail(parametri, numero, model, session, IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER);
	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/salvaImpresa", method = RequestMethod.POST)
	public String secureSave(HttpServletRequest request, HttpSession session, PLFImpresaEntity dettaglio, Model model)
	{
		logger.debug("ImpresaHandler::save: BEGIN");

		boolean aggiornamento = dettaglio != null && dettaglio.getIdPlfImpresa() != null && dettaglio.getIdPlfImpresa().intValue() > 0;

		if (aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/impresa/" + String.valueOf(dettaglio.getIdPlfImpresa()));
		}
		else
		{
			model.addAttribute("refreshRelativeUrl", "/impresa/0");
		}

		if (!controlloParametri(dettaglio))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model, dettaglio.getTipoInformazione());
			return VIEW_DETTAGLIO;
		}

		dettaglio = clearNullableReferences(dettaglio);

		try
		{
			impresaService.update(dettaglio);
			if (dettaglio.getTipoInformazione() == IAbstractServiceImpl.TIPO_INFO_IMPRESA)
				impresaService.updateImageImpresa(dettaglio.getIdPlfImpresa(), dettaglio.getImageData());
			else if (dettaglio.getTipoInformazione() == IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER)
				impresaService.updateImageStakeholder(dettaglio.getIdPlfImpresa(), dettaglio.getImageData());

		}
		catch (InformazioneDuplicataException err)
		{
			LoggerUtility.error(err.getMessage(), err);
			model.addAttribute("errorMessage", getMessage("erroreDuplicazioneImpresa"));
			loadModel(session, dettaglio, model, dettaglio.getTipoInformazione());
			return VIEW_DETTAGLIO;
		}
		catch (Exception err)
		{
			err.printStackTrace();
			model.addAttribute("errorMessage", getMessage("erroreSalvataggio"));
			loadModel(session, dettaglio, model, dettaglio.getTipoInformazione());
			return VIEW_DETTAGLIO;
		}
		finally
		{
			logger.debug("ImpresaHandler::save: END");
		}

		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			
			int tipoInformazione = IAbstractServiceImpl.TIPO_INFO_IMPRESA;
			if (dettaglio.getPlfTTipoImpresa() != null && dettaglio.getPlfTTipoImpresa().getId() != null
					&& dettaglio.getPlfTTipoImpresa().getId().intValue() == IAbstractServiceImpl.TIPO_IMPRESA_STAKEHOLDER)
				tipoInformazione = IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER;
			
			
			dettaglio = impresaService.find(dettaglio.getIdPlfImpresa());
			dettaglio.setTipoInformazione(tipoInformazione);
			loadModel(session, dettaglio, model, dettaglio.getTipoInformazione());
			return VIEW_DETTAGLIO;
		}
		else
		{

			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto"));
			if (dettaglio.getPlfTTipoImpresa() != null && dettaglio.getPlfTTipoImpresa().getId() != null
					&& dettaglio.getPlfTTipoImpresa().getId().intValue() == IAbstractServiceImpl.TIPO_IMPRESA_STAKEHOLDER)
				return "redirect:/secure/stakeholder/" + dettaglio.getIdPlfImpresa();
			else
				return "redirect:/secure/impresa/" + dettaglio.getIdPlfImpresa();
		}
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @param tipoInfo
	 * @return
	 */
	private String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session, int tipoInfo)
	{
		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;

		model.addAttribute("refreshRelativeUrl", "/impresa/" + numero);

		model.addAttribute("tipoInfo", tipoInfo);

		PLFImpresaEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = impresaService.find(new BigDecimal(numero));

		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFImpresaEntity();
		}

		loadModel(session, dettaglio, model, tipoInfo);

		parametri.setTipoInformazione(tipoInfo);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		return view;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean controlloParametri(PLFImpresaEntity dettaglio)
	{
		if (dettaglio.getImpresaTranslation().getDescImpresa() == null || dettaglio.getImpresaTranslation().getDescImpresa().trim().length() <= 0)
			return false;

		if ((dettaglio.getCodFiscale() == null || dettaglio.getCodFiscale().trim().length() <= 0)
				&& (dettaglio.getPartitaIva() == null || dettaglio.getPartitaIva().trim().length() <= 0))
			return false;

		if (dettaglio.getImpresaTranslation().getDescMissione() == null || dettaglio.getImpresaTranslation().getDescMissione().trim().length() <= 0)
			return false;

		if (dettaglio.getImpresaTranslation().getDescBreveImpresa() == null || dettaglio.getImpresaTranslation().getDescBreveImpresa().trim().length() <= 0)
			return false;

		if (dettaglio.getDescIndirizzo() == null || dettaglio.getDescIndirizzo().trim().length() <= 0)
			return false;

		if (dettaglio.getPlfTAteco() == null || dettaglio.getPlfTAteco().getIdAteco() == null || dettaglio.getPlfTAteco().getIdAteco().intValue() <= 0)
			return false;

		if (dettaglio.getPlfTSettoreImpresa() == null || dettaglio.getPlfTSettoreImpresa().getId() == null || dettaglio.getPlfTSettoreImpresa().getId().intValue() <= 0)
			return false;

		if (dettaglio.getPlfTComune() == null || dettaglio.getPlfTComune().getIdComune() == null || dettaglio.getPlfTComune().getIdComune().intValue() <= 0)
			return false;

		List<PLFTMercatiEntity> mercati = impresaService.loadMercati(dettaglio.getIdPlfImpresa());
		if (mercati == null || mercati.size() <= 0)
		{
			if (dettaglio.getImpresaTranslation().getMercatiAltro() == null || dettaglio.getImpresaTranslation().getMercatiAltro().trim().length() <= 0)
				return false;
		}

		if (dettaglio.getEmailContatto() == null || dettaglio.getEmailContatto().trim().length() <= 0)
			return false;

		return true;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private PLFImpresaEntity clearNullableReferences(PLFImpresaEntity dettaglio)
	{
		if (dettaglio.getPlfTNaturaGiuridica() != null && (dettaglio.getPlfTNaturaGiuridica().getId() == null || dettaglio.getPlfTNaturaGiuridica().getId().intValue() <= 0))
			dettaglio.setPlfTNaturaGiuridica(null);

		if (dettaglio.getPlfTClasseAddetti() != null && (dettaglio.getPlfTClasseAddetti().getId() == null || dettaglio.getPlfTClasseAddetti().getId().intValue() <= 0))
			dettaglio.setPlfTClasseAddetti(null);

		if (dettaglio.getPlfTClasseCapitale() != null && (dettaglio.getPlfTClasseCapitale().getId() == null || dettaglio.getPlfTClasseCapitale().getId().intValue() <= 0))
			dettaglio.setPlfTClasseCapitale(null);

		if (dettaglio.getPlfTClasseProduzione() != null && (dettaglio.getPlfTClasseProduzione().getId() == null || dettaglio.getPlfTClasseProduzione().getId().intValue() <= 0))
			dettaglio.setPlfTClasseProduzione(null);

		if (dettaglio.getPlfTTipoImpresa() != null && (dettaglio.getPlfTTipoImpresa().getId() == null || dettaglio.getPlfTTipoImpresa().getId().intValue() <= 0))
			dettaglio.setPlfTTipoImpresa(null);

		if (dettaglio.getPlfTStatoImpresa() != null && (dettaglio.getPlfTStatoImpresa().getId() == null || dettaglio.getPlfTStatoImpresa().getId().intValue() <= 0))
			dettaglio.setPlfTStatoImpresa(null);

		if (dettaglio.getPlfTRuoloAziendale() != null && (dettaglio.getPlfTRuoloAziendale().getId() == null || dettaglio.getPlfTRuoloAziendale().getId().intValue() <= 0))
			dettaglio.setPlfTRuoloAziendale(null);

		if (dettaglio.getPlfTOrigineImpresa() != null && (dettaglio.getPlfTOrigineImpresa().getId() == null || dettaglio.getPlfTOrigineImpresa().getId().intValue() <= 0))
			dettaglio.setPlfTOrigineImpresa(null);

		if (dettaglio.getPlfTSezioneSpecialeImpresa() != null
				&& (dettaglio.getPlfTSezioneSpecialeImpresa().getId() == null || dettaglio.getPlfTSezioneSpecialeImpresa().getId().intValue() <= 0))
			dettaglio.setPlfTSezioneSpecialeImpresa(null);

		if (dettaglio.getPlfTPrevalenzaFemminile() != null
				&& (dettaglio.getPlfTPrevalenzaFemminile().getId() == null || dettaglio.getPlfTPrevalenzaFemminile().getId().intValue() <= 0))
			dettaglio.setPlfTPrevalenzaFemminile(null);

		if (dettaglio.getPlfTPrevalenzaGiovanile() != null
				&& (dettaglio.getPlfTPrevalenzaGiovanile().getId() == null || dettaglio.getPlfTPrevalenzaGiovanile().getId().intValue() <= 0))
			dettaglio.setPlfTPrevalenzaGiovanile(null);

		if (dettaglio.getPlfTPrevalenzaStraniera() != null
				&& (dettaglio.getPlfTPrevalenzaStraniera().getId() == null || dettaglio.getPlfTPrevalenzaStraniera().getId().intValue() <= 0))
			dettaglio.setPlfTPrevalenzaStraniera(null);

		return dettaglio;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @param tipoInformazione
	 */
	private void loadModel(HttpSession session, PLFImpresaEntity dettaglio, Model model, int tipoInformazione)
	{
	
		if (dettaglio != null)
		{
			byte[] image = impresaService.getImage(dettaglio.getIdPlfImpresa());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));

			dettaglio.setPlfTMercatis(impresaService.loadMercati(dettaglio.getIdPlfImpresa()));
			dettaglio.setPlfTInnovaziones(impresaService.loadInnovazioni(dettaglio.getIdPlfImpresa()));

			if (tipoInformazione == IAbstractServiceImpl.TIPO_INFO_IMPRESA)
				dettaglio.setStakeholders(impresaService.loadStakeholder(dettaglio.getIdPlfImpresa()));
			else if (tipoInformazione == IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER)
				dettaglio.setImpreseCollegate(impresaService.loadImpreseCollegate(dettaglio.getIdPlfImpresa()));

			dettaglio.setServiziStandard(serviziService.loadServiziByImpresa(dettaglio.getIdPlfImpresa(), true));

			dettaglio.setPlfProgettiProdottis(progettoService.findByImpresaAttivi(dettaglio.getIdPlfImpresa()));

			dettaglio.setPlfNewsImpresas(newsImpresaService.findByImpresaAttive(dettaglio.getIdPlfImpresa()));

			dettaglio.setAllegati(impresaService.loadAllegati(dettaglio.getIdPlfImpresa()));

			dettaglio.setTipoInformazione(tipoInformazione);

		}

		if (checkModify(dettaglio, model))
			model.addAttribute("modifica", true);
		else
		{
			model.addAttribute("modifica", false);
		}

		model.addAttribute("dettaglio", dettaglio);

		if (tipoInformazione == IAbstractServiceImpl.TIPO_INFO_IMPRESA)
			model.addAttribute("stakeholderList",
					IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, impresaService.getStakeholders(), "idPlfImpresa", "descImpresa", null, false));
		else if (tipoInformazione == IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER)
			model.addAttribute("impreseList", IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, impresaService.getImprese(), "idPlfImpresa", "descImpresa", null, false));

		model.addAttribute("tipoImpresaList", IDecodificheServiceImpl.toMap(PLFTTipoImpresaEntity.class, decodificheService.getTipoImpresa(), "id", "descrizione", null, true));
		model.addAttribute("settoreImpresaList",
				IDecodificheServiceImpl.toMap(PLFTSettoreImpresaEntity.class, decodificheService.getSettoreImpresa(), "id", "descrizione", null, true));
		model.addAttribute("prevalenzaList", IDecodificheServiceImpl.toMap(PLFTPrevalenzaEntity.class, decodificheService.getPrevalenza(), "id", "codice", "descrizione", true));

		model.addAttribute("classeAddettiList",
				IDecodificheServiceImpl.toMap(PLFTClasseAddettiEntity.class, decodificheService.getClasseAddetti(), "id", "codice", "descrizione", true));
		model.addAttribute("classeCapitaleList",
				IDecodificheServiceImpl.toMap(PLFTClasseCapitaleEntity.class, decodificheService.getClasseCapitale(), "id", "codice", "descrizione", true));
		model.addAttribute("classeProduzioneList",
				IDecodificheServiceImpl.toMap(PLFTClasseProduzioneEntity.class, decodificheService.getClasseProduzione(), "id", "codice", "descrizione", true));

		model.addAttribute("ruoloAziendaleList",
				IDecodificheServiceImpl.toMap(PLFTRuoloAziendaleEntity.class, decodificheService.getRuoloAziendale(), "id", "descrizione", null, true));

		model.addAttribute("elementiInnovazioneList",
				IDecodificheServiceImpl.toMap(PLFTInnovazioneEntity.class, decodificheService.getInnovazione(), "id", "descrizione", null, true));

		model.addAttribute("mercatiRiferimentoList", IDecodificheServiceImpl.toMap(PLFTMercatiEntity.class, decodificheService.getMercati(), "id", "descrizione", null, true));

		model.addAttribute("naturaGiuridicaList",
				IDecodificheServiceImpl.toMap(PLFTNaturaGiuridicaEntity.class, decodificheService.getNaturaGiuridica(), "id", "descrizione", null, true));

		model.addAttribute("statoImpresaList", IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, true));

		model.addAttribute("atecoList", IDecodificheServiceImpl.toMap(PLFTAtecoEntity.class, decodificheService.getAteco(), "idAteco", "descrizione", null, true));

		model.addAttribute("serviziStandardList", IDecodificheServiceImpl.toMap(PLFServiziEntity.class, serviziService.find(), "idServizi", "denominazioneCalcolata", null, true));

		model.addAttribute("origineImpresaList",
				IDecodificheServiceImpl.toMap(PLFTOrigineImpresaEntity.class, decodificheService.getOrigineImpresa(), "id", "descrizione", null, true));

		model.addAttribute("sezioneSpecialeImpresaList",
				IDecodificheServiceImpl.toMap(PLFTSezioneSpecialeImpresaEntity.class, decodificheService.getSezioneSpecialeImpresa(), "id", "descrizione", null, true));

		model.addAttribute("provinciaList", IDecodificheServiceImpl.toMap(PLFTProvinciaEntity.class,
				decodificheService.getProvinciaByCodRegione(IAbstractServiceImpl.COD_REGIONE_LIGURIA), "codProvincia", "descProvincia", null, false));

		dettaglio.setCodProvincia(null);
		dettaglio.setProvincia(null);
		if (dettaglio != null && dettaglio.getPlfTComune() != null && dettaglio.getPlfTComune().getIdComune() != null && dettaglio.getPlfTComune().getIdComune().intValue() > 0)
		{
			PLFTComuneEntity comune = decodificheService.getComune(dettaglio.getPlfTComune().getIdComune());
			if (comune != null && comune.getProvincia() != null && comune.getProvincia().getCodProvincia() != null)
			{

				String codProvincia = comune.getProvincia().getCodProvincia();
				dettaglio.setCodProvincia(codProvincia);
				dettaglio.setProvincia(comune.getProvincia().getDescProvincia());

				model.addAttribute("comuneList",
						IDecodificheServiceImpl.toMap(PLFTComuneEntity.class, decodificheService.getComuneByCodProvincia(codProvincia), "idComune", "descComune", null, false));
			}
			else
				model.addAttribute("comuneList", new TreeMap<String, String>());
			
			if (IAbstractServiceImpl.COD_COMUNE_GENOVA.equalsIgnoreCase(comune.getCodiceIstat()))
			{
				dettaglio.setNumeroCivicoTopo(dettaglio.getNumeroCivico());
				dettaglio.setIndirizzoTopo(dettaglio.getDescIndirizzo());
				
				if (dettaglio.getDescIndirizzo() != null && dettaglio.getDescIndirizzo().trim().length()>0)
				{
					Map<String, String> strada = new TreeMap<String, String>();
					strada.put(dettaglio.getDescIndirizzo(), dettaglio.getDescIndirizzo());
					model.addAttribute("indirizzoTopolist", strada);
				}
				
				if (dettaglio.getNumeroCivico() != null && dettaglio.getNumeroCivico().trim().length()>0)
				{
					Map<String, String> civico = new TreeMap<String, String>();
					civico.put(dettaglio.getNumeroCivico(), dettaglio.getNumeroCivico());
					model.addAttribute("numeroCivicoTopolist", civico);
				}
			}
		}
		else
			model.addAttribute("comuneList", new TreeMap<String, String>());
		
		
		PLFTComuneEntity comuneGenova =  decodificheService.getComuneByCodiceIstat(IAbstractServiceImpl.COD_COMUNE_GENOVA);
		if (comuneGenova != null && comuneGenova.getIdComune() != null)
			model.addAttribute("idComuneGenova", ""+ comuneGenova.getIdComune().intValue());

		// tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if (dettaglio.getIdPlfImpresa() != null)
		{

			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdPlfImpresa(), BigDecimal.valueOf(tipoInformazione));

			if (tags != null && tags.size() > 0)
			{
				dettaglio.setTags(tags);
				List<BigDecimal> ids = new ArrayList<BigDecimal>();
				for (PLFTTagEntity tag : tags)
				{
					ids.add(tag.getId());
				}
				dettaglio.setElencoIdTags(ids);
			}

		}

		model.addAttribute("allTags", allTags);

		modelTags(dettaglio, model);

	}

	/**
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	private boolean checkModify(PLFImpresaEntity dettaglio, Model model)
	{
		if (UtenteContext.getCurrentUser().isBackoffice())
		{
			if (dettaglio != null && dettaglio.getDataAccreditamento() != null)
			{
				model.addAttribute("warningMessage", getMessage("messaggioImpresaNonEditabile"));
				return false;
			}
			return true;
		}

		if (dettaglio.getTipoInformazione() == IAbstractServiceImpl.TIPO_INFO_IMPRESA)
		{
			PLFVDelegatoEntity delega = delegatoService.findDelegato(UtenteContext.getCurrentUser().getIdUtente(), dettaglio.getIdPlfImpresa());
			return delega != null;
		}

		if (dettaglio.getTipoInformazione() == IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER)
		{
			if (UtenteContext.getCurrentUser().isStakeholder() && UtenteContext.getCurrentUser().getPlfImpresas() != null
					&& UtenteContext.getCurrentUser().getPlfImpresas().size() > 0)
			{
				BigDecimal idStakeholder = UtenteContext.getCurrentUser().getPlfImpresas().get(0).getIdPlfImpresa();
				if (idStakeholder != null && idStakeholder.intValue() > 0 && dettaglio != null && dettaglio.getIdPlfImpresa() != null
						&& idStakeholder.intValue() == dettaglio.getIdPlfImpresa().intValue())
					return true;
			}
		}

		return false;
	}

	// ===================================================
	// ELEMENTI INNOVAZIONE

	/**
	 * @param idImpresa
	 * @param idElementoInnovazione
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaElementiInnovazione", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaElementiInnovazione(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idElementoInnovazione", required = true) String idElementoInnovazione)
	{
		try
		{
			PLFRImpresaInnovazioneEntity impresaInnovazione = new PLFRImpresaInnovazioneEntity();
			impresaInnovazione.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaInnovazione.setIdInnovazione(BigDecimal.valueOf(Long.valueOf(idElementoInnovazione)));

			impresaService.salvaCollegamentoImpresaInnovazione(impresaInnovazione);

			PLFTInnovazioneEntity innovazione = decodificheService.getInnovazione(impresaInnovazione.getIdInnovazione());
			EditableResult res = EditableResult.getInstance("" + innovazione.getId().intValue(), innovazione.getDescrizione());
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento impresa elementi innovazione fallito");
			throw new RuntimeException("Inserimento elementi innovazione fallito.");
		}
		finally
		{
			logger.debug("Inserimento colleagmento impresa elementi innovazione");
		}
	}

	/**
	 * @param idImpresa
	 * @param idElementoInnovazione
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoElementiInnovazione", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoElementiInnovazione(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idElementoInnovazione", required = true) String idElementoInnovazione)
	{

		try
		{
			PLFRImpresaInnovazioneEntity impresaInnovazione = new PLFRImpresaInnovazioneEntity();
			impresaInnovazione.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaInnovazione.setIdInnovazione(BigDecimal.valueOf(Long.valueOf(idElementoInnovazione)));

			impresaService.cancellaCollegamentoImpresaInnovazione(impresaInnovazione);
			return EditableResult.getInstance("idElementoInnovazione", idElementoInnovazione);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione impresa elementi di innovazione");
			throw new RuntimeException("Cancellazione impresa elementi di innovazione.");
		}
		finally
		{
			logger.debug("Cancellazione impresa elementi di innovazione " + idElementoInnovazione);
		}

	}

	// ===================================================
	// MERCATI RIFERIMENTO

	/**
	 * @param idImpresa
	 * @param idMercatoRiferimento
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaMercatiRiferimento", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaMercatiRiferimento(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idMercatoRiferimento", required = true) String idMercatoRiferimento)
	{
		try
		{
			PLFRImpresaMercatiEntity impresaMercati = new PLFRImpresaMercatiEntity();
			impresaMercati.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaMercati.setIdMercato(BigDecimal.valueOf(Long.valueOf(idMercatoRiferimento)));

			impresaService.salvaCollegamentoImpresaMercati(impresaMercati);

			PLFTMercatiEntity mercato = decodificheService.getMercati(impresaMercati.getIdMercato());
			EditableResult res = EditableResult.getInstance("" + mercato.getId().intValue(), mercato.getDescrizione());
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento impresa mercati riferimento fallito");
			throw new RuntimeException("Inserimento impresa mercati riferimento fallito.");
		}
		finally
		{
			logger.debug("Inserimento colleagmento impresa mercati di riferimento");
		}
	}

	/**
	 * @param idImpresa
	 * @param idMercatiRiferimento
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoMercatiRiferimento", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoMercatiRiferimento(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idMercatiRiferimento", required = true) String idMercatiRiferimento)
	{

		try
		{
			PLFRImpresaMercatiEntity impresaMercati = new PLFRImpresaMercatiEntity();
			impresaMercati.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaMercati.setIdMercato(BigDecimal.valueOf(Long.valueOf(idMercatiRiferimento)));

			impresaService.cancellaCollegamentoImpresaMercati(impresaMercati);
			return EditableResult.getInstance("idMercatiRiferimento", idMercatiRiferimento);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione impresa mercati di riferimento");
			throw new RuntimeException("Cancellazione impresa mercati di riferimento.");
		}
		finally
		{
			logger.debug("Cancellazione impresa mercati di riferimento" + idMercatiRiferimento);
		}

	}

	// ===================================================
	// STAKEHOLDER

	/**
	 * @param idImpresa
	 * @param idStakeholder
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaStakeholder", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaStakeholder(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idStakeholder", required = true) String idStakeholder)
	{
		try
		{
			PLFRImpresaStakeholderEntity impresaStakeholder = new PLFRImpresaStakeholderEntity();
			impresaStakeholder.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaStakeholder.setIdStakeholder(BigDecimal.valueOf(Long.valueOf(idStakeholder)));

			impresaService.salvaCollegamentoImpresaStakeholder(impresaStakeholder);

			PLFImpresaEntity stakeholder = impresaService.find(BigDecimal.valueOf(Long.valueOf(idStakeholder)));
			EditableResult res = EditableResult.getInstance("" + stakeholder.getIdPlfImpresa().intValue(), stakeholder.getImpresaTranslation().getDescImpresa());
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento impresa stakeholder fallito");
			throw new RuntimeException("Inserimento impresa stakeholder fallito.");
		}
		finally
		{
			logger.debug("Inserimento colleagmento impresa stakeholder");
		}
	}

	/**
	 * @param idImpresa
	 * @param idStakeholder
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoStakeholder", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoStakeholder(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idStakeholder", required = true) String idStakeholder)
	{

		try
		{
			PLFRImpresaStakeholderEntity impresaStakeholder = new PLFRImpresaStakeholderEntity();
			impresaStakeholder.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaStakeholder.setIdStakeholder(BigDecimal.valueOf(Long.valueOf(idStakeholder)));

			impresaService.cancellaCollegamentoImpresaStakeholder(impresaStakeholder);
			return EditableResult.getInstance("idStakeholder", idStakeholder);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione impresa stakeholder");
			throw new RuntimeException("Cancellazione impresa stakeholder.");
		}
		finally
		{
			logger.debug("Cancellazione impresa stakeholder " + idStakeholder);
		}

	}

	// ===================================================
	// SERVIZI STANDARD

	/**
	 * @param idImpresa
	 * @param idServizi
	 * @param linkCollegamentoImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaServiziStandard", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaServiziStandard(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idServizi", required = true) String idServizi, @RequestParam(value = "linkCollegamentoImpresa", required = false) String linkCollegamentoImpresa)
	{
		// FIXME REF_SIMP
		try
		{
			PLFRServiziImpresaEntity impresaServizi = new PLFRServiziImpresaEntity();
			impresaServizi.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaServizi.setIdServizi(BigDecimal.valueOf(Long.valueOf(idServizi)));

			impresaServizi.setLinkCollegamentoImpresa(linkCollegamentoImpresa);
			serviziService.salvaCollegamentoImpresaServiziStandard(impresaServizi);

			PLFServiziEntity servizio = serviziService.loadServizioStandardForImpresa(impresaServizi.getIdServizi(), impresaServizi.getIdImpresa());
			EditableResult res = EditableResult.getInstance("" + servizio.getIdServizi().intValue(), servizio.getServiziTranslation().getDescrizione(),
					new String[] { servizio.getLinkCollegamentoImpresa() });
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento impresa servizi standard fallito");
			throw new RuntimeException("Inserimento impresa servizi standard fallito.");
		}
		finally
		{
			logger.debug("Inserimento colleagmento impresa servizi standard");
		}
	}

	/**
	 * @param idImpresa
	 * @param idServiziStandard
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoServiziStandard", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoServiziStandard(@RequestParam(value = "idImpresa", required = true) String idImpresa,
			@RequestParam(value = "idServiziStandard", required = true) String idServiziStandard)
	{

		try
		{
			PLFRServiziImpresaEntity impresaServiziStandard = new PLFRServiziImpresaEntity();
			impresaServiziStandard.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaServiziStandard.setIdServizi(BigDecimal.valueOf(Long.valueOf(idServiziStandard)));

			serviziService.cancellaCollegamentoImpresaServiziStandard(impresaServiziStandard);
			return EditableResult.getInstance("idServiziStandard", idServiziStandard);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione impresa servizi standard");
			throw new RuntimeException("Cancellazione impresa servizi standard.");
		}
		finally
		{
			logger.debug("Cancellazione impresa servizi standard " + idServiziStandard);
		}

	}

	// ===================================================
	// SERVIZI STANDARD

	/**
	 * @param idAllegato
	 * @param response
	 */
	@RequestMapping(value = "/impresaAllegato/{idAllegato}", method = RequestMethod.GET)
	public void getFile(@PathVariable("idAllegato") String idAllegato, HttpServletResponse response)
	{
		getFileSecure(idAllegato, response);
	}

	/**
	 * @param idAllegato
	 * @param response
	 */
	@RequestMapping(value = "/secure/impresaAllegato/{idAllegato}", method = RequestMethod.GET)
	public void getFileSecure(@PathVariable("idAllegato") String idAllegato, HttpServletResponse response)
	{
		try
		{
			PLFImpresaAllegatiEntity allegato = impresaService.findAllegato(BigDecimal.valueOf(Long.valueOf(idAllegato)));

			InputStream is = new ByteArrayInputStream(allegato.getAllegato());

			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());

			if (allegato.getContentType() != null && allegato.getContentType().trim().length() > 0)
				response.setContentType(allegato.getContentType());

			String fileName = allegato.getFileName();
			// fileName = fileName.replaceAll(" ", "");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

			response.flushBuffer();
		}
		catch (IOException ex)
		{
			logger.debug("Scarico allegato impresa fallita");
			throw new RuntimeException("Scarico allegato impresa fallita.");
		}

	}

	/**
	 * @param idAllegato
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaImpresaAllegato", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaImpresaAllegatoSecure(@RequestParam(value = "idAllegato", required = true) String idAllegato)
	{

		try
		{
			PLFImpresaAllegatiEntity allegato = new PLFImpresaAllegatiEntity();
			allegato.setIdImpresaAllegati(BigDecimal.valueOf(Long.valueOf(idAllegato)));
			impresaService.cancellaAllegato(allegato);
			return EditableResult.getInstance("idProgettoRicerca", idAllegato + "");
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione allegato impresa fallita");
			throw new RuntimeException("Cancellazione impresa fallita.");
		}
		finally
		{
			logger.debug("Cancellazione allegato impresa " + idAllegato);
		}

	}

	/**
	 * @param idPlfImpresa
	 * @param file
	 * @param descrizione
	 * @return
	 */
	@RequestMapping(value = "/secure/allegaFileImpresa", method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = { "application/json" })
	@ResponseBody
	public EditableResult allegaFileImpresaSecure(@RequestParam(value = "idPlfImpresa", required = true) BigDecimal idPlfImpresa, @RequestPart("allegato") MultipartFile file,
			@RequestParam(value = "descrizione", required = false) String descrizione)
	{
		try
		{

			if (file.getBytes().length > 20971520)
			{
				logger.debug("Upload allegato impresa troppo grande");
				throw new RuntimeException("Upload allegato impresa troppo grande.");
			}

			PLFImpresaAllegatiEntity e = new PLFImpresaAllegatiEntity();
			PLFImpresaAllegatiTranslationEntity translation = new PLFImpresaAllegatiTranslationEntity();
			translation.setDescrizione(descrizione);
			e.setImpresaAllegatiTranslation(translation);
			e.setIdPlfImpresa(idPlfImpresa);
			e.setFileName(file.getOriginalFilename());
			e.setAllegato(file.getBytes());
			e.setContentType(file.getContentType());
			e.setPathName(file.getOriginalFilename());
			e.setNome(FilenameUtils.removeExtension(file.getOriginalFilename()));

			PLFTTipoAllegatoEntity tipo = new PLFTTipoAllegatoEntity();
			tipo.setId(new BigDecimal(IAbstractServiceImpl.TIPO_ALLEGATO_GENERICO));
			e.setPlfTTipoAllegato(tipo);

			e = impresaService.salvaAllegato(e);

			EditableResult res = EditableResult.getInstance("" + e.getIdImpresaAllegati().intValue(), e.getNome(), new String[] { descrizione });

			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.debug("Upload allegato impresa fallito");
			throw new RuntimeException("Upload allegato impresa fallito.");
		}
		finally
		{
			logger.debug("Allegato impresa " + idPlfImpresa);
		}

	}

	// ===================================================
	// IMPRESE COLLEGATE

	/**
	 * @param idStakeholder
	 * @param idImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaImpresa", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaImpresa(@RequestParam(value = "idStakeholder", required = true) String idStakeholder,
			@RequestParam(value = "idImpresa", required = true) String idImpresa)
	{
		try
		{
			PLFRImpresaStakeholderEntity impresaStakeholder = new PLFRImpresaStakeholderEntity();
			impresaStakeholder.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaStakeholder.setIdStakeholder(BigDecimal.valueOf(Long.valueOf(idStakeholder)));

			impresaService.salvaCollegamentoImpresaStakeholder(impresaStakeholder);

			PLFImpresaEntity stakeholder = impresaService.find(impresaStakeholder.getIdImpresa());
			EditableResult res = EditableResult.getInstance("" + stakeholder.getIdPlfImpresa().intValue(), stakeholder.getImpresaTranslation().getDescImpresa());
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento impresa stakeholder fallito");
			throw new RuntimeException("Inserimento impresa stakeholder fallito.");
		}
		finally
		{
			logger.debug("Inserimento colleagmento impresa stakeholder");
		}
	}

	/**
	 * @param idStakeholder
	 * @param idImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoImpresa", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoImpresa(@RequestParam(value = "idStakeholder", required = true) String idStakeholder,
			@RequestParam(value = "idImpresa", required = true) String idImpresa)
	{

		try
		{
			PLFRImpresaStakeholderEntity impresaStakeholder = new PLFRImpresaStakeholderEntity();
			impresaStakeholder.setIdImpresa(BigDecimal.valueOf(Long.valueOf(idImpresa)));
			impresaStakeholder.setIdStakeholder(BigDecimal.valueOf(Long.valueOf(idStakeholder)));

			impresaService.cancellaCollegamentoImpresaStakeholder(impresaStakeholder);
			return EditableResult.getInstance("idStakeholder", idStakeholder);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione impresa stakeholder");
			throw new RuntimeException("Cancellazione impresa stakeholder.");
		}
		finally
		{
			logger.debug("Cancellazione impresa stakeholder " + idStakeholder);
		}

	}

	/**
	 * @param impresaAllegato
	 * @return
	 */
	@RequestMapping(value = "/secure/updateImpresaAllegatoDescription", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public PLFImpresaAllegatiTranslationEntity updateAllegatoDescription(PLFImpresaAllegatiTranslationEntity impresaAllegato)
	{

		try
		{
			return impresaService.salvaTranslationAllegato(impresaAllegato);
		}
		catch (Exception e)
		{
			logger.debug("Aggiornamento allegato impresa fallito");
			throw new RuntimeException("Aggiornamento allegato impresa fallito");
		}
		finally
		{
			logger.debug("Aggiornamento allegato impresa " + impresaAllegato.getIdImpresaAllegati());
		}

	}

}
