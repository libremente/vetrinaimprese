package it.interlogic.vimp.web.rest;

import it.interlogic.vimp.data.jpa.model.PLFTRuoloEntity;
import it.interlogic.vimp.service.ConfigurazioneAmbiente;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.web.AbstractHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RuoloHandler extends AbstractHandler
{

	@Autowired
	IDecodificheService decodificheService;
	
	@Autowired
	ConfigurazioneAmbiente ca;

	@Override
	public String getPageName()
	{
		return "ruoloHandler";
	}
	
	@RequestMapping(value = "/secure/updateRuolo", method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
	@ResponseBody
	public List<PLFTRuoloEntity> updateRuolo(HttpSession session, @RequestBody Map<String, Map<String, String>> payload)
	{
		logger.debug("RuoloHandler::updateRuolo: BEGIN");
		
		Map<String, String> ruolo = (Map<String, String>) payload.get("ruolo");
		
		decodificheService.updateRuolo(new BigDecimal(ruolo.get("ID")), ruolo.get("DESCRIZIONE"));
		
		logger.debug("RuoloHandler::updateRuolo: END");
		
		return decodificheService.getRuolo();
	}

}
