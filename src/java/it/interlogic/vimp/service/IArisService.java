package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;

public interface IArisService
{
	/**
	 * @param codiceFiscalePartitaIva
	 * @return
	 */
	it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(String codiceFiscalePartitaIva);
	
	/**
	 * @param url
	 * @param authorization
	 * @param codiceFiscalePartitaIva
	 * @return
	 */
	it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(String url, String authorization, String codiceFiscalePartitaIva);

	/**
	 * @param codiceFiscalePartitaIva
	 * @return
	 */
	it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult getUL(String codiceFiscalePartitaIva);
	
	/**
	 * @param codiceFiscalePartitaIva
	 * @return
	 */

	it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult getULAll(String codiceFiscalePartitaIva);
	it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult getULAll(String url, String authorization, String codiceFiscalePartitaIva);
	
	/**
	 * @param url
	 * @param authorization
	 * @param codiceFiscalePartitaIva
	 * @return
	 */
	it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult getUL(String url, String authorization,String codiceFiscalePartitaIva);
	
	/**
	 * @param res
	 * @return
	 */
	PLFImpresaEntity arisToImpresa(it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult res);
}
