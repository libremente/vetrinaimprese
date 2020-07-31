package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.dao.DecodificaRestHelper;
import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTNazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoProgettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoAllegatoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipologiaProgettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
import it.interlogic.vimp.service.*;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.CollaborazioneResult;
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
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProgettoHandler extends AbstractHandler
{

	private static final String VIEW_DETTAGLIO = "dettaglioProgetto";
	private static final String VIEW_NUOVA = "nuovoProgetto";

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	protected IProgettoService progettoService;
	
	@Autowired
	protected INewsImpresaService newsImpresaService;

	@Autowired
	protected IImpresaService impresaService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	protected IDelegatoService delegatoService;

	@Autowired DecodificaRestHelper decodificaRestHelper;

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

		DecimalFormat numberFormatter = new DecimalFormat("###,###,##0");
		binder.registerCustomEditor(BigDecimal.class, "numValoreEconomico", new CustomNumberEditor(BigDecimal.class, numberFormatter, true));
	}

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "progetto";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/progetto/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("IProgettoService::detail: BEGIN");

		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;
		
		model.addAttribute("refreshRelativeUrl", "/progetto/" + numero);
		//model.addAttribute("refreshRelativeUrl", "/progetto/0"); // Questo redirigerà sempre al form per nuovi inserimenti

		PLFProgettiProdottiEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = progettoService.find(new BigDecimal(numero));

		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFProgettiProdottiEntity();
		}

		loadModel(session, dettaglio, model);

		parametri.setTipoInformazione(IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		logger.debug("IProgettoService::detail: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/progetto/{numero}", method = RequestMethod.GET)
	public String detailSecure(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		return detail(parametri, numero, model, session);
	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/salvaProgetto", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, @ModelAttribute PLFProgettiProdottiEntity dettaglio, Model model)
	{
		logger.debug("IProgettoService::save: BEGIN");
		
		boolean aggiornamento = dettaglio != null && dettaglio.getIdPlfProgettiProdotti() != null && dettaglio.getIdPlfProgettiProdotti().intValue()>0;

		if(aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/progetto/" + String.valueOf(dettaglio.getIdPlfProgettiProdotti()));
		} else
		{
			model.addAttribute("refreshRelativeUrl", "/progetto/0");
		}

		if (dettaglio.getProgettiProdottiTranslation().getDescFonte() == null || dettaglio.getProgettiProdottiTranslation().getDescFonte().trim().length() <= 0)
			dettaglio.getProgettiProdottiTranslation().setDescFonte("*");

		if (dettaglio.getProgettoOrigine() == null || dettaglio.getProgettoOrigine().trim().length() <= 0)
			dettaglio.setProgettoOrigine("N");

		if (UtenteContext.getCurrentUser().isImpresa() || (UtenteContext.getCurrentUser().isStakeholder()))
		{
			if (dettaglio.getPlfImpresa() == null || dettaglio.getPlfImpresa().getIdPlfImpresa() == null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() <= 0)
			{
				if (UtenteContext.getCurrentUser().getPlfImpresas() != null && UtenteContext.getCurrentUser().getPlfImpresas().size() > 0)
				{
					// TODO MULTIIMPRESA
					PLFImpresaEntity impresa = impresaService.find(UtenteContext.getCurrentUser().getPlfImpresas().get(0).getIdPlfImpresa());
					if (impresa != null && impresa.getIdPlfImpresa() != null && impresa.getIdPlfImpresa().intValue() > 0)
					{
						dettaglio.setPlfImpresa(impresa);
						dettaglio.setPlfTTipoImpresa(impresa.getPlfTTipoImpresa());
					}
				}
			}
		}

		if (!controlloParametri(dettaglio))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}

		dettaglio = clearNullableReferences(dettaglio);

		try
		{
			progettoService.update(dettaglio);
			progettoService.updateImage(dettaglio.getIdPlfProgettiProdotti(), dettaglio.getImageData());
		}
		catch (InformazioneDuplicataException err)
		{
			model.addAttribute("errorNameDuplicate", dettaglio.getNomeProgettoProdotto());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		catch (Exception err)
		{
			err.printStackTrace();
			model.addAttribute("errorMessage", getMessage("erroreSalvataggio"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		finally
		{
			logger.debug("IProgettoService::detail: END");
		}

		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			dettaglio = progettoService.find(dettaglio.getIdPlfProgettiProdotti());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		else
		{
			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto") + " " + getMessage("info.progetto.allegati"));
			loadModel(session, progettoService.find(dettaglio.getIdPlfProgettiProdotti()), model);
			return VIEW_DETTAGLIO;
		}
	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaProgetto", method = RequestMethod.POST)
	@Transactional
	public String delete(HttpServletRequest request, HttpSession session, PLFProgettiProdottiEntity dettaglio, Model model)
	{
		if (dettaglio != null && dettaglio.getIdPlfProgettiProdotti() != null)
		{
			try
			{
				BigDecimal idDettaglio = dettaglio.getIdPlfProgettiProdotti();
				dettaglio = clearNullableReferences(dettaglio);
				progettoService.delete(dettaglio);
				newsImpresaService.deleteByProgettoImpresa(idDettaglio);
			}
			catch (Exception err)
			{
				err.printStackTrace();
				model.addAttribute("errorMessage", getMessage("erroreCancellazione"));
				loadModel(session, dettaglio, model);
				return VIEW_DETTAGLIO;
			}
		}
		return "redirect:/home/" + IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean controlloParametri(PLFProgettiProdottiEntity dettaglio)
	{

		if (dettaglio.getProgettiProdottiTranslation().getNomeProgettoProdotto() == null || dettaglio.getProgettiProdottiTranslation().getNomeProgettoProdotto().trim().length() <= 0)
			return false;

		if (dettaglio.getPlfTTipoProgettiProdotti() == null || dettaglio.getPlfTTipoProgettiProdotti().getId() == null
				|| dettaglio.getPlfTTipoProgettiProdotti().getId().intValue() <= 0)
			return false;
		
		if (dettaglio.getPlfTTipoProgettiProdotti().getId().intValue() == IAbstractServiceImpl.TIPO_PROGETTO && dettaglio.getProgettiProdottiTranslation().getDescProgetto() == null)
			dettaglio.getProgettiProdottiTranslation().setDescProgetto("");

		if (dettaglio.getPlfTTipoProgettiProdotti().getId().intValue() == IAbstractServiceImpl.TIPO_PROGETTO)
		{
			if (dettaglio.getProgettiProdottiTranslation().getDescrizione() == null || dettaglio.getProgettiProdottiTranslation().getDescrizione().trim().length() <= 0)
				return false;
		}

		if (dettaglio.getProgettoOrigine() == null || dettaglio.getProgettoOrigine().trim().length() <= 0)
			return false;

		return true;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private PLFProgettiProdottiEntity clearNullableReferences(PLFProgettiProdottiEntity dettaglio)
	{

		if (dettaglio.getPlfTTipologiaProgetto() != null
				&& (dettaglio.getPlfTTipologiaProgetto().getId() == null || dettaglio.getPlfTTipologiaProgetto().getId().intValue() <= 0))
			dettaglio.setPlfTTipologiaProgetto(null);

		if (dettaglio.getPlfTStatoProgetto() != null
				&& (dettaglio.getPlfTStatoProgetto().getId() == null || dettaglio.getPlfTStatoProgetto().getId().intValue() <= 0))
			dettaglio.setPlfTStatoProgetto(null);


		// plfCollaborazionis

		return dettaglio;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 */
	private void loadModel(HttpSession session, PLFProgettiProdottiEntity dettaglio, Model model)
	{
		if (dettaglio != null)
		{
			byte[] image = progettoService.getImage(dettaglio.getIdPlfProgettiProdotti());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));

			dettaglio.setPlfCollaborazionis(impresaService.loadCollaborazioniByIdProgetto(dettaglio.getIdPlfProgettiProdotti()));
			dettaglio.setAllegati(progettoService.loadAllegati(dettaglio.getIdPlfProgettoProdotto()));
		}

		if (checkModify(dettaglio))
			model.addAttribute("modifica", true);
		else
			model.addAttribute("modifica", false);

		//model.addAttribute("impresaList", IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, impresaService.getImprese(), "idPlfImpresa", "descImpresa", null, true));

		model.addAttribute("nazioneList", IDecodificheServiceImpl.toMap(PLFTNazioneEntity.class, decodificheService.getNazione(), "codNazione", "descrizione", null, false));
		model.addAttribute("regioneList", new TreeMap<String, String>());
		model.addAttribute("provinciaList", new TreeMap<String, String>());
		model.addAttribute("comuneList", new TreeMap<String, String>());
		model.addAttribute("dataInizioCollaborazione", new Date());
		model.addAttribute("dataFineCollaborazione", null);

		model.addAttribute("dettaglio", dettaglio);
		
		
		
		List<PLFImpresaEntity> imprese = new ArrayList<PLFImpresaEntity>();
		List<PLFImpresaEntity> impreseUser = UtenteContext.getCurrentUser().getPlfImpresas();
		List<PLFImpresaEntity> impreseDelegate = delegatoService.findImpreseDelegato(UtenteContext.getCurrentUser().getIdUtente());
		if (impreseUser != null) imprese.addAll(impreseUser);
		if (impreseDelegate != null) imprese.addAll(impreseDelegate);
		model.addAttribute("impresaList",
				IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, imprese, "idPlfImpresa", "descImpresa", null, false));
		
		
		
		model.addAttribute("tipologiaProgettoList",
				IDecodificheServiceImpl.toMap(PLFTTipologiaProgettoEntity.class, decodificheService.getTipologiaProgetto(), "id", "descrizione", null, false));

		model.addAttribute("listaSettoriProgetto", decodificheService.getSettoriProgettiProdotti());
				
		model.addAttribute("listaSettoriTecnologie", decodificheService.getSettoriTecnologie());

		model.addAttribute("tipoProgettoList", IDecodificheServiceImpl.toMap(PLFTTipoProgettiProdottiEntity.class, decodificheService.getTipoProgettiProdotti(),
				"id", "descrizione", null, false));
		model.addAttribute("statoProgettoList",
				IDecodificheServiceImpl.toMap(PLFTStatoProgettoEntity.class, decodificheService.getStatoProgetto(), "id", "descrizione", null, false));


		//tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if(dettaglio != null && dettaglio.getIdPlfProgettiProdotti() != null) {
			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdPlfProgettiProdotti(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO));
			if (tags != null && tags.size()>0)
			{
				dettaglio.setTags(tags);
				List<BigDecimal> ids = new ArrayList<BigDecimal>();
				for (PLFTTagEntity tag : tags) {
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
	 * @return
	 */
	private boolean checkModify(PLFProgettiProdottiEntity dettaglio)
	{

		if (UtenteContext.getCurrentUser().isImpresa() || UtenteContext.getCurrentUser().isStakeholder())
		{
			if (dettaglio.getIdPlfProgettiProdotti() == null || dettaglio.getIdPlfProgettiProdotti().intValue() <= 0)
				return true;
			else
			{
				if(dettaglio.getPlfImpresa() != null) {
					PLFVDelegatoEntity delega = delegatoService.findDelegato(UtenteContext.getCurrentUser().getIdUtente(), dettaglio.getPlfImpresa().getIdPlfImpresa());
					return delega != null;
				}
			}
		}
		return false;
	}

	/**
	 * @param idProgetto
	 * @param idImpresaStr
	 * @param ragioneSociale
	 * @param partitaIva
	 * @param codiceFiscale
	 * @param comune
	 * @param nazione
	 * @param dataInizioStr
	 * @param dataFineStr
	 * @return
	 */
	@RequestMapping(value = "/secure/collaborazioneProgetto", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public CollaborazioneResult collaborazioneProgetto(@RequestParam(value = "idProgetto", required = true) String idProgetto,
			@RequestParam(value = "idImpresa", required = true) String idImpresaStr, @RequestParam(value = "ragioneSociale", required = true) String ragioneSociale,
			@RequestParam(value = "partitaIva", required = true) String partitaIva, @RequestParam(value = "codiceFiscale", required = true) String codiceFiscale,
			@RequestParam(value = "comune", required = true) String comune, @RequestParam(value = "nazione", required = true) String nazione,
			@RequestParam(value = "dataInizio", required = true) String dataInizioStr, @RequestParam(value = "dataFine", required = true) String dataFineStr)
	{
		try
		{
			
			Date dataInizio = null;
			if (dataInizioStr != null && dataInizioStr.trim().length() > 0)
				dataInizio = dateFormat.parse(dataInizioStr.trim());

			Date dataFine = null;
			if (dataFineStr != null && dataFineStr.trim().length() > 0)
				dataFine = dateFormat.parse(dataFineStr.trim());


			PLFCollaborazioniEntity collaborazione = new PLFCollaborazioniEntity();
			PLFProgettiProdottiEntity progetto = new PLFProgettiProdottiEntity();
			progetto.setIdPlfProgettiProdotti(new BigDecimal(idProgetto.trim()));
			collaborazione.setPlfProgettiProdotti(progetto);
			collaborazione.setDataInizioCollaborazione(dataInizio);
			collaborazione.setDataFineCollaborazione(dataFine);

			if (idImpresaStr != null && idImpresaStr.trim().length() > 0)
			{
				PLFImpresaEntity impresa = impresaService.find(new BigDecimal(idImpresaStr));
				if (impresa != null)
				{
					PLFCollaborazioniEntity data = progettoService.findCollaborazioniImpresaProdotto(impresa.getIdPlfImpresa(), BigDecimal.valueOf(Long.valueOf(idProgetto)));
					if (data != null && data.getIdPlfCollaborazioni() != null && data.getIdPlfCollaborazioni().intValue() > 0)
						throw new RuntimeException("Collaborazione gia' presente");

					PLFImpresaEntity impresaInsert = new PLFImpresaEntity();
					impresaInsert.setIdPlfImpresa(impresa.getIdPlfImpresa());
					collaborazione.setPlfImpresa(impresaInsert);
					collaborazione.setCodiceFiscale(impresa.getCodFiscale());
					collaborazione.setPartitaIva(impresa.getPartitaIva());
					collaborazione.setRagioneSociale(impresa.getImpresaTranslation().getDescImpresa());

					PLFTComuneEntity comuneCollaborazione = new PLFTComuneEntity();
					comuneCollaborazione.setIdComune(impresa.getPlfTComune().getIdComune());
					collaborazione.setPlfTComune(comuneCollaborazione);

					PLFTNazioneEntity nazioneCollaborazione = decodificheService.getNazioneItalia();
					collaborazione.setPlfTNazione(nazioneCollaborazione);

				}
				else
					throw new RuntimeException("Inserimento collaborazione fallito");
			}
			else
			{
				if (ragioneSociale == null || ragioneSociale.trim().length() <= 0)
				{
					throw new RuntimeException("Compilare la ragione sociale.");
				}

				if (partitaIva != null && partitaIva.trim().length() > 0)
				{
					PLFCollaborazioniEntity data = progettoService.findCollaborazioniPartitaIvaProdotto(partitaIva.trim(), BigDecimal.valueOf(Long.valueOf(idProgetto)));
					if (data != null && data.getIdPlfCollaborazioni() != null && data.getIdPlfCollaborazioni().intValue() > 0)
						throw new RuntimeException("Collaborazione gia' presente");
				}
				else if (codiceFiscale != null && codiceFiscale.trim().length() > 0)
				{
					PLFCollaborazioniEntity data = progettoService.findCollaborazioniCodiceFiscaleProdotto(codiceFiscale.trim(), BigDecimal.valueOf(Long.valueOf(idProgetto)));
					if (data != null && data.getIdPlfCollaborazioni() != null && data.getIdPlfCollaborazioni().intValue() > 0)
						throw new RuntimeException("Collaborazione gia' presente");
				}

				collaborazione.setCodiceFiscale(codiceFiscale);
				collaborazione.setPartitaIva(partitaIva);
				collaborazione.setRagioneSociale(ragioneSociale);

				if (comune != null && comune.trim().length() > 0)
				{
					PLFTComuneEntity comuneCollaborazione = new PLFTComuneEntity();
					comuneCollaborazione.setIdComune(new BigDecimal(comune.trim()));
					collaborazione.setPlfTComune(comuneCollaborazione);
				}

				if (nazione != null && nazione.trim().length() > 0)
				{

					PLFTNazioneEntity data = decodificheService.getNazioneByCodice(nazione.trim());
					if (data != null && data.getIdPlfTNazione() != null && data.getIdPlfTNazione().intValue() > 0)
					{
						PLFTNazioneEntity nazioneCollaborazione = new PLFTNazioneEntity();
						nazioneCollaborazione.setIdPlfTNazione(data.getIdPlfTNazione());
						collaborazione.setPlfTNazione(nazioneCollaborazione);
					}
				}
			}

			collaborazione = progettoService.salvaCollaborazione(collaborazione);
			collaborazione = progettoService.findCollaborazioniById(collaborazione.getIdPlfCollaborazioni());

			return CollaborazioneResult.getInstance(collaborazione);
		}
		catch (RuntimeException err)
		{
			logger.debug(err.getMessage());
			return CollaborazioneResult.getInstance(err.getMessage());

		}
		catch (Exception err)
		{
			logger.debug("Inserimento collaborazione fallito");
			return CollaborazioneResult.getInstance("Inserimento collaborazione fallito.");
		}
		finally
		{
			logger.debug("Inserimento collaborazione");
		}
	}

	/**
	 * @param idCollaborazione
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollaborazioneProgetto", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollaborazioneProgetto(@RequestParam(value = "idCollaborazione", required = true) String idCollaborazione)
	{

		try
		{
			PLFCollaborazioniEntity collaborazione = new PLFCollaborazioniEntity();
			collaborazione.setIdPlfCollaborazioni(BigDecimal.valueOf(Long.valueOf(idCollaborazione)));
			progettoService.cancellaCollaborazione(collaborazione);
			return EditableResult.getInstance("idCollaborazione", idCollaborazione);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione collaborazione");
			throw new RuntimeException("Cancellazione collaborazione.");
		}
		finally
		{
			logger.debug("Cancellazione collaborazione " + idCollaborazione);
		}

	}
	
	/**
	 * @param idPlfProgettiProdotti
	 * @param file
	 * @param descrizione
	 * @return
	 */
	@RequestMapping(value = "/secure/allegaFileProgettoProdotto", method = RequestMethod.POST, consumes = { "multipart/form-data" }, produces = { "application/json" })
	@ResponseBody
	public EditableResult allegaFileProgettoProdottoSecure(@RequestParam(value = "idPlfProgettiProdotti", required = true) BigDecimal idPlfProgettiProdotti, @RequestPart("allegato") MultipartFile file,
			@RequestParam(value = "descrizione", required = false) String descrizione)
	{
		try
		{

			if (file.getBytes().length>20971520)
			{
				logger.debug("Upload allegato impresa troppo grande");
				throw new RuntimeException("Upload allegato impresa troppo grande.");
			}

			PLFProgettiProdottiAllegatiEntity e = new PLFProgettiProdottiAllegatiEntity();
			PLFProgettiProdottiAllegatiTranslationEntity translation = new PLFProgettiProdottiAllegatiTranslationEntity();
			translation.setDescrizione(descrizione);
			e.setProgettiProdottiAllegatiTranslation(translation);
			e.setIdPlfProgettiProdotti(idPlfProgettiProdotti);
			e.setFileName(file.getOriginalFilename());
			e.setAllegato(file.getBytes());
			e.setContentType(file.getContentType());
			e.setPathName(file.getOriginalFilename());
			e.setNome(FilenameUtils.removeExtension(file.getOriginalFilename()));

			PLFTTipoAllegatoEntity tipo = new PLFTTipoAllegatoEntity();
			tipo.setId(new BigDecimal(IAbstractServiceImpl.TIPO_ALLEGATO_GENERICO));
			e.setPlfTTipoAllegato(tipo);

			e = progettoService.salvaAllegato(e);

			EditableResult res = EditableResult.getInstance("" + e.getIdProgettiProdottiAllegati().intValue(), e.getNome(), new String[] { descrizione });

			return res;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.debug("Upload allegato progetto prodotto fallito");
			throw new RuntimeException("Upload allegato progetto prodotto fallito.");
		}
		finally
		{
			logger.debug("Allegato progetto prodotto " + idPlfProgettiProdotti);
		}

	}
	
	/**
	 * @param idAllegato
	 * @param response
	 */
	@RequestMapping(value = "/progettoProdottoAllegato/{idAllegato}", method = RequestMethod.GET)
	public void getFile(@PathVariable("idAllegato") String idAllegato, HttpServletResponse response)
	{
		getFileSecure(idAllegato, response);
	}
	
	/**
	 * @param idAllegato
	 * @param response
	 */
	@RequestMapping(value = "/secure/progettoProdottoAllegato/{idAllegato}", method = RequestMethod.GET)
	public void getFileSecure(@PathVariable("idAllegato") String idAllegato, HttpServletResponse response)
	{
		try
		{
			PLFProgettiProdottiAllegatiEntity allegato = progettoService.findAllegato(BigDecimal.valueOf(Long.valueOf(idAllegato)));

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
			logger.debug("Scarico allegato progetto prodotto fallita");
			throw new RuntimeException("Scarico allegato progetto prodotto fallita.");
		}

	}


	/**
	 * @param idAllegato
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaProgettoProdottoAllegato", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaProgettoProdottoAllegatoSecure(@RequestParam(value = "idAllegato", required = true) String idAllegato)
	{

		try
		{
			PLFProgettiProdottiAllegatiEntity allegato = new PLFProgettiProdottiAllegatiEntity();
			allegato.setIdProgettiProdottiAllegati(BigDecimal.valueOf(Long.valueOf(idAllegato)));
			progettoService.cancellaAllegato(allegato);
			return EditableResult.getInstance("idProgettoRicerca", idAllegato + "");
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione allegato progetto prodotto fallita");
			throw new RuntimeException("Cancellazione progetto prodotto fallita.");
		}
		finally
		{
			logger.debug("Cancellazione allegato progetto prodotto " + idAllegato);
		}

	}
	
	/**
	 * @param progettiProdottiAllegatiTranslation
	 * @return
	 */
	@RequestMapping(value = "/secure/updateProgettiProdottiAllegatoDescription", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public PLFProgettiProdottiAllegatiTranslationEntity updateProgettoProdottoAllegatoDescription(PLFProgettiProdottiAllegatiTranslationEntity progettiProdottiAllegatiTranslation)
	{
		try
		{
			return progettoService.saveAllegatiTranslation(progettiProdottiAllegatiTranslation);
		}
		catch (Exception e)
		{
			logger.debug("Aggiornamento descrizione allegato progetto prodotto fallito");
			throw new RuntimeException("Aggiornamento descrizione allegato progetto prodotto fallito.");
		}
		finally
		{
			logger.debug("Aggiornamento descrizione allegato progetto prodotto " + progettiProdottiAllegatiTranslation.getIdProgettiProdottiAllegati());
		}

	}
}
