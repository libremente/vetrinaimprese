package it.interlogic.vimp.web.rest;

import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTProvinciaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRegioneEntity;
import it.interlogic.vimp.service.ConfigurazioneAmbiente;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.web.AbstractHandler;
import it.interlogic.vimp.web.dto.EditableListItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DecodificheHandler extends AbstractHandler
{
	@Autowired
	IDecodificheService decodificheService;
	
	@Autowired
	IServiziService serviziService;
	
	@Autowired
	ConfigurazioneAmbiente ca;

	@Override
	public String getPageName()
	{
		return "decodificheHandler";
	}

	@RequestMapping(value = "/elencoComuni.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoComuni(HttpSession session, @RequestParam(value = "provincia", required = true) String codProvincia)
	{
		try
		{
			List<EditableListItem> list = new ArrayList<EditableListItem>();

			Iterable<PLFTComuneEntity> result = decodificheService.getComuneByCodProvincia(codProvincia);
			if (result != null)
			{
				for (PLFTComuneEntity comune : result)
					list.add(EditableListItem.getInstance(comune.getIdComune(), comune.getDescComune()));

				return list;
			}
			else
			{
				logger.debug("NESSUN DATO");
				return null;
			}
		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/elencoComuni.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoComuniSecure(HttpSession session, @RequestParam(value = "provincia", required = true) String codProvincia)
	{
		return getElencoComuni(session, codProvincia);
	}

	@RequestMapping(value = "/elencoRegioni.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoRegioni(HttpSession session, @RequestParam(value = "nazione", required = true) String codNazione)
	{
		try
		{
			List<EditableListItem> list = new ArrayList<EditableListItem>();
			// list.add(EditableListItem.getInstance("", ""));

			Iterable<PLFTRegioneEntity> result = decodificheService.getRegione();
			if (result != null)
			{
				for (PLFTRegioneEntity regione : result)
					list.add(EditableListItem.getInstance(regione.getCodRegione(), regione.getDescRegione()));

				return list;
			}
			else
			{
				logger.debug("NESSUN DATO");
				return null;
			}
		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/elencoRegioni.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoRegioniSecure(HttpSession session, @RequestParam(value = "nazione", required = true) String codNazione)
	{
		return getElencoRegioni(session, codNazione);
	}

	@RequestMapping(value = "/elencoProvincie.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoProvincie(HttpSession session, @RequestParam(value = "regione", required = true) String codRegione)
	{
		try
		{
			List<EditableListItem> list = new ArrayList<EditableListItem>();
			// list.add(EditableListItem.getInstance("", ""));

			Iterable<PLFTProvinciaEntity> result = decodificheService.getProvinciaByCodRegione(codRegione);
			if (result != null)
			{
				for (PLFTProvinciaEntity provincia : result)
					list.add(EditableListItem.getInstance(provincia.getCodProvincia(), provincia.getDescProvincia()));

				return list;
			}
			else
			{
				logger.debug("NESSUN DATO");
				return null;
			}
		}
		catch (Throwable e)
		{
			throw manageThrowable(e);
		}
	}

	@RequestMapping(value = "/secure/elencoProvincie.json", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public List<EditableListItem> getElencoProvincieSecure(HttpSession session, @RequestParam(value = "regione", required = true) String codRegione)
	{
		return getElencoProvincie(session, codRegione);
	}
	
	@RequestMapping(value = "/secure/elencoCodifiche.json", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getListaCodifiche(HttpSession session, @RequestParam(value = "table", required = true) String table)
	{
		logger.debug("DecodificheHandler::getListaCodifiche: BEGIN");
		
		Map<String, List<Map<String, Object>>> finalMap = new HashMap<String, List<Map<String, Object>>>();
		Set<String> langs = ca.getSelectableLanguages().keySet();
		String defaultLocale = I18nConstants.DEFAULT_LANGUAGE_CODE;
		
		for(String lang : langs) {
			String tableName;
			if(!lang.equals(defaultLocale)) {
				tableName = table + "_" + lang;
			} else {
				tableName = table;
			}
			
			List<Map<String, Object>> dbResult = new ArrayList<Map<String, Object>>();
			
			try {
				dbResult = decodificheService.getCodificheByTable(tableName.toUpperCase());
			} catch(Throwable e) {
				// throw manageThrowable(e);
			}
			
			finalMap.put(tableName.toUpperCase(), dbResult);
		}
		
		logger.debug("DecodificheHandler::getListaCodifiche: END");
		
		return finalMap;
	}
	
	@RequestMapping(value = "/secure/translatedCodificheByid.json", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Map<String, Map<String, Object>> getTranslatedCodificheByid(HttpSession session,
			@RequestParam(value = "table", required = true) String table, @RequestParam(value = "id", required = true) String id)
	{
		logger.debug("DecodificheHandler::getTranslatedCodificheByid: BEGIN");
		
		Map<String, Map<String, Object>> finalMap = new HashMap<String, Map<String, Object>>();
		Set<String> langs = ca.getSelectableLanguages().keySet();
		String defaultLocale = I18nConstants.DEFAULT_LANGUAGE_CODE;
		
		for(String lang : langs) {
			String tableName;
			if(!lang.equals(defaultLocale)) {
				tableName = table + "_" + lang;
			} else {
				tableName = table;
			}
			
			Map<String, Object> dbResult = new HashMap<String, Object>();
			
			try {
				dbResult = decodificheService.getTranslatedCodificheByid(tableName.toUpperCase(), id);
			} catch(Throwable e) {
				// throw manageThrowable(e);
			}
			
			finalMap.put(lang.toUpperCase(), dbResult);
		}

		
		logger.debug("DecodificheHandler::getTranslatedCodificheByid: END");
		
		return finalMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/secure/saveCodifiche", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> saveCodifiche(HttpSession session, @RequestBody Map<String, Object> payload)
	{
		logger.debug("DecodificheHandler::saveCodifiche: BEGIN");

		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		
		int countCheck = ca.getSelectableLanguages().keySet().size();
		String tableName = (String) payload.get("table");
		List<Map<String, Object>> datas = (List<Map<String, Object>>) payload.get("data");
		Map<String, List<String>> errors = decodificheService.checkDuplicates(tableName, datas);

		if(errors.get("errors").size() > 0) {
			List<Map<String, Object>> errorValues = new ArrayList<Map<String, Object>>();
			Map<String, Object> errorMap = new HashMap<String, Object>();

			for(int x = 0; x < errors.get("errors").size(); x++) {
				errorMap.put(String.valueOf(x), errors.get("errors").get(x));
			}

			errorValues.add(errorMap);

			result.put("errorData", errorValues);
		} else {
			decodificheService.saveCodifiche(tableName, datas, countCheck);

			result = getListaCodifiche(session, tableName);
		}
		
		logger.debug("DecodificheHandler::saveCodifiche: END");
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/secure/updateCodifiche", method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> updateCodifiche(HttpSession session, @RequestBody Map<String, Object> payload)
	{
		logger.debug("DecodificheHandler::updateCodifiche: BEGIN");

		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		
		int countCheck = ca.getSelectableLanguages().keySet().size();
		String tableName = (String) payload.get("table");
		List<Map<String, Object>> datas = (List<Map<String, Object>>) payload.get("data");
		Map<String, List<String>> errors = decodificheService.checkDuplicates(tableName, datas);

		if(errors.get("errors").size() > 0) {
			List<Map<String, Object>> errorValues = new ArrayList<Map<String, Object>>();
			Map<String, Object> errorMap = new HashMap<String, Object>();

			for(int x = 0; x < errors.get("errors").size(); x++) {
				errorMap.put(String.valueOf(x), errors.get("errors").get(x));
			}

			errorValues.add(errorMap);

			result.put("errorData", errorValues);

		} else {
			decodificheService.updateCodifiche(tableName, (List<Map<String, Object>>) payload.get("data"), countCheck);

			result = getListaCodifiche(session, tableName);
		}

		logger.debug("DecodificheHandler::updateCodifiche: END");

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/secure/disableCodifiche", method = RequestMethod.DELETE, produces = {"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public Map<String, List<Map<String, Object>>> disabeCodifiche(HttpSession session, @RequestBody Map<String, Object> payload)
	{
		logger.debug("DecodificheHandler::updateCodifiche: BEGIN");
		
		int countCheck = ca.getSelectableLanguages().keySet().size();
		String tableName = (String) payload.get("table");
		
		decodificheService.disableCodifiche(tableName, (List<Map<String, Object>>) payload.get("data"), countCheck);
		
		logger.debug("DecodificheHandler::updateCodifiche: END");
		return getListaCodifiche(session, tableName);
	}
	
	

	@RequestMapping(value = "/secure/checkDisableDemServiziCodifiche", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Integer checkDisableDemServiziCodifiche(HttpSession session, @RequestParam(value = "idDenominazioneServizio", required = true) String idDenominazioneServizio)
	{
		logger.debug("DecodificheHandler::checkDisableDemServiziCodifiche: BEGIN");
		int count = (int)serviziService.countServiziByDenominazione(new BigDecimal(Integer.valueOf(idDenominazioneServizio.trim())));
		logger.debug("DecodificheHandler::checkDisableDemServiziCodifiche: END");
		return count;
	}

}
