package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.dao.PLFOpportunitaTranslationDao;
import it.interlogic.vimp.data.jpa.model.PLFOpportunitaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFROpportunitaStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFROpportunitaStatoImpresaEntityKey;
import it.interlogic.vimp.data.jpa.repository.PLFOpportunitaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTStatoImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFROpportunitaStatoImpresaJpaRepository;
import it.interlogic.vimp.service.IOpportunitaService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iOpportunitaService")
public class IOpportunitaServiceImpl extends IAbstractServiceImpl implements IOpportunitaService
{

	@Autowired
	PLFOpportunitaJpaRepository repository;

	@Autowired
	PLFOpportunitaTranslationDao translation;

	@Autowired
	PLFROpportunitaStatoImpresaJpaRepository opportunitaStatoImpresaRepository;

	@Autowired
	PLFTStatoImpresaJpaRepository statoImpresaRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Override
	public List<PLFOpportunitaEntity> find()
	{
		return (List<PLFOpportunitaEntity>) repository.findAll();
	}

	@Override
	public PLFOpportunitaEntity find(BigDecimal id)
	{
		PLFOpportunitaEntity result = (PLFOpportunitaEntity) repository.findOne(id);
		if (result != null)
			super.updateAttibuti(result.getIdPlfOpportunita(), IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA);
		return result;
	}

	@Override
	public PLFOpportunitaEntity update(PLFOpportunitaEntity dettaglio) throws InformazioneDuplicataException
	{
		if (dettaglio.getIdPlfOpportunita() == null || dettaglio.getIdPlfOpportunita().intValue() <= 0)
		{
			dettaglio.setIdPlfOpportunita(null);
		}

		/*
		 * List<PLFOpportunitaEntity> result =
		 * repository.findOpportunitaByDescrizione(dettaglio.getDescNome()); if
		 * (result != null && result.size() > 0) { if (result.size() > 1) throw
		 * new InformazioneDuplicataException();
		 * 
		 * PLFOpportunitaEntity e = result.get(0); if
		 * (dettaglio.getIdPlfOpportunita() != null &&
		 * e.getIdPlfOpportunita().intValue() !=
		 * dettaglio.getIdPlfOpportunita().intValue()) throw new
		 * InformazioneDuplicataException(); }
		 */
		translation.saveOrUpdateTranslation(dettaglio);

		updateRTags(dettaglio);

		return dettaglio;
	}

	private void updateRTags(PLFOpportunitaEntity dettaglio)
	{
		// tags
		List<BigDecimal> toDelete = new ArrayList<BigDecimal>();
		List<BigDecimal> toInsert = new ArrayList<BigDecimal>();

		if (dettaglio.getIdPlfOpportunita() != null)
		{
			List<BigInteger> previous = informazioneTagJpaRepository.findIdsByInformazioneAndType(dettaglio.getIdPlfOpportunita(), BigDecimal.valueOf(TIPO_INFO_OPPORTUNITA));

			if (previous != null && dettaglio.getElencoIdTags() != null)
			{
				for (BigDecimal tag : dettaglio.getElencoIdTags())
				{
					// controllo se esiste già a db
					boolean found = false;
					for (BigInteger id : previous)
					{
						if (new BigDecimal(id).equals(tag))
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						toInsert.add(tag);
					}
				}
			}

			if (previous != null && dettaglio.getElencoIdTags() != null)
			{
				for (BigInteger id : previous)
				{
					// controllo se esiste sul db ma non nell'oggetto
					boolean found = false;
					for (BigDecimal tag : dettaglio.getElencoIdTags())
					{
						if (new BigDecimal(id).equals(tag))
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						toDelete.add(new BigDecimal(id));
					}
				}
			}

		}
		else
		{
			if (dettaglio.getElencoIdTags() != null)
			{
				toInsert.addAll(dettaglio.getElencoIdTags());
			}
		}

		for (BigDecimal id : toDelete)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfOpportunita(), BigDecimal.valueOf(TIPO_INFO_OPPORTUNITA), id);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.delete(entity);
		}

		for (BigDecimal tag : toInsert)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfOpportunita(), BigDecimal.valueOf(TIPO_INFO_OPPORTUNITA), tag);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.save(entity);
		}
	}

	@Override
	public PLFOpportunitaEntity delete(PLFOpportunitaEntity dettaglio)
	{
		if (dettaglio.getIdPlfOpportunita() == null || dettaglio.getIdPlfOpportunita().intValue() > 0)
		{
			dettaglio.setDataCancellazione(new Date());
			repository.save(dettaglio);
		}
		return dettaglio;
	}

	@Override
	public PLFROpportunitaStatoImpresaEntity findCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntityKey opportunitaStatoImpresa)
	{
		return opportunitaStatoImpresaRepository.findOne(opportunitaStatoImpresa);
	}

	@Override
	public void salvaCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa)
	{
		opportunitaStatoImpresaRepository.save(opportunitaStatoImpresa);
	}

	@Override
	public void cancellaCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa)
	{
		opportunitaStatoImpresaRepository.delete(opportunitaStatoImpresa);
	}

	@Override
	public List<PLFTStatoImpresaEntity> loadOpportunitaStatoImpresa(BigDecimal idOpportunita)
	{
		return statoImpresaRepository.findByIdOpportunita(idOpportunita);
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA);
	}

	@Override
	public void updateImage(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA, image);
	}

	@Override
	public void deleteImage(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA);
	}
}
