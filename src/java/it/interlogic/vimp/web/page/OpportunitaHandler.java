package it.interlogic.vimp.web.page;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.interlogic.vimp.data.jpa.model.PLFOpportunitaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreProgettiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSoggAmmissibiliEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoProgrammaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFROpportunitaStatoImpresaEntity;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IOpportunitaService;
import it.interlogic.vimp.service.ITagService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.EditableResult;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.UtenteContext;

@Controller
public class OpportunitaHandler extends AbstractHandler
{

	private static final String VIEW_DETTAGLIO = "dettaglioOpportunita";
	private static final String VIEW_NUOVA = "nuovaOpportunita";

	@Autowired
	protected IOpportunitaService opportunitaService;

	@Autowired
	protected IDecodificheService decodificheService;
	
	
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
		binder.registerCustomEditor(BigDecimal.class, "numValEconomico", new CustomNumberEditor(BigDecimal.class, numberFormatter, true));
	}

	/* (non-Javadoc)
	 * @see it.interlogic.vimp.web.AbstractHandler#getPageName()
	 */
	@Override
	public String getPageName()
	{
		return "opportunita";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/opportunita/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("OpportunitaHandler::detail: BEGIN");

		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;

		model.addAttribute("refreshRelativeUrl", "/opportunita/" + numero);

		PLFOpportunitaEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = opportunitaService.find(new BigDecimal(numero));

		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFOpportunitaEntity();
		}

		loadModel(session, dettaglio, model);

		parametri.setTipoInformazione(IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		logger.debug("OpportunitaHandler::detail: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/opportunita/{numero}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/secure/salvaOpportunita", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, PLFOpportunitaEntity dettaglio, Model model)
	{
		logger.debug("OpportunitaHandler::save: BEGIN");

		boolean aggiornamento = dettaglio != null && dettaglio.getIdPlfOpportunita() != null && dettaglio.getIdPlfOpportunita().intValue() > 0;

		if (aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/opportunita/" + String.valueOf(dettaglio.getIdPlfOpportunita()));
		}
		else
		{
			model.addAttribute("refreshRelativeUrl", "/opportunita/0");
		}

		if (dettaglio.getOpportunitaTranslation().getDescFonte() == null || dettaglio.getOpportunitaTranslation().getDescFonte().trim().length() <= 0)
			dettaglio.getOpportunitaTranslation().setDescFonte("*");

		if (!controlloParametri(dettaglio))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}

		if (dettaglio.getDataApertura() != null && dettaglio.getDataScadenza() != null && dettaglio.getDataScadenza().before(dettaglio.getDataApertura()))
		{
			model.addAttribute("errorMessage", getMessage("erroreDateOpportunita"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}

		try
		{
			dettaglio = clearNullableReferences(dettaglio);
			opportunitaService.update(dettaglio);
			opportunitaService.updateImage(dettaglio.getIdPlfOpportunita(), dettaglio.getImageData());
		}
		catch (InformazioneDuplicataException err)
		{
			model.addAttribute("errorMessage", getMessage("erroreDuplicazioneOpportunita"));
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
			logger.debug("OpportunitaHandler::save: END");
		}

		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			dettaglio = opportunitaService.find(dettaglio.getIdPlfOpportunita());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		else
		{
			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto"));
			return "redirect:/secure/opportunita/" + dettaglio.getIdPlfOpportunita();
		}
	}

	/**
	 * @param idOpportunita
	 * @param idStatoImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/collegaStatoImpresa", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult collegaStatoImpresa(@RequestParam(value = "idOpportunita", required = true) String idOpportunita,
			@RequestParam(value = "idStatoImpresa", required = true) String idStatoImpresa)
	{
		try
		{
			PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa = new PLFROpportunitaStatoImpresaEntity();
			opportunitaStatoImpresa.setIdOpportunita(BigDecimal.valueOf(Long.valueOf(idOpportunita)));
			opportunitaStatoImpresa.setIdStatoImpresa(BigDecimal.valueOf(Long.valueOf(idStatoImpresa)));

			PLFROpportunitaStatoImpresaEntity present = opportunitaService.findCollegamentoOpportunitaStatoImpresa(opportunitaStatoImpresa.getCompositePrimaryKey());
			if (present != null && present.getIdOpportunita() != null && present.getIdOpportunita().intValue() > 0)
			{
				logger.debug("Inserimento stato impresa opportunita' fallito");
				throw new RuntimeException("Inserimento stato impresa opportunita' fallito.");
			}
			opportunitaService.salvaCollegamentoOpportunitaStatoImpresa(opportunitaStatoImpresa);
			PLFTStatoImpresaEntity statoImpresa = decodificheService.getStatoImpresa(opportunitaStatoImpresa.getIdStatoImpresa());

			EditableResult res = EditableResult.getInstance("" + statoImpresa.getId().intValue(), statoImpresa.getDescrizione());
			return res;
		}
		catch (Exception err)
		{
			logger.debug("Inserimento stato impresa opportunita' fallito");
			throw new RuntimeException("Inserimento stato impresa opportunita' fallito.");
		}
		finally
		{
			logger.debug("Inserimento stato impresa opportunita'");
		}
	}

	/**
	 * @param idOpportunita
	 * @param idStatoImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaCollegamentoStatoImpresa", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public EditableResult cancellaCollegamentoStatoImpresa(@RequestParam(value = "idOpportunita", required = true) String idOpportunita,
			@RequestParam(value = "idStatoImpresa", required = true) String idStatoImpresa)
	{

		try
		{
			PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa = new PLFROpportunitaStatoImpresaEntity();
			opportunitaStatoImpresa.setIdOpportunita(BigDecimal.valueOf(Long.valueOf(idOpportunita)));
			opportunitaStatoImpresa.setIdStatoImpresa(BigDecimal.valueOf(Long.valueOf(idStatoImpresa)));
			opportunitaService.cancellaCollegamentoOpportunitaStatoImpresa(opportunitaStatoImpresa);
			return EditableResult.getInstance("idStatoImpresa", idStatoImpresa);
		}
		catch (Exception e)
		{
			logger.debug("Cancellazione opportunità stato impresa fallita");
			throw new RuntimeException("Cancellazione opportunità stato impresa fallita.");
		}
		finally
		{
			logger.debug("Cancellazione opportunità stato impresa " + idStatoImpresa);
		}

	}

	/**
	 * @param request
	 * @param session
	 * @param dettaglio
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/cancellaOpportunita", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpSession session, PLFOpportunitaEntity dettaglio, Model model)
	{
		if (dettaglio != null && dettaglio.getIdPlfOpportunita() != null)
		{
			try
			{
				dettaglio = clearNullableReferences(dettaglio);
				opportunitaService.delete(dettaglio);
			}
			catch (Exception err)
			{
				err.printStackTrace();
				model.addAttribute("errorMessage", getMessage("erroreCancellazione"));
				loadModel(session, dettaglio, model);
				return VIEW_DETTAGLIO;
			}
		}
		return "redirect:/home/" + IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private PLFOpportunitaEntity clearNullableReferences(PLFOpportunitaEntity dettaglio)
	{
		if (dettaglio.getPlfTSoggAmmissibili() != null && (dettaglio.getPlfTSoggAmmissibili().getId() == null || dettaglio.getPlfTSoggAmmissibili().getId().intValue() <= 0))
			dettaglio.setPlfTSoggAmmissibili(null);

		if (dettaglio.getPlfTSettoreProgetti() != null && (dettaglio.getPlfTSettoreProgetti().getId() == null || dettaglio.getPlfTSettoreProgetti().getId().intValue() <= 0))
			dettaglio.setPlfTSettoreProgetti(null);

		return dettaglio;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean controlloParametri(PLFOpportunitaEntity dettaglio)
	{
		if (dettaglio.getOpportunitaTranslation().getDescNome() == null || dettaglio.getOpportunitaTranslation().getDescNome().trim().length() <= 0)
			return false;

		if (dettaglio.getPlfTTipoProgramma() == null || dettaglio.getPlfTTipoProgramma().getId() == null && dettaglio.getPlfTTipoProgramma().getId().intValue() <= 0)
			return false;

		if (dettaglio.getNumValEconomico() == null || dettaglio.getNumValEconomico().intValue() <= 0)
			return false;

		if (dettaglio.getOpportunitaTranslation().getDescPeriodo() == null || dettaglio.getOpportunitaTranslation().getDescPeriodo().trim().length() <= 0)
			return false;

		if (dettaglio.getOpportunitaTranslation().getDescFonte() == null || dettaglio.getOpportunitaTranslation().getDescFonte().trim().length() <= 0)
			return false;

		if (dettaglio.getDataApertura() == null)
			return false;

		if (dettaglio.getDataScadenza() == null)
			return false;

		return true;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 */
	private void loadModel(HttpSession session, PLFOpportunitaEntity dettaglio, Model model)
	{
		if (dettaglio != null)
		{
			byte[] image = opportunitaService.getImage(dettaglio.getIdPlfOpportunita());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));

			dettaglio.setStatoImpresas(opportunitaService.loadOpportunitaStatoImpresa(dettaglio.getIdPlfOpportunita()));
		}

		if (UtenteContext.getCurrentUser().isBackoffice())
			model.addAttribute("modifica", true);
		else
			model.addAttribute("modifica", false);

		model.addAttribute("dettaglio", dettaglio);

		model.addAttribute("tipoProgrammaList",
				IDecodificheServiceImpl.toMap(PLFTTipoProgrammaEntity.class, decodificheService.getTipoProgramma(), "id", "descrizione", null, true));
		model.addAttribute("soggAmmissibiliList",
				IDecodificheServiceImpl.toMap(PLFTSoggAmmissibiliEntity.class, decodificheService.getSoggAmmissibili(), "id", "descrizione", null, true));
		model.addAttribute("settoreImpresaList",
				IDecodificheServiceImpl.toMap(PLFTSettoreProgettiEntity.class, decodificheService.getSettoreProgetti(), "id", "descrizione", null, true));
		model.addAttribute("statoImpresaList", IDecodificheServiceImpl.toMap(PLFTStatoImpresaEntity.class, decodificheService.getStatoImpresa(), "id", "descrizione", null, false));

		// tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if (dettaglio.getIdPlfOpportunita() != null)
		{
			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdPlfOpportunita(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA));
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
}
