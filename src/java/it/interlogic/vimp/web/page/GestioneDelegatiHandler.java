package it.interlogic.vimp.web.page;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
import it.interlogic.vimp.service.IDelegatoService;
import it.interlogic.vimp.service.IImpresaService;
import it.interlogic.vimp.service.IUtenteService;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GestioneDelegatiHandler extends AbstractHandler
{
	private static final String FORM = "formDelegato";
	private static final String LIST = "gestioneDelegati";

	@Autowired
	private IImpresaService impresaService;

	@Autowired
	private IDelegatoService delegatoService;

	@Autowired
	private IUtenteService utenteService;

	/**
	 * @param model
	 * @param idPlfImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/gestioneDelegati", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "idPlfImpresa", required = false) BigDecimal idPlfImpresa)
	{

		List<PLFImpresaEntity> impreseUtente = delegatoService.findImpreseDelegato(UtenteContext.getCurrentUser().getIdUtente());
		if (idPlfImpresa == null)
		{
			idPlfImpresa = impreseUtente.get(0).getIdPlfImpresa();
		}

		// prepareModelToSearchDelegati(model, idPlfImpresa);
		List<PLFVDelegatoEntity> delegati = delegatoService.findUtentiDelegati(idPlfImpresa);
		PLFVDelegatoEntity delega = delegatoService.findDelegato(UtenteContext.getCurrentUser().getIdUtente(), idPlfImpresa);

		model.addAttribute("idPlfImpresa", idPlfImpresa);
		model.addAttribute("elencoImprese", impreseUtente);
		model.addAttribute("elencoDelegati", delegati);

		model.addAttribute("modifica", delega != null && delega.isLegaleRappresentante());

		return LIST;
	}

	/**
	 * @param idPlfImpresa
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secure/formDelegato", method = RequestMethod.GET)
	public String prepareForm(@RequestParam("idPlfImpresa") BigDecimal idPlfImpresa, Model model)
	{
		PLFImpresaEntity impresa = impresaService.find(idPlfImpresa);
		model.addAttribute("formDelegato", new PLFTUtenteEntity());
		model.addAttribute("impresa", impresa);
		model.addAttribute("refreshRelativeUrl", "/secure/gestioneDelegati");

		return FORM;
	}

	/**
	 * @param delegato
	 * @param model
	 * @param idImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/salvaDelegato", method = RequestMethod.POST)
	public String save(@ModelAttribute("formDelegato") PLFTUtenteEntity delegato, Model model, @RequestParam(value = "idPlfImpresa", required = false) BigDecimal idImpresa)
	{

		if (!controlloParametri(delegato, model))
		{
			return prepareForm(idImpresa, model);
		}

		delegatoService.save(delegato, idImpresa);

		return "redirect:/secure/gestioneDelegati";
	}

	/**
	 * @param delegato
	 * @param model
	 * @return
	 */
	private boolean controlloParametri(PLFTUtenteEntity delegato, Model model)
	{
		PLFTUtenteEntity utente = utenteService.findUtenteByCodiceFiscale(delegato.getCodiceFiscale());

		if (utente != null)
		{
			model.addAttribute("errorMessage", "form_delegato.codice_fiscale_esistente");
			return false;
		}

		return true;
	}

	/**
	 * @param model
	 * @param idPlfImpresa
	 * @param idUtente
	 * @return
	 */
	@RequestMapping(value = "/secure/revocaDelegato", method = RequestMethod.GET)
	public String revocaDelegato(Model model, @RequestParam("idPlfImpresa") BigDecimal idPlfImpresa, @RequestParam("idUtente") BigDecimal idUtente)
	{

		delegatoService.revocaDelegato(idPlfImpresa, idUtente);

		return list(model, idPlfImpresa);
	}

	/* REST */

	/**
	 * @param parametri
	 * @return
	 */
	@RequestMapping(value = "/secure/cercaDelegato", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PLFTUtenteEntity> cercaDelegato(@RequestBody Map<String, String> parametri)
	{
		ResponseEntity<PLFTUtenteEntity> response = null;
		BigDecimal idImpresa = BigDecimal.valueOf(Long.parseLong(parametri.get("idPlfImpresa")));
		List<PLFTUtenteEntity> result = delegatoService.searchDelegati(parametri);

		if (result.size() == 1)
		{
			boolean associato = false;
			List<PLFImpresaEntity> imprese = delegatoService.findImpreseDelegato(result.get(0).getIdUtente());
			for (PLFImpresaEntity i : imprese)
			{
				if (idImpresa.equals(i.getIdPlfImpresa()))
				{
					associato = true;
					break;
				}
			}
			HttpStatus status;
			if (associato)
			{
				status = HttpStatus.I_AM_A_TEAPOT;
			}
			else
			{
				status = HttpStatus.OK;
			}
			response = new ResponseEntity<PLFTUtenteEntity>(result.get(0), status);
		}
		else if (result.isEmpty())
		{
			response = new ResponseEntity<PLFTUtenteEntity>(HttpStatus.NO_CONTENT);
		}
		else
		{
			response = new ResponseEntity<PLFTUtenteEntity>(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	/**
	 * @param idUtente
	 * @param idPlfImpresa
	 * @return
	 */
	@RequestMapping(value = "/secure/associaDelegato/{idUtente}/{idPlfImpresa}")
	@ResponseBody
	public ResponseEntity<Object> salvaAssociazione(@PathVariable BigDecimal idUtente, @PathVariable BigDecimal idPlfImpresa)
	{
		ResponseEntity<Object> response = null;

		PLFVDelegatoEntity utente = delegatoService.findDelegato(idUtente, idPlfImpresa);

		if (utente == null)
		{
			delegatoService.saveAssociazione(idUtente, idPlfImpresa);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		}
		else
		{
			response = new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	/*
	 * Ricerca paginata. TODO public Model prepareModelToSearchDelegati(Model
	 * model, BigDecimal idPlfImpresa) {
	 * 
	 * int numeroPagina = 0;
	 * 
	 * CriteriQuery filtri = new CriteriQuery(); PLFVDelegatoKeyEntity
	 * primaryKey = new PLFVDelegatoKeyEntity();
	 * primaryKey.setIdPlfImpresa(idPlfImpresa);
	 * filtri.addParametroEqual("compositePrimaryKey", primaryKey); //FIXME
	 * ottenere il dato dall'attributo interno
	 * 
	 * 
	 * Page<PLFVDelegatoEntity> risultato =
	 * delegatoService.elencoDelegati(numeroPagina, 5, filtri);
	 * List<PLFVDelegatoEntity> lista = risultato.getContent();
	 * ArrayList<Map<String, Object>> elenco = new ArrayList<Map<String,
	 * Object>>(); for (PLFVDelegatoEntity pLFVDelegatoEntity : lista) {
	 * 
	 * @SuppressWarnings("unchecked") Map<String, Object> oggettoMappa = new
	 * org.apache.commons.beanutils.BeanMap(pLFVDelegatoEntity); oggettoMappa =
	 * UtilityStringhe.escapeHtmlValues(oggettoMappa); elenco.add(oggettoMappa);
	 * } model.addAttribute("lista", elenco);
	 * 
	 * model.addAttribute("totRecord", risultato.getTotalElements());
	 * model.addAttribute("totPagine", risultato.getTotalPages());
	 * model.addAttribute("paginaCorrente", risultato.getNumber() + 1); int
	 * numeroRecordPerPagina = 5; model.addAttribute("numeroRecordDa",
	 * (risultato.getNumber() * numeroRecordPerPagina) + 1);
	 * model.addAttribute("numeroRecordA", Math.min((risultato.getNumber() *
	 * numeroRecordPerPagina) + numeroRecordPerPagina,
	 * risultato.getTotalElements()));
	 * 
	 * 
	 * return model; }
	 */

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
}
