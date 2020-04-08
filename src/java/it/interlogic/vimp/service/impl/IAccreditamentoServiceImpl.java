package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFRichiestaAccreditamentoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.repository.PLFRichiestaAccreditamentoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTComuneJpaRepository;
import it.interlogic.vimp.service.IAccreditamentoService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iAccreditamentoService")
public class IAccreditamentoServiceImpl implements IAccreditamentoService
{

	@Autowired
	PLFRichiestaAccreditamentoJpaRepository repository;

	@Autowired
	PLFTComuneJpaRepository repositoryComune;

	@Override
	public List<PLFRichiestaAccreditamentoEntity> find()
	{
		return (List<PLFRichiestaAccreditamentoEntity>) repository.findAll();
	}

	@Override
	public PLFRichiestaAccreditamentoEntity update(PLFRichiestaAccreditamentoEntity dettaglio)
	{
		repository.save(dettaglio);
		return dettaglio;
	}

	@Override
	public PLFRichiestaAccreditamentoEntity find(BigDecimal id)
	{
		PLFRichiestaAccreditamentoEntity result = (PLFRichiestaAccreditamentoEntity) repository.findOne(id);
		return result;
	}

	@Override
	public PLFRichiestaAccreditamentoEntity findRichiesta(String codiceFiscale, String partitaIva)
	{
		if (partitaIva != null && partitaIva.trim().length() > 0)
		{
			List<PLFRichiestaAccreditamentoEntity> resultPartitaIva = repository.findByPartitaIva(partitaIva);
			if (resultPartitaIva != null && resultPartitaIva.size() > 0)
				return resultPartitaIva.get(0);
		}

		if (codiceFiscale != null && codiceFiscale.trim().length() > 0)
		{
			List<PLFRichiestaAccreditamentoEntity> resultCodFiscale = repository.findByCodiceFiscale(codiceFiscale);
			if (resultCodFiscale != null && resultCodFiscale.size() > 0)
				return resultCodFiscale.get(0);
		}
		return null;
	}

	@Override
	public PLFRichiestaAccreditamentoEntity salvaRichiesta(String codiceFiscaleRichiedente, String codiceFiscale, String partitaIva, String email, BigDecimal idStatoImpresa,
			PLFImpresaEntity impresa, int statoRichiesta, int idControllo, boolean flagAccreditamento)
	{
		PLFRichiestaAccreditamentoEntity entity = new PLFRichiestaAccreditamentoEntity();

		if (impresa != null)
		{
			entity.setCodCap(impresa.getCodCap());
			entity.setCodFiscale(impresa.getCodFiscale());
			entity.setDescImpresa(impresa.getImpresaTranslation().getDescImpresa());
			
			
			if (impresa.getDescIndirizzo() != null && impresa.getDescIndirizzo().trim().length()>0)
				entity.setDescIndirizzo(impresa.getDescIndirizzo());
			else
				entity.setDescIndirizzo("  ");
			
			
			
			entity.setElementiInnovazioneAltro(impresa.getImpresaTranslation().getElementiInnovazioneAltro());
			
			if (impresa.getNumeroCivico() != null && impresa.getNumeroCivico().trim().length()>0)
				entity.setNumeroCivico(impresa.getNumeroCivico());
			else
				entity.setNumeroCivico("  ");
			

			entity.setPartitaIva(impresa.getPartitaIva());

			if (impresa.getIdPlfImpresa() != null && impresa.getIdPlfImpresa().intValue() > 0)
			{
				PLFImpresaEntity relImpresa = new PLFImpresaEntity();
				relImpresa.setIdPlfImpresa(impresa.getIdPlfImpresa());
				entity.setPlfImpresa(relImpresa);
			}

			if (impresa.getPlfTComune() != null && impresa.getPlfTComune().getIdComune().intValue() > 0)
			{
				PLFTComuneEntity relComune = new PLFTComuneEntity();
				relComune.setIdComune(impresa.getPlfTComune().getIdComune());
				entity.setPlfTComune(relComune);
			}
			entity.setRagioneSociale(impresa.getImpresaTranslation().getDescImpresa());
			entity.setTelContatto(impresa.getDescTelefono());
		}
		else
		{
			entity.setRagioneSociale("");
			entity.setDescImpresa("");
			entity.setDescIndirizzo("");
			entity.setNumeroCivico("");

			PLFTComuneEntity relComune = new PLFTComuneEntity();
			List<PLFTComuneEntity> list = repositoryComune.findComuneByCodice(IAbstractServiceImpl.COD_COMUNE_GENOVA);
			if (list != null && !list.isEmpty())
				relComune.setIdComune(list.get(0).getIdComune());
			entity.setPlfTComune(relComune);
		}

		if (impresa != null && impresa.getEmailContatto() != null && impresa.getEmailContatto().trim().length() > 0)
			entity.setEmailContatto(impresa.getEmailContatto());
		else
			entity.setEmailContatto(email);

		if (flagAccreditamento)
			entity.setFlagAccreditamento("S");
		else
			entity.setFlagAccreditamento("N");

		entity.setIdStatoRichiesta(new BigDecimal(statoRichiesta));
		entity.setIdControlliRichiesta(new BigDecimal(idControllo));

		entity.setDataRichiesta(new Date());
		entity.setCodFiscaleRichiedente(codiceFiscaleRichiedente);
		PLFTStatoImpresaEntity relStatoImpresa = new PLFTStatoImpresaEntity();
		relStatoImpresa.setId(idStatoImpresa);
		entity.setPlfTStatoImpresa(relStatoImpresa);
		entity.setParereAccreditamento("");
		entity.setFlagPercorsoInnovazione("");
		entity.setPlfTInnovaziones(null);
		
	
		if ((entity.getCodFiscale() == null || entity.getCodFiscale().trim().length()<=0) &&
				(entity.getPartitaIva() == null || entity.getPartitaIva().trim().length()<=0))
		{
			entity.setCodFiscale(codiceFiscale);
			entity.setPartitaIva(partitaIva);
		}
			
			
			
			
			
		
		
		
		return repository.save(entity);
	}

}
