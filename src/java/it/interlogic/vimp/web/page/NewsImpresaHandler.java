package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoNewsEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
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
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.ParametriRicerca;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsImpresaHandler extends AbstractHandler
{

	private static final String VIEW_DETTAGLIO = "dettaglioNewsImpresa";
	private static final String VIEW_NUOVA = "nuovaNewsImpresa";

	@Autowired
	protected INewsImpresaService newsImpresaService;

	@Autowired
	protected IImpresaService impresaService;

	@Autowired
	protected IProgettoService progettoService;

	@Autowired
	protected IServiziService serviziService;

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
		return "newsImpresa";
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/news/{numero}", method = RequestMethod.GET)
	public String detail(ParametriRicerca parametri, @PathVariable Integer numero, Model model, HttpSession session)
	{
		logger.debug("NewsImpresaHandler::detail: BEGIN");

		String view = VIEW_DETTAGLIO;
		if (numero <= 0)
			view = VIEW_NUOVA;
		
		model.addAttribute("refreshRelativeUrl", "/news/" + numero);
		//model.addAttribute("refreshRelativeUrl", "/news/0"); // Questo redirigerà sempre al form per nuovi inserimenti

		PLFNewsImpresaEntity dettaglio = null;
		if (numero > 0)
		{
			model.addAttribute("aggiornamento", true);
			dettaglio = newsImpresaService.find(new BigDecimal(numero));
		}
		else
		{
			model.addAttribute("aggiornamento", false);
			dettaglio = new PLFNewsImpresaEntity();
		}

		loadModel(session, dettaglio, model);

		parametri.setTipoInformazione(IAbstractServiceImpl.TIPO_INFO_NEWS);
		parametri.setNumeroInformazione(numero);

		model.addAttribute("parametriRicerca", parametri);

		logger.debug("NewsImpresaHandler::detail: END");
		return view;
	}

	/**
	 * @param parametri
	 * @param numero
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/secure/news/{numero}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/secure/salvaNewsImpresa", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpSession session, PLFNewsImpresaEntity dettaglio, Model model)
	{
		logger.debug("NewsImpresaHandler::save: BEGIN");
		
		boolean aggiornamento = dettaglio != null && dettaglio.getIdNewsImpresa() != null && dettaglio.getIdNewsImpresa().intValue()>0;

		if(aggiornamento)
		{
			model.addAttribute("refreshRelativeUrl", "/news/" + String.valueOf(dettaglio.getIdNewsImpresa()));
		} else 
		{
			model.addAttribute("refreshRelativeUrl", "/news/0" );
		}

		// inserimento impresa di default
		if (UtenteContext.getCurrentUser().isImpresa() || (UtenteContext.getCurrentUser().isStakeholder()))
		{
			if (dettaglio.getIdNewsImpresa() == null || dettaglio.getIdNewsImpresa().intValue() <= 0)
			{
				boolean defaultInerente = false;
				if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
				{
					defaultInerente = true;
				}

				if (dettaglio.getPlfProgettiProdotti() != null && dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti() != null
						&& dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti().intValue() > 0)
				{
					defaultInerente = true;
				}

				if (!defaultInerente && UtenteContext.getCurrentUser().getPlfImpresas() != null && UtenteContext.getCurrentUser().getPlfImpresas().size() > 0)
				{
					PLFImpresaEntity impresa = new PLFImpresaEntity();
					impresa.setIdPlfImpresa(UtenteContext.getCurrentUser().getPlfImpresas().get(0).getIdPlfImpresa());
					dettaglio.setPlfImpresa(impresa);
				}
			}
			else
			{

				if ("1".equalsIgnoreCase(dettaglio.getRadioNewsInerente()))
				{
					dettaglio.setPlfProgettiProdotti(null);
					dettaglio.setPlfServizi(null);
				}
				else if ("2".equalsIgnoreCase(dettaglio.getRadioNewsInerente()))
				{
					dettaglio.setPlfImpresa(null);
					dettaglio.setPlfProgettiProdotti(null);
				}
				else if ("3".equalsIgnoreCase(dettaglio.getRadioNewsInerente()))
				{
					dettaglio.setPlfImpresa(null);
					dettaglio.setPlfServizi(null);
				}
			}
		}

		if (!controlloParametri(dettaglio))
		{
			model.addAttribute("errorMessage", getMessage("datiObbligatori"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		
		
		if (dettaglio.getDataInizio() != null && dettaglio.getDataFine() != null && dettaglio.getDataFine().before(dettaglio.getDataInizio()))
		{
			model.addAttribute("errorMessage", getMessage("erroreDateNews"));
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}


		try
		{
			dettaglio = clearNullableReferences(dettaglio);
			newsImpresaService.update(dettaglio);
			newsImpresaService.updateImage(dettaglio.getIdNewsImpresa(), dettaglio.getImageData());
		}
		catch (InformazioneDuplicataException err)
		{
			model.addAttribute("errorMessage", getMessage("erroreDuplicazioneNewsImpresa"));
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
			logger.debug("NewsImpresaHandler::save: END");
		}

		
		if (aggiornamento)
		{
			model.addAttribute("successMessage", getMessage("aggiornamentoAvvenuto"));
			dettaglio = newsImpresaService.find(dettaglio.getIdNewsImpresa());
			loadModel(session, dettaglio, model);
			return VIEW_DETTAGLIO;
		}
		else
		{
			model.addAttribute("successMessage", getMessage("inserimentoAvvenuto"));
			loadModel(session, newsImpresaService.find(dettaglio.getIdNewsImpresa()), model);
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
	@RequestMapping(value = "/secure/cancellaNewsImpresa", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpSession session, PLFNewsImpresaEntity dettaglio, Model model)
	{
		if (dettaglio != null && dettaglio.getIdNewsImpresa() != null)
		{
			try
			{
				dettaglio = clearNullableReferences(dettaglio);
				newsImpresaService.delete(dettaglio);
			}
			catch (Exception err)
			{
				err.printStackTrace();
				model.addAttribute("errorMessage", getMessage("erroreCancellazione"));
				loadModel(session, dettaglio, model);
				return VIEW_DETTAGLIO;
			}
		}
		return "redirect:/home/" + IAbstractServiceImpl.TIPO_INFO_NEWS;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean controlloParametri(PLFNewsImpresaEntity dettaglio)
	{
		if (dettaglio.getNewsImpresaTranslation().getDescrizione() == null || dettaglio.getNewsImpresaTranslation().getDescrizione().trim().length() <= 0)
			return false;

		if (dettaglio.getNewsImpresaTranslation().getDataTesto() == null || dettaglio.getNewsImpresaTranslation().getDataTesto().trim().length() <= 0)
			return false;

		if (dettaglio.getPlfTTipoNew() == null || dettaglio.getPlfTTipoNew().getId() == null || dettaglio.getPlfTTipoNew().getId().intValue() <= 0)
			return false;

		if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
		{
			return true;
		}
		else if (dettaglio.getPlfProgettiProdotti() != null && dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti() != null
				&& dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti().intValue() > 0)
		{
			return true;
		}
		else if (dettaglio.getPlfServizi() != null && dettaglio.getPlfServizi().getIdServizi() != null
				&& dettaglio.getPlfServizi().getIdServizi().intValue() > 0)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * @param session
	 * @param dettaglio
	 * @param model
	 */
	private void loadModel(HttpSession session, PLFNewsImpresaEntity dettaglio, Model model)
	{
		if (dettaglio != null)
		{
			byte[] image = newsImpresaService.getImage(dettaglio.getIdNewsImpresa());
			if (image != null && image.length > 0)
				dettaglio.setImageData(Base64.encodeBase64String(image));
		}

		if (checkModify(dettaglio))
			model.addAttribute("modifica", true);
		else
			model.addAttribute("modifica", false);

		String radioNewsInerente = "";
		if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
		{
			radioNewsInerente = "1";
		}
		else if (dettaglio.getPlfProgettiProdotti() != null && dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti() != null
				&& dettaglio.getPlfProgettiProdotti().getIdPlfProgettiProdotti().intValue() > 0)
		{
			radioNewsInerente = "3";
		}
		else if (dettaglio.getPlfServizi() != null && dettaglio.getPlfServizi().getIdServizi() != null
				&& dettaglio.getPlfServizi().getIdServizi().intValue() > 0)
		{
			radioNewsInerente = "2";
		}
		

		dettaglio.setRadioNewsInerente(radioNewsInerente);

		model.addAttribute("dettaglio", dettaglio);

		model.addAttribute("tipoNewsList", IDecodificheServiceImpl.toMap(PLFTTipoNewsEntity.class, decodificheService.getTipoNews(), "id", "descrizione", null, false));

		if (UtenteContext.getCurrentUser().isImpresa() || UtenteContext.getCurrentUser().isStakeholder())
		{			
			if (UtenteContext.getCurrentUser().getPlfImpresas() != null && UtenteContext.getCurrentUser().getPlfImpresas().size() > 0)
			{
				// IMPRESE
				model.addAttribute("impresaList",
						IDecodificheServiceImpl.toMap(PLFImpresaEntity.class, UtenteContext.getCurrentUser().getPlfImpresas(), "idPlfImpresa", "descImpresa", null, false));

				
				// PROGETTI
				List<PLFProgettiProdottiEntity> progetti = new ArrayList<PLFProgettiProdottiEntity>();
				for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
				{
					List<PLFProgettiProdottiEntity> subProject = progettoService.findByImpresaAttiviNonScaduti(impresa.getIdPlfImpresa());
					if (subProject != null)
						progetti.addAll(subProject);
				}
				Collections.sort(progetti, new Comparator<PLFProgettiProdottiEntity>(){
					@Override
					public int compare(PLFProgettiProdottiEntity o1, PLFProgettiProdottiEntity o2)
					{
						if (o1.getNomeProgettoProdotto()==null || o2.getNomeProgettoProdotto()==null)
							return -1;
						return o1.getNomeProgettoProdotto().compareTo(o2.getNomeProgettoProdotto());
					}
				});
				model.addAttribute("progettiList", IDecodificheServiceImpl.toMap(PLFProgettiProdottiEntity.class, progetti,"idPlfProgettiProdotti", "nomeProgettoProdotto", null, false));
				
				
				// SERVIZI
				List<PLFServiziEntity> servizi = new ArrayList<PLFServiziEntity>();
				for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
				{
					List<PLFServiziEntity> subService = serviziService.loadServiziByImpresa(impresa.getIdPlfImpresa(),true);
					if (subService != null)
						servizi.addAll(subService);
				}
				Collections.sort(servizi, new Comparator<PLFServiziEntity>(){
					@Override
					public int compare(PLFServiziEntity o1, PLFServiziEntity o2)
					{
						if (o1.getDenominazioneCalcolata()==null || o2.getDenominazioneCalcolata()==null)
							return -1;
						return o1.getDenominazioneCalcolata().compareTo(o2.getDenominazioneCalcolata());
					}
				});
				model.addAttribute("serviziList", IDecodificheServiceImpl.toMap(PLFServiziEntity.class, servizi,"idServizi", "denominazioneCalcolata", null, false));
			}
		}

		//tags
		List<PLFTTagEntity> allTags = tagService.findTags();

		if(dettaglio.getIdNewsImpresa() != null) {
			List<PLFTTagEntity> tags = tagService.findByInfoAndType(dettaglio.getIdNewsImpresa(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_NEWS));
			
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
	private PLFNewsImpresaEntity clearNullableReferences(PLFNewsImpresaEntity dettaglio)
	{
		String radioNewsInerente = dettaglio.getRadioNewsInerente();
		if ("1".equals(radioNewsInerente.trim()))
		{
			dettaglio.setPlfServizi(null);
			dettaglio.setPlfProgettiProdotti(null);
		}
		else if ("2".equals(radioNewsInerente.trim()))
		{
			dettaglio.setPlfImpresa(null);
			dettaglio.setPlfProgettiProdotti(null);
		}
		else if ("3".equals(radioNewsInerente.trim()))
		{
			dettaglio.setPlfServizi(null);
			dettaglio.setPlfImpresa(null);
		}
		return dettaglio;
	}

	/**
	 * @param dettaglio
	 * @return
	 */
	private boolean checkModify(PLFNewsImpresaEntity dettaglio)
	{

		if (UtenteContext.getCurrentUser().isImpresa() || UtenteContext.getCurrentUser().isStakeholder())
		{
			if (dettaglio.getIdNewsImpresa() == null || dettaglio.getIdNewsImpresa().intValue() <= 0)
				return true;
			else
			{
				PLFVDelegatoEntity delega = null;

				if(dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa()!=null)
				{
					delega = delegatoService.findDelegato(UtenteContext.getCurrentUser().getIdUtente(),dettaglio.getPlfImpresa().getIdPlfImpresa());
				}

				else if (dettaglio.getPlfProgettiProdotti() != null && dettaglio.getPlfProgettiProdotti().getPlfImpresa() != null
						&& dettaglio.getPlfProgettiProdotti().getPlfImpresa().getIdPlfImpresa() != null)
				{
					delega = delegatoService.findDelegato(UtenteContext.getCurrentUser().getIdUtente(),dettaglio.getPlfProgettiProdotti().getPlfImpresa().getIdPlfImpresa());
				}

				else if (dettaglio.getPlfServizi() != null && dettaglio.getPlfServizi().getIdServizi() != null)
				{
					List<PLFServiziEntity> listaServizi = delegatoService.findServiziImpreseDelegato(UtenteContext.getCurrentUser().getIdUtente());

					for(PLFServiziEntity servizio : listaServizi) {
						if(servizio.getIdServizi().equals(dettaglio.getPlfServizi().getIdServizi())) {
							return true;
						}
					}
				}

				return delega != null;

			}
		}

		return false;
	}
}
