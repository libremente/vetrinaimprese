package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.dao.PLFImpresaAllegatiTranslationDao;
import it.interlogic.vimp.data.dao.PLFImpresaTranslationDao;
import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaStakeholderEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntityKey;
import it.interlogic.vimp.data.jpa.repository.PLFCollaborazioniJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaAllegatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaAllegatiTranslationJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTInnovazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTMercatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRImpresaInnovazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRImpresaMercatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRImpresaStakeholderJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRServiziImpresaJpaRepository;
import it.interlogic.vimp.service.IImpresaService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.utils.LoggerUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iImpresaServiceImpl")
public class IImpresaServiceImpl extends IAbstractServiceImpl implements IImpresaService
{

	@Autowired
	PLFImpresaJpaRepository repository;

	@Autowired
	PLFImpresaTranslationDao translation;

	@Autowired
	PLFImpresaAllegatiTranslationDao allegatiTranslation;

	@Autowired
	PLFRServiziImpresaJpaRepository serviziImpresaRepository;

	@Autowired
	PLFCollaborazioniJpaRepository collaborazioniRepository;

	@Autowired
	PLFTMercatiJpaRepository mercatiRepository;

	@Autowired
	PLFRImpresaMercatiJpaRepository impresaMercatiRepository;

	@Autowired
	PLFTInnovazioneJpaRepository innovazioneRepository;

	@Autowired
	PLFRImpresaInnovazioneJpaRepository impresaInnovazioneRepository;

	@Autowired
	PLFRImpresaStakeholderJpaRepository impresaStakeholderRepository;

	@Autowired
	PLFImpresaAllegatiJpaRepository impresaAllegatiRepository;

	@Autowired
	PLFImpresaAllegatiTranslationJpaRepository impresaAllegatiTranslationRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Override
	public List<PLFImpresaEntity> find()
	{
		return (List<PLFImpresaEntity>) repository.findAll();
	}

	@Override
	public PLFImpresaEntity find(BigDecimal id)
	{
		PLFImpresaEntity result = (PLFImpresaEntity) repository.findOne(id);
		if (result != null)
			super.updateAttibuti(result.getIdPlfImpresa(), IAbstractServiceImpl.TIPO_INFO_IMPRESA);
		return result;
	}

	@Override
	public PLFImpresaEntity find(String partitaIva, String codiceFiscale)
	{
		List<PLFImpresaEntity> result = null;

		if (partitaIva != null && partitaIva.trim().length() > 0)
		{
			result = repository.findByPartitaIva(partitaIva);
			if (result != null && result.size() > 0 && result.get(0).getIdPlfImpresa() != null && result.get(0).getIdPlfImpresa().intValue() > 0)
				return result.get(0);
		}
		if (codiceFiscale != null && codiceFiscale.trim().length() > 0)
		{
			result = repository.findByCodiceFiscale(codiceFiscale);
			if (result != null && result.size() > 0 && result.get(0).getIdPlfImpresa() != null && result.get(0).getIdPlfImpresa().intValue() > 0)
				return result.get(0);
		}

		return null;
	}

	@Override
	public PLFImpresaEntity update(PLFImpresaEntity dettaglio) throws InformazioneDuplicataException
	{
		if (dettaglio.getIdPlfImpresa() == null || dettaglio.getIdPlfImpresa().intValue() <= 0)
		{
			dettaglio.setIdPlfImpresa(null);
			List<PLFImpresaEntity> result = repository.findByDescImpresa(dettaglio.getImpresaTranslation().getDescImpresa());
			LoggerUtility.info(result.toString());
			if (result != null && result.size() > 0)
			{
				if (result.size() > 1)
					throw new InformazioneDuplicataException();

				PLFImpresaEntity e = result.get(0);
				if (dettaglio.getIdPlfImpresa() != null && e.getIdPlfImpresa().intValue() != dettaglio.getIdPlfImpresa().intValue())
					throw new InformazioneDuplicataException();
			}
		}

		updateRTags(dettaglio);

		translation.saveOrUpdateTranslation(dettaglio);

		return dettaglio;
	}

	private void updateRTags(PLFImpresaEntity dettaglio)
	{
		// tags
		List<BigInteger> previous = informazioneTagJpaRepository.findIdsByInformazioneAndType(dettaglio.getIdPlfImpresa(), BigDecimal.valueOf(dettaglio.getTipoInformazione()));
		List<BigDecimal> toDelete = new ArrayList<BigDecimal>();
		List<BigDecimal> toInsert = new ArrayList<BigDecimal>();

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

		for (BigDecimal id : toDelete)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfImpresa(), BigDecimal.valueOf(dettaglio.getTipoInformazione()), id);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.delete(entity);
		}

		for (BigDecimal tag : toInsert)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfImpresa(), BigDecimal.valueOf(dettaglio.getTipoInformazione()), tag);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.save(entity);
		}
	}

	@Override
	public PLFImpresaEntity updateImpresa(PLFImpresaEntity dettaglio)
	{
		translation.saveOrUpdateTranslation(dettaglio);
		return dettaglio;
	}

	@Override
	public List<PLFImpresaEntity> loadImpreseServizi(BigDecimal idServizio)
	{
		List<PLFImpresaEntity> list = repository.findByIdServizi(idServizio);
		if (list != null && list.size() > 0)
		{
			for (PLFImpresaEntity impresa : list)
			{
				PLFRServiziImpresaEntity ss = serviziImpresaRepository.findOne(new PLFRServiziImpresaEntityKey(idServizio, impresa.getIdPlfImpresa()));
				if (ss != null && ss.getLinkCollegamentoImpresa() != null)
				{
					if (ss.getLinkCollegamentoImpresa().startsWith("http"))
						impresa.setLinkServizioStandard(ss.getLinkCollegamentoImpresa());
					else
						impresa.setLinkServizioStandard("http://" + ss.getLinkCollegamentoImpresa());
				}
			}
		}
		return list;
	}

	@Override
	public List<PLFCollaborazioniEntity> loadCollaborazioniByIdProgetto(BigDecimal idProgetto)
	{
		return collaborazioniRepository.findCollaborazioniByIdProgetto(idProgetto);
	}

	@Override
	public List<PLFCollaborazioniEntity> loadCollaborazioniByIdImpresa(BigDecimal idImpresa)
	{
		return collaborazioniRepository.findCollaborazioniByIdImpresa(idImpresa);
	}

	@Override
	public List<PLFTMercatiEntity> loadMercati(BigDecimal idImpresa)
	{
		return mercatiRepository.findByIdImpresa(idImpresa);

	}

	@Override
	public List<PLFTInnovazioneEntity> loadInnovazioni(BigDecimal idImpresa)
	{
		return innovazioneRepository.findByIdImpresa(idImpresa);
	}

	@Override
	public List<PLFImpresaEntity> loadImpreseStakeholderCollegate(BigDecimal idStakeholder)
	{
		return repository.findImpreseStakeholderCollegate(idStakeholder);
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_IMPRESA);
	}

	@Override
	public void updateImageImpresa(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_IMPRESA, image);
	}

	@Override
	public void updateImageStakeholder(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER, image);
	}

	@Override
	public void deleteImageImpresa(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER);
	}

	@Override
	public void deleteImageStakeholder(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_IMPRESA);
	}

	@Override
	public void salvaCollegamentoImpresaInnovazione(PLFRImpresaInnovazioneEntity impresaInnovazione) throws Exception
	{
		PLFRImpresaInnovazioneEntity entity = impresaInnovazioneRepository.findOne(impresaInnovazione.getCompositePrimaryKey());
		if (entity != null && entity.getIdInnovazione() != null && entity.getIdInnovazione().intValue() > 0)
			throw new Exception("Duplicate key");
		impresaInnovazioneRepository.save(impresaInnovazione);
	}

	@Override
	public void cancellaCollegamentoImpresaInnovazione(PLFRImpresaInnovazioneEntity impresaInnovazione) throws Exception
	{
		impresaInnovazioneRepository.delete(impresaInnovazione);
	}

	@Override
	public void salvaCollegamentoImpresaMercati(PLFRImpresaMercatiEntity impresaMercati) throws Exception
	{
		PLFRImpresaMercatiEntity entity = impresaMercatiRepository.findOne(impresaMercati.getCompositePrimaryKey());
		if (entity != null && entity.getIdMercato() != null && entity.getIdMercato().intValue() > 0)
			throw new Exception("Duplicate key");
		impresaMercatiRepository.save(impresaMercati);
	}

	@Override
	public void cancellaCollegamentoImpresaMercati(PLFRImpresaMercatiEntity impresaMercati) throws Exception
	{
		impresaMercatiRepository.delete(impresaMercati);
	}

	@Override
	public List<PLFImpresaEntity> loadStakeholder(BigDecimal idImpresa)
	{
		return repository.findStakeholderByIdImpresa(idImpresa);
	}

	@Override
	public List<PLFImpresaEntity> loadImpreseCollegate(BigDecimal idStakeholder)
	{
		return repository.findImpreseByIdStakeholder(idStakeholder);
	}

	@Override
	public List<PLFImpresaEntity> getStakeholders()
	{
		return repository.findStakeholder();
	}

	@Override
	public List<PLFImpresaEntity> getImprese()
	{
		return repository.findImprese();
	}

	@Override
	public void salvaCollegamentoImpresaStakeholder(PLFRImpresaStakeholderEntity impresaStakeholder) throws Exception
	{
		impresaStakeholderRepository.save(impresaStakeholder);
	}

	@Override
	public void cancellaCollegamentoImpresaStakeholder(PLFRImpresaStakeholderEntity impresaStakeholder) throws Exception
	{
		impresaStakeholderRepository.delete(impresaStakeholder);
	}

	@Override
	public List<PLFImpresaAllegatiEntity> loadAllegati(BigDecimal idImpresa)
	{
		return impresaAllegatiRepository.findByImpresa(idImpresa);
	}

	@Override
	public void cancellaAllegato(PLFImpresaAllegatiEntity allegato)
	{
		allegatiTranslation.deleteAllegatoTranslation(allegato);
		// impresaAllegatiRepository.delete(allegato);
	}

	@Override
	public PLFImpresaAllegatiEntity salvaAllegato(PLFImpresaAllegatiEntity allegato)
	{
		// return impresaAllegatiRepository.save(allegato);
		allegatiTranslation.saveOrUpdateAllegato(allegato);
		return allegato;
	}

	@Override
	public PLFImpresaAllegatiEntity findAllegato(BigDecimal idAllegato)
	{
		return impresaAllegatiRepository.findOne(idAllegato);
	}

	@Override
	public PLFImpresaAllegatiTranslationEntity salvaTranslationAllegato(PLFImpresaAllegatiTranslationEntity translation)
	{
		return impresaAllegatiTranslationRepository.save(translation);
	}

}
