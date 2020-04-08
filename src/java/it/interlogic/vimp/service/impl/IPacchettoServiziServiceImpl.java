package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.dao.PLFPacchettoServiziTranslationDao;
import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.repository.PLFPacchettoServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRPacchettoServiziJpaRepository;
import it.interlogic.vimp.service.IPacchettoServiziService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.utils.EntityUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iPacchettoServiziService")
public class IPacchettoServiziServiceImpl extends IAbstractServiceImpl implements IPacchettoServiziService
{
	@Autowired
	PLFPacchettoServiziJpaRepository repository;

	@Autowired
	PLFPacchettoServiziTranslationDao translation;

	@Autowired
	PLFServiziJpaRepository serviziRepository;

	@Autowired
	PLFRPacchettoServiziJpaRepository pacchettoServiziRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Override
	public PLFPacchettoServiziEntity find(BigDecimal id)
	{
		PLFPacchettoServiziEntity result = repository.findOne(id);

		if (result != null)
		{
			super.updateAttibuti(id, TIPO_INFO_PACCHETTO_SERVIZI);
		}
		result.setServizi(serviziRepository.findByPacchetto(id));
		result.setElencoServizi(EntityUtility.toBigint(serviziRepository.findIdsByPacchetto(id)));

		return result;
	}

	@Override
	public PLFPacchettoServiziEntity update(PLFPacchettoServiziEntity dettaglio) throws InformazioneDuplicataException
	{

		if (dettaglio.getIdPacchettoServizi() == null || dettaglio.getIdPacchettoServizi().intValue() <= 0)
		{
			dettaglio.setIdPacchettoServizi(null);
		}

		translation.saveOrUpdateTranslation(dettaglio);

		updateServizi(dettaglio);

		updateRTags(dettaglio);

		return dettaglio;
	}

	private void updateRTags(PLFPacchettoServiziEntity dettaglio)
	{
		// tags
		List<BigInteger> previous = informazioneTagJpaRepository.findIdsByInformazioneAndType(dettaglio.getIdPacchettoServizi(), BigDecimal.valueOf(TIPO_INFO_PACCHETTO_SERVIZI));
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
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPacchettoServizi(), BigDecimal.valueOf(TIPO_INFO_PACCHETTO_SERVIZI), id);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.delete(entity);
		}

		for (BigDecimal tag : toInsert)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPacchettoServizi(), BigDecimal.valueOf(TIPO_INFO_PACCHETTO_SERVIZI), tag);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.save(entity);
		}
	}

	private void updateServizi(PLFPacchettoServiziEntity pacchetto)
	{
		List<BigDecimal> oldServizi = serviziRepository.findIdsByPacchetto(pacchetto.getIdPacchettoServizi());
		List<BigInteger> newPacchettoServizi = pacchetto.getElencoServizi();

		Map<BigDecimal, Boolean> mergedValues = EntityUtility.mergeValues(oldServizi, newPacchettoServizi);

		for (Map.Entry<BigDecimal, Boolean> e : mergedValues.entrySet())
		{
			PLFRPacchettoServiziEntity ass = new PLFRPacchettoServiziEntity(e.getKey(), pacchetto.getIdPacchettoServizi());
			if (e.getValue())
			{
				pacchettoServiziRepository.save(ass);
			}
			else
			{
				pacchettoServiziRepository.delete(ass);
			}
		}
	}

	@Override
	public PLFPacchettoServiziEntity delete(PLFPacchettoServiziEntity dettaglio)
	{
		if (dettaglio.getIdPacchettoServizi() == null || dettaglio.getIdPacchettoServizi().intValue() > 0)
		{
			dettaglio.setDataCancellazione(new Date());
			repository.save(dettaglio);
		}
		return dettaglio;
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI);
	}

	@Override
	public void updateImage(BigDecimal idInformazione,String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI, image);
	}

	@Override
	public void deleteImage(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI);
	}

	@Override
	public List<PLFPacchettoServiziEntity> findByIdsServizi(List<BigDecimal> elencoServizi)
	{
		return repository.findByIdsServizi(elencoServizi, elencoServizi.size());
	}

	@Override
	public List<PLFPacchettoServiziEntity> findByIdsServiziCompleto(List<BigDecimal> elencoServizi)
	{
		return repository.findByIdsServiziCompleto(elencoServizi);
	}

	@Override
	public List<PLFPacchettoServiziEntity> findByServizio(BigDecimal idServizi)
	{
		return repository.findByServizio(idServizi);
	}

	@Override
	public List<PLFPacchettoServiziEntity> findAttiviByServizio(BigDecimal idServizi)
	{
		return repository.findAttiviByServizio(idServizi);
	}

}
