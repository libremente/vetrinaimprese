package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAreeCompetenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTDenominazioneServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMacroareaServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoServizioEntity;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IDelegatoService;
import it.interlogic.vimp.service.INewsImpresaService;
import it.interlogic.vimp.service.IPacchettoServiziService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.service.ITagService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.utils.EntityUtility;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServiziHandler extends AbstractHandler
{

	private static final String VIEW_DETTAGLIO = "dettaglioServizi";
	private static final String VIEW_NUOVA = "nuovoServizi";

	@Autowired
	protected IServiziService serviziService;

	@Autowired
	protected INewsImpresaService newsImpresaService;

	@Autowired
	protected IPacchettoServiziService pacchettoServiziService;

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
	}

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "servizi";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/servizi/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("ServiziHandler::detail: BEGIN");

		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;

		model.addAttribute("refreshRelativeUrl", "/servizi/" + numero);

		PLFServiziEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = serviziService.find(new BigDecimal(numero));

		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFServiziEntity();
		}

		loadModel(session, dettaglio, model);

		parametri.setTipoInformazione(IAbstractServiceImpl.TIPO_INFO_SERVIZIO);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		logger.debug("ServiziHandler::detail: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/servizi/{numero}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/secure/salvaServizi", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, PLFServiziEntity dettaglio, Model model)
	{
		logger.debug("ServiziHandler::save: BEGIN");

		boolean aggiornamento = dettaglio != null && dettaglio.getIdServizi() != null && dettaglio.getIdServizi().intValue() > 0;

		if (aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/servizi/" + String.valueOf(dettaglio.getIdServizi()));
		}
		else
		{
			model.addAttribute("refreshRelativeUrl", "/servizi/0");
		}

		if (dettaglio.getIdServizi() == null || dettaglio.getIdServizi().intValue() <= 0)
		{
			if (dettaglio.getDataInizio() == null)
				dettaglio.setDataInizio(new Date());
		}

		if (!controlloParametri(dettaglio))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}

		try
		{
			dettaglio = clearNullableReferences(dettaglio);
			serviziService.update(dettaglio);
			serviziService.updateImage(dettaglio.getIdServizi(), dettaglio.getImageData());
		}
		catch (InformazioneDuplicataException err)
		{
			model.addAttribute("errorMessage", getMessage("erroreDuplicazioneServiziStandard"));
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
			logger.debug("ServiziHandler::save: END");
		}

		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			dettaglio = serviziService.find(dettaglio.getIdServizi());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		else
		{
			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto"));
			loadModel(session, serviziService.find(dettaglio.getIdServizi()), model);
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
	@RequestMapping(value = "/secure/cancellaServizi", method = RequestMethod.POST)
	@Transactional
	public String delete(HttpServletRequest request, HttpSession session, PLFServiziEntity dettaglio, Model model)
	{
		if (dettaglio != null && dettaglio.getIdServizi() != null)
		{
			try
			{
				BigDecimal idDettaglio = dettaglio.getIdServizi();
				dettaglio = clearNullableReferences(dettaglio);
				serviziService.delete(dettaglio);
				newsImpresaService.deleteByServizi(idDettaglio);
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
	 * @return
	 */
	private boolean controlloParametri(PLFServiziEntity dettaglio)
	{
		if (StringUtils.isBlank(dettaglio.getServiziStandard()))
			return false;

		if ("S".equals(dettaglio.getServiziStandard()) && EntityUtility.isNotValid(dettaglio.getDenominazioneServizio()))
			return false;

		if ("N".equals(dettaglio.getServiziStandard()) && StringUtils.isBlank(dettaglio.getServiziTranslation().getTitolo()))
			return false;

		if (dettaglio.getServiziTranslation().getDescrizione() == null || dettaglio.getServiziTranslation().getDescrizione().trim().length() <= 0)
			return false;

		if (EntityUtility.isNotValid(dettaglio.getPlfTTipoServizio()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getPlfTAreeCompetenza()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getModalitaErogazioneServizio()))
			return false;

		if (EntityUtility.isNotValid(dettaglio.getElencoMacroarea()))
			return false;

		return true;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 */
	private void loadModel(HttpSession session, PLFServiziEntity dettaglio, Model model)
	{
		if (dettaglio != null)
		{
			byte[] image = serviziService.getImage(dettaglio.getIdServizi());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));
		}

		if (checkModify(dettaglio))
		{
			model.addAttribute("modifica", true);
		}
		else
		{
			model.addAttribute("modifica", false);
		}

		if ((UtenteContext.getCurrentUser().isStakeholder() || UtenteContext.getCurrentUser().isBackoffice()) && EntityUtility.isValid(dettaglio.getIdServizi()))
		{
			model.addAttribute("pacchetti", pacchettoServiziService.findAttiviByServizio(dettaglio.getIdServizi()));
		}

		if (dettaglio.getPlfImpresas() != null && dettaglio.getPlfImpresas().size() > 0)
			dettaglio.setPlfImpresa(dettaglio.getPlfImpresas().get(0));

		model.addAttribute("dettaglio", dettaglio);

		model.addAttribute("impresaList", IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, getOnlyStakeholder(), "idPlfImpresa", "descImpresa", null, false));

		model.addAttribute("denominazioniServizioList", IDecodificheServiceImpl.toMap(PLFTDenominazioneServiziEntity.class, decodificheService.getDenominazioniServizi(), true));

		model.addAttribute("areeCompetenzaList", IDecodificheServiceImpl.toMap(PLFTAreeCompetenzaEntity.class, decodificheService.getAreeCompetenza(), true));

		model.addAttribute("tipoServizioList", IDecodificheServiceImpl.toMap(PLFTTipoServizioEntity.class, decodificheService.getTipoServizio(), true));

		model.addAttribute("modalitaErogazioneServizioList",
				IDecodificheServiceImpl.toMap(PLFTModalitaErogazioneServizioEntity.class, decodificheService.getModalitaErogazioneServizio(), true));

		model.addAttribute("tipoErogazioneServizioList", IDecodificheServiceImpl.toMap(PLFTTipoErogazioneServizioEntity.class, decodificheService.getTipiErogazioneServizio()));

		model.addAttribute("macroareaServizioList", IDecodificheServiceImpl.toMap(PLFTMacroareaServiziEntity.class, decodificheService.getMacroareeServizio()));

		// tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if (dettaglio.getIdServizi() != null)
		{
			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdServizi(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_SERVIZIO));
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
	private PLFServiziEntity clearNullableReferences(PLFServiziEntity dettaglio)
	{
		if (dettaglio.getDenominazioneServizio() != null && (dettaglio.getDenominazioneServizio().getId() == null || dettaglio.getDenominazioneServizio().getId().intValue() <= 0))
		{
			dettaglio.setDenominazioneServizio(null);
		}
		if (dettaglio.getPlfTAreeCompetenza() != null && (dettaglio.getPlfTAreeCompetenza().getId() == null || dettaglio.getPlfTAreeCompetenza().getId().intValue() <= 0))
			dettaglio.setPlfTAreeCompetenza(null);
		if (dettaglio.getModalitaErogazioneServizio() != null
				&& (dettaglio.getModalitaErogazioneServizio().getId() == null || dettaglio.getModalitaErogazioneServizio().getId().intValue() <= 0))
		{
			dettaglio.setModalitaErogazioneServizio(null);
		}

		return dettaglio;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean checkModify(PLFServiziEntity dettaglio)
	{
		return UtenteContext.getCurrentUser().isStakeholder();
	}

	/**
	 * @return
	 */
	private List<PLFImpresaEntity> getOnlyStakeholder()
	{
		List<PLFImpresaEntity> ret = new ArrayList<PLFImpresaEntity>();
		;
		List<PLFImpresaEntity> list = UtenteContext.getCurrentUser().getPlfImpresas();
		if (list != null && list.size() > 0)
		{
			for (PLFImpresaEntity imp : list)
			{
				if (imp.getPlfTTipoImpresa() != null && imp.getPlfTTipoImpresa().getId() != null && imp.getPlfTTipoImpresa().getId().intValue() == 2)
					ret.add(imp);
			}
		}
		return ret;
	}

}
