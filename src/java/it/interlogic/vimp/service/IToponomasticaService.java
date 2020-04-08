package it.interlogic.vimp.service;

import it.interlogic.vimp.service.ws.toponomastica.Civico;
import it.interlogic.vimp.service.ws.toponomastica.Strada;

import java.util.List;

public interface IToponomasticaService
{
	List<Strada> getStrade(String via);

	List<Civico> getCivici(String codiceStrada);

	Civico getCivico(String codiceStrada, String numero, String lettera, String colore);
	
	Civico getCivico(String query);
}
