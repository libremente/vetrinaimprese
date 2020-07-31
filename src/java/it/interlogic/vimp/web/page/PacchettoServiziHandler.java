package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMacroareaServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazionePacchettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IDelegatoService;
import it.interlogic.vimp.service.IPacchettoServiziService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.service.ITagService;
import it.interlogic.vimp.service.IUtenteService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.utils.EntityUtility;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PacchettoServiziHandler extends AbstractHandler
{
	private static final String VIEW_DETTAGLIO = "dettaglioPacchettoServizi";
	private static final String VIEW_NUOVA = "nuovoPacchettoServizi";

	@Autowired
	protected IPacchettoServiziService pacchettoServiziService;

	@Autowired
	protected IDelegatoService delegatoService;

	@Autowired
	protected IServiziService serviziService;

	@Autowired
	protected IUtenteService utenteService;

	@Autowired
	protected IDecodificheService decodificheService;

	@Autowired
	protected ITagService tagService;

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "pacchettoServizi";
	}

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pacchettoServizi/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("PacchettoServiziHandler::detail: BEGIN");

		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;

		model.addAttribute("refreshRelativeUrl", "/pacchettoServizi/" + numero);

		PLFPacchettoServiziEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = pacchettoServiziService.find(new BigDecimal(numero));
		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFPacchettoServiziEntity();
		}

		loadModel(session, dettaglio, model);

		parametri.setTipoInformazione(IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		logger.debug("PacchettoServiziHandler::detail: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/pacchettoServizi/{numero}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/secure/salvaPacchettoServizi", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, PLFPacchettoServiziEntity dettaglio, Model model)
	{
		logger.debug("PacchettoServiziHandler::save: BEGIN");

		boolean aggiornamento = dettaglio != null && dettaglio.getIdPacchettoServizi() != null && dettaglio.getIdPacchettoServizi().intValue() > 0;

		if (aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/pacchettoServizi/" + String.valueOf(dettaglio.getIdPacchettoServizi()));
		}
		else
		{
			model.addAttribute("refreshRelativeUrl", "/pacchettoServizi/0");
		}

		if (dettaglio.getIdPacchettoServizi() == null || dettaglio.getIdPacchettoServizi().intValue() <= 0)
		{

			if (dettaglio.getDataInizio() == null)
				dettaglio.setDataInizio(new Date());
		}

		if (!aggiornamento)
		{
			boolean aPagamento = false;
			for (BigInteger id : dettaglio.getElencoServizi())
			{
				if (serviziService.find(EntityUtility.toBigDecimal(id)).getModalitaErogazioneServizio().getId().equals(PLFTTipoErogazioneServizioEntity.A_PAGAMENTO))
				{
					aPagamento = true;
					break;
				}
			}
			if (aPagamento)
			{
				dettaglio.setModalitaErogazione(decodificheService.getModalitaErogazionePacchettoById(PLFTModalitaErogazionePacchettoEntity.A_PAGAMENTO));
			}
			else
			{
				dettaglio.setModalitaErogazione(decodificheService.getModalitaErogazionePacchettoById(PLFTModalitaErogazionePacchettoEntity.GRATUITA));
			}

		}

		PLFTUtenteEntity lastUtenteCreazione = null;
		PLFTUtenteEntity lastUtenteModifica = null;
		if (aggiornamento)
		{
			lastUtenteModifica = utenteService.findUtente(UtenteContext.getCurrentUser().getIdUtente());
			dettaglio.setUtenteUltimaModifica(lastUtenteModifica);
			dettaglio.setDataUltimaModifica(new Date());
		}
		else
		{
			lastUtenteCreazione = utenteService.findUtente(UtenteContext.getCurrentUser().getIdUtente());
			dettaglio.setUtenteCreazione(lastUtenteCreazione);
			dettaglio.setDataCreazione(new Date());
		}

		if (!controlloParametri(dettaglio, model, aggiornamento))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
	
		

		try
		{
			dettaglio = clearNullableReferences(dettaglio);
			pacchettoServiziService.update(dettaglio);
			pacchettoServiziService.updateImage(dettaglio.getIdPacchettoServizi(), dettaglio.getImageData());
		}
		catch (InformazioneDuplicataException err)
		{
			model.addAttribute("errorMessage", getMessage("erroreDuplicazioneServizi"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		catch (Exception err)
		{
			LoggerUtility.error(err.getMessage(), err);
			model.addAttribute("errorMessage", getMessage("erroreSalvataggio"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		finally
		{
			logger.debug("PacchettoServiziHandler::save: END");
		}
		
		if (lastUtenteCreazione != null)
			dettaglio.setUtenteCreazione(lastUtenteCreazione);
		
		dettaglio.setUtenteUltimaModifica(lastUtenteModifica);
		

		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			dettaglio = pacchettoServiziService.find(dettaglio.getIdPacchettoServizi());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		else
		{
			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto"));
			loadModel(session, pacchettoServiziService.find(dettaglio.getIdPacchettoServizi()), model);
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
	@RequestMapping(value = "/secure/cancellaPacchettoServizi", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpSession session, PLFPacchettoServiziEntity dettaglio, Model model)
	{
		if (dettaglio != null && dettaglio.getIdPacchettoServizi() != null)
		{
			try
			{
				dettaglio = clearNullableReferences(dettaglio);
				pacchettoServiziService.delete(dettaglio);
			}
			catch (Exception err)
			{
				err.printStackTrace();
				model.addAttribute("errorMessage", getMessage("erroreCancellazione"));
				loadModel(session, dettaglio, model);
				return VIEW_DETTAGLIO;
			}
		}
		return "redirect:/home/" + IAbstractServiceImpl.TIPO_INFO_SERVIZIO;
	}

	/**
	 * @param dettaglio
	 * @param model
	 * @param aggiornamento
	 * @return
	 */
	private boolean controlloParametri(PLFPacchettoServiziEntity dettaglio, Model model, boolean aggiornamento)
	{

		if (StringUtils.isBlank(dettaglio.getPacchettoServiziTranslation().getTitolo()))
			return false;

		if (StringUtils.isBlank(dettaglio.getPacchettoServiziTranslation().getDescrizione()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getMacroarea()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getModalitaErogazione()))
			return false;

		if (StringUtils.isBlank(dettaglio.getLuogoErogazione()))
			return false;

		if (StringUtils.isBlank(dettaglio.getContatti()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getElencoServizi()) || dettaglio.getElencoServizi().size() < 2)
			return false;

		List<PLFPacchettoServiziEntity> altriPacchettiUguali = pacchettoServiziService.findByIdsServizi(EntityUtility.toBigdec(dettaglio.getElencoServizi()));
		if (!altriPacchettiUguali.isEmpty() && !aggiornamento)
		{
			return false;
		}

		return true;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 */
	private void loadModel(HttpSession session, PLFPacchettoServiziEntity dettaglio, Model model)
	{
		model.addAttribute("utente", UtenteContext.getCurrentUser());

		if (dettaglio != null)
		{
			byte[] image = pacchettoServiziService.getImage(dettaglio.getIdPacchettoServizi());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));
		}

		boolean isModifica = false;
		if (checkModify(dettaglio))
		{
			model.addAttribute("modifica", true);
			isModifica = true;
		}
		else
			model.addAttribute("modifica", false);
	
		
		model.addAttribute("dettaglio", dettaglio);

		List<PLFServiziEntity> serviziUtente = new ArrayList<PLFServiziEntity>();
		List<PLFServiziEntity> serviziGenerali = new ArrayList<PLFServiziEntity>();

		if (UtenteContext.getCurrentUser().isStakeholder())
		{
			// Servizi
			BigDecimal idStakeholder = UtenteContext.getCurrentUser().getPlfImpresas().get(0).getIdPlfImpresa();
			serviziUtente = serviziService.loadServiziAttiviByImpresa(idStakeholder, true);
			List<PLFServiziEntity> temp = serviziService.findAttivi();
			for (PLFServiziEntity servizio : temp)
			{
				boolean alreadyThere = false;
				for (PLFServiziEntity servizioUtente : serviziUtente)
				{
					if (EntityUtility.equals(servizio.getIdServizi(), servizioUtente.getIdServizi()))
					{
						alreadyThere = true;
						break;
					}
				}
				if (!alreadyThere)
				{
					serviziGenerali.add(servizio);
				}
			}

		}
		else if (UtenteContext.getCurrentUser().isBackoffice())
		{
			serviziGenerali.addAll(serviziService.findAttivi());
		}

		// tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if (dettaglio != null && dettaglio.getIdPacchettoServizi() != null)
		{
			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdPacchettoServizi(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI));
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

		model.addAttribute("serviziUtenteList", IDecodificheServiceImpl.toMap(PLFServiziEntity.class, serviziUtente, "idServizi", "denominazioneCalcolata", null, true,"select.service.option"));

		model.addAttribute("serviziGeneralList", IDecodificheServiceImpl.toMap(PLFServiziEntity.class, serviziGenerali, "idServizi", "denominazioneCalcolata", null, true,"select.service.option"));

		model.addAttribute("macroareeList", IDecodificheServiceImpl.toMap(PLFTMacroareaServiziEntity.class, decodificheService.getMacroareeServizio()));

		model.addAttribute("modalitaErogazioneList",
				IDecodificheServiceImpl.toMap(PLFTModalitaErogazionePacchettoEntity.class, decodificheService.getModalitaErogazionePacchetto()));
		
		
		int numeroServizi = 0;
		if (serviziUtente != null)
			numeroServizi += serviziUtente.size();
		if (serviziGenerali != null)
			numeroServizi += serviziGenerali.size();
		
		if (isModifica && numeroServizi<2 && dettaglio.isPubblicato())
		{
			model.addAttribute("warningMessage",getMessage("pacchettoMenoDiDueServizi"));	
		}

		modelTags(dettaglio, model);

	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private PLFPacchettoServiziEntity clearNullableReferences(PLFPacchettoServiziEntity dettaglio)
	{
		if (dettaglio.getPlfImpresa() != null && (dettaglio.getPlfImpresa().getIdPlfImpresa() == null || dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() <= 0))
			dettaglio.setPlfImpresa(null);

		return dettaglio;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean checkModify(PLFPacchettoServiziEntity dettaglio)
	{
		return UtenteContext.getCurrentUser().isStakeholder() || UtenteContext.getCurrentUser().isBackoffice();
	}

	/* **REST** */

	/**
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/secure/checkPacchettoServizi", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> checkServizi(@RequestBody Map<String, Object> obj)
	{

		Object idObj = obj.get("idPacchetto");
		@SuppressWarnings("unchecked")
		List<String> idsObj = (List<String>) obj.get("servizi");

		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		boolean anotherExist = false;
		ResponseEntity r = null;

		for (String id : idsObj)
		{
			BigDecimal bi = new BigDecimal(id);
			ids.add(bi);
		}

		List<PLFPacchettoServiziEntity> altriPacchettiUguali = pacchettoServiziService.findByIdsServizi(ids);

		if (idObj != null)
		{
			BigDecimal idPacchetto = new BigDecimal((Integer) idObj);

			for (PLFPacchettoServiziEntity pacchetto : altriPacchettiUguali)
			{
				if (!pacchetto.getIdPacchettoServizi().equals(idPacchetto))
				{
					anotherExist = true;
					break;
				}
			}
		}
		else
		{
			if (!altriPacchettiUguali.isEmpty())
			{
				anotherExist = true;
			}
		}

		if (anotherExist)
		{
			r = new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
		}
		else
		{
			r = new ResponseEntity(HttpStatus.OK);
		}

		return r;
	}

}
