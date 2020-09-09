package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.service.IArisService;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.service.ws.aris.uisearch.ResultElement;
import it.interlogic.vimp.utils.LoggerUtility;
import it.interlogic.vimp.web.security.AmbienteContext;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iArisService")
public class IArisServiceImpl implements IArisService
{

	@Autowired
	protected IDecodificheService decodificaService;

	@Override
	public it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult getUL(String codiceFiscalePartitaIva)
	{
		return getUL(AmbienteContext.getWsArisUiSearchUrl(), AmbienteContext.getWsArisAuthorization(), codiceFiscalePartitaIva);
	}

	@Override
	public it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult getUL(String url, String authorization, String codiceFiscalePartitaIva)
	{
		it.interlogic.vimp.service.ws.aris.uisearch.EnterprisePortTypeProxy service = new it.interlogic.vimp.service.ws.aris.uisearch.EnterprisePortTypeProxy(url);
		service.addToHeader("Authorization", authorization);

		LoggerUtility.info("ARIS getUL(url:"+ url +", "+ "codiceFiscalePartitaIva:" + codiceFiscalePartitaIva + ")");
		try
		{
			it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult res = service.getUL(codiceFiscalePartitaIva);
			return res;
		}
		catch (RemoteException e)
		{
			LoggerUtility.error("ARIS getUL error:RemoteException " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult getULAll(String codiceFiscalePartitaIva)
	{
		return getULAll(AmbienteContext.getWsArisUiSearchAllUrl(), AmbienteContext.getWsArisAuthorization(), codiceFiscalePartitaIva);
	}
	
	@Override
	public it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult getULAll(String url, String authorization, String codiceFiscalePartitaIva)
	{
		it.interlogic.vimp.service.ws.aris.uisearchall.EnterprisePortTypeProxy service = new it.interlogic.vimp.service.ws.aris.uisearchall.EnterprisePortTypeProxy(url);
		service.addToHeader("Authorization", authorization);

		LoggerUtility.info("ARIS getULAll(url:"+ url +", "+ "codiceFiscalePartitaIva:" + codiceFiscalePartitaIva + ")");
		try
		{
			it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult res = service.getULAll(codiceFiscalePartitaIva);
			return res;
		}
		catch (RemoteException e)
		{
			LoggerUtility.error("ARIS getULAll error:RemoteException " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(String codiceFiscalePartitaIva)
	{
		return getMultiRL(AmbienteContext.getWsArisRlMultiSearchUrl(), AmbienteContext.getWsArisAuthorization(), codiceFiscalePartitaIva);
	}

	@Override
	public it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(String url, String authorization, String codiceFiscalePartitaIva)
	{
		it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortTypeProxy service = new it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortTypeProxy(url);
		service.addToHeader("Authorization", authorization);

		
		LoggerUtility.info("ARIS getMultiRL(url:"+ url +", "+ "codiceFiscalePartitaIva:" + codiceFiscalePartitaIva + ")");
		
		
		try
		{
			it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult res = service.getMultiRL(codiceFiscalePartitaIva, "", "", "si");
			return res;
		}
		catch (RemoteException e)
		{
			LoggerUtility.error("ARIS getMultiRL error:RemoteException " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PLFImpresaEntity arisToImpresa(it.interlogic.vimp.service.ws.aris.uisearch.UlSearchResult res)
	{
		PLFImpresaEntity impresa = null;

		if (res != null)
		{
			impresa = new PLFImpresaEntity();
			impresa.setCodFiscale(res.getC_fiscale_impresa());

			String ragioneSociale = res.getDenominazione_sede();
			if (ragioneSociale != null)
				ragioneSociale = ragioneSociale.replace("\"", "");
			
			if (impresa.getImpresaTranslation() == null)
				impresa.setImpresaTranslation(new PLFImpresaTranslationEntity());

			impresa.getImpresaTranslation().setDescImpresa(ragioneSociale);
			impresa.setPartitaIva(res.getPartita_iva());

			for (ResultElement sedi : res.getResultUnitaLocali())
			{
				if ("0".equals(sedi.getPro_localizzazione()))
				{
					List<PLFTComuneEntity> listComuni = decodificaService.getComuneByCodice(sedi.getC_comune());
					if (listComuni != null && listComuni.size() > 0)
					{
						PLFTComuneEntity relComune = new PLFTComuneEntity();
						relComune.setIdComune(listComuni.get(0).getIdComune());
						impresa.setPlfTComune(relComune);
					}

					impresa.setCodCap(sedi.getCap());
					if (sedi.getC_via() != null)
						impresa.setDescIndirizzo(sedi.getC_via() + " " + sedi.getVia());
					impresa.getImpresaTranslation().setElementiInnovazioneAltro("");
					impresa.setNumeroCivico(sedi.getN_civico());
					impresa.setDescTelefono(sedi.getN_telefono());

					break;
				}
			}
		}

		return impresa;
	}

}
