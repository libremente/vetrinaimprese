package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.dao.PLFServiziTranslationDao;
import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntityKey;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziMacroareaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziTipoErogazioneEntity;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFPacchettoServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTMacroareaServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoErogazioneServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRPacchettoServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRServiziImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRServiziMacroareaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRServiziTipoErogazioneJpaRepository;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.utils.EntityUtility;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iServiziService")
public class IServiziServiceImpl extends IAbstractServiceImpl implements IServiziService
{

	@Autowired
	PLFServiziJpaRepository repository;

	@Autowired
	PLFServiziTranslationDao translation;

	@Autowired
	PLFImpresaJpaRepository impresaRepository;

	@Autowired
	PLFRServiziImpresaJpaRepository impresaServiziRepository;

	@Autowired
	PLFTTipoErogazioneServizioJpaRepository tipoErogazioneServizioRepository;

	@Autowired
	PLFTMacroareaServizioJpaRepository macroareaServizioRepository;

	@Autowired
	PLFRServiziTipoErogazioneJpaRepository serviziTipoErogazioneRepository;

	@Autowired
	PLFRServiziMacroareaJpaRepository serviziMacroareaRepository;

	@Autowired
	PLFRPacchettoServiziJpaRepository pacchettoServiziJpaRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Autowired
	PLFPacchettoServiziJpaRepository pacchettoJpaRepository;

	@Override
	public List<PLFServiziEntity> find()
	{
		return (List<PLFServiziEntity>) repository.findAll();
	}

	@Override
	public List<PLFServiziEntity> findAttivi()
	{
		List<PLFServiziEntity> servizi = (List<PLFServiziEntity>) repository.findAllAttivi();
		if (servizi != null)
		{
			for (PLFServiziEntity servizio : servizi)
			{
				// if ("S".equalsIgnoreCase(servizio.getServiziStandard()))
				servizio.setPlfImpresas(impresaRepository.findByIdServizi(servizio.getIdServizi()));
			}
		}

		return servizi;
	}

	@Override
	public PLFServiziEntity find(BigDecimal id)
	{
		PLFServiziEntity result = repository.findOne(id);
		if (result != null)
			super.updateAttibuti(result.getIdServizi(), IAbstractServiceImpl.TIPO_INFO_SERVIZIO);

		result.setPlfImpresas(impresaRepository.findByIdServizi(id));

		result.setElencoTipoErogazione(EntityUtility.toBigint(tipoErogazioneServizioRepository.findIdsByServizio(id)));
		result.setTipiErogazione(tipoErogazioneServizioRepository.findByServizio(id));

		result.setElencoMacroarea(EntityUtility.toBigint(macroareaServizioRepository.findIdsByServizio(id)));
		result.setMacroaree(macroareaServizioRepository.findByServizio(id));

		return result;
	}

	@Override
	public List<PLFServiziEntity> findByImpresa(BigDecimal id)
	{
		return repository.findByIdImpresa(id);
	}

	@Override
	// /*☺ Uncomment at your own risk */ @Transactional
	public PLFServiziEntity update(PLFServiziEntity dettaglio) throws InformazioneDuplicataException
	{

		boolean nuovoRecord = false;

		boolean duplicateFound = false;
		if (dettaglio.getIdServizi() == null || dettaglio.getIdServizi().intValue() <= 0)
		{
			dettaglio.setIdServizi(null);
			nuovoRecord = true;
		}

		List<PLFServiziEntity> result = repository.findByTitolo(dettaglio.getDenominazioneCalcolata());

		if (dettaglio.getIdServizi() == null)
		{
			if (result != null && result.size() > 0)
			{
				duplicateFound = true;
			}
		}
		else
		{
			if (result != null && result.size() > 0)
			{
				for (PLFServiziEntity servizio : result)
				{
					if (!servizio.getIdServizi().equals(dettaglio.getIdServizi()))
					{
						duplicateFound = true;
						break;
					}
				}
			}
		}

		if (duplicateFound)
		{
			throw new InformazioneDuplicataException();
		}
		else
		{
			translation.saveOrUpdateTranslation(dettaglio);

			if (UtenteContext.getCurrentUser() != null)
			{
				PLFRServiziImpresaEntity relation = new PLFRServiziImpresaEntity();

				if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
					relation.setIdImpresa(dettaglio.getPlfImpresa().getIdPlfImpresa());
				else
					relation.setIdImpresa(UtenteContext.getCurrentUser().getPlfImpresas().get(0).getIdPlfImpresa());

				relation.setIdServizi(dettaglio.getIdServizi());
				impresaServiziRepository.save(relation);

				updateTipoErogazione(dettaglio);
				updateMacroarea(dettaglio);
			}
			else if (nuovoRecord) // For batch loading only
			{
				PLFRServiziImpresaEntity relation = new PLFRServiziImpresaEntity();

				if (dettaglio.getPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa() != null && dettaglio.getPlfImpresa().getIdPlfImpresa().intValue() > 0)
				{
					relation.setIdImpresa(dettaglio.getPlfImpresa().getIdPlfImpresa());
					relation.setIdServizi(dettaglio.getIdServizi());
					impresaServiziRepository.save(relation);
				}
				else
					throw new RuntimeException("For batch loading only");

				if (dettaglio.getElencoTipoErogazione() != null)
					updateTipoErogazione(dettaglio);

				if (dettaglio.getElencoMacroarea() != null)
					updateMacroarea(dettaglio);
			}

		}

		updateRTags(dettaglio);

		return dettaglio;
	}

	private void updateRTags(PLFServiziEntity dettaglio)
	{
		// tags
		List<BigInteger> previous = informazioneTagJpaRepository.findIdsByInformazioneAndType(dettaglio.getIdServizi(), BigDecimal.valueOf(TIPO_INFO_SERVIZIO));
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
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdServizi(), BigDecimal.valueOf(TIPO_INFO_SERVIZIO), id);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.delete(entity);
		}

		for (BigDecimal tag : toInsert)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdServizi(), BigDecimal.valueOf(TIPO_INFO_SERVIZIO), tag);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.save(entity);
		}
	}

	private void updateTipoErogazione(PLFServiziEntity servizio)
	{
		List<BigDecimal> oldTipiErogazione = tipoErogazioneServizioRepository.findIdsByServizio(servizio.getIdServizi());
		List<BigInteger> newTipiErogazione = servizio.getElencoTipoErogazione();

		Map<BigDecimal, Boolean> mergedValues = EntityUtility.mergeValues(oldTipiErogazione, newTipiErogazione);

		for (Entry<BigDecimal, Boolean> e : mergedValues.entrySet())
		{
			PLFRServiziTipoErogazioneEntity ass = new PLFRServiziTipoErogazioneEntity(servizio.getIdServizi(), e.getKey());
			if (e.getValue())
			{
				serviziTipoErogazioneRepository.save(ass);
			}
			else
			{
				serviziTipoErogazioneRepository.delete(ass);

			}
		}
	}

	private void updateMacroarea(PLFServiziEntity servizio)
	{
		List<BigDecimal> oldMacroarea = macroareaServizioRepository.findIdsByServizio(servizio.getIdServizi());
		List<BigInteger> newMacroarea = servizio.getElencoMacroarea();

		Map<BigDecimal, Boolean> mergedValues = EntityUtility.mergeValues(oldMacroarea, newMacroarea);

		for (Entry<BigDecimal, Boolean> e : mergedValues.entrySet())
		{
			PLFRServiziMacroareaEntity ass = new PLFRServiziMacroareaEntity(servizio.getIdServizi(), e.getKey());
			if (e.getValue())
			{
				serviziMacroareaRepository.save(ass);
			}
			else
			{
				serviziMacroareaRepository.delete(ass);

			}
		}
	}

	@Override
	public PLFServiziEntity delete(PLFServiziEntity dettaglio)
	{
		if (dettaglio.getIdServizi() == null || dettaglio.getIdServizi().intValue() > 0)
		{
			dettaglio.setDataCancellazione(new Date());
			repository.save(dettaglio);

			try
			{
				List<PLFRPacchettoServiziEntity> pacchetti = pacchettoServiziJpaRepository.findRPacchettoServiziByServizio(dettaglio.getIdServizi());
				// pacchettoServiziJpaRepository.deleteRPacchettoServiziByServizio(dettaglio.getIdServizi());

				if (pacchetti != null)
				{
					for (PLFRPacchettoServiziEntity pacchetto : pacchetti)
					{
						pacchettoServiziJpaRepository.delete(pacchetto);
					}

					for (PLFRPacchettoServiziEntity pacchetto : pacchetti)
					{
						List<PLFRPacchettoServiziEntity> servizi = pacchettoServiziJpaRepository.findRPacchettoServiziByPacchetto(pacchetto.getIdPacchettoServizi());
						if (servizi == null || servizi.size() < 2)
						{
							PLFPacchettoServiziEntity pacchettoDaSpubblicare = pacchettoJpaRepository.findOne(pacchetto.getIdPacchettoServizi());
							if (pacchettoDaSpubblicare != null)
							{
								pacchettoDaSpubblicare.setPubblicato(false);
								pacchettoJpaRepository.save(pacchettoDaSpubblicare);
							}

						}
					}
				}

			}
			catch (Exception err)
			{
				// il servizio non e' contenuto in nessun pacchetto
			}
		}
		return dettaglio;
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_SERVIZIO);
	}

	@Override
	public void updateImage(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_SERVIZIO, image);
	}

	@Override
	public void deleteImage(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_SERVIZIO);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_SERVIZIO);
	}

	@Override
	public List<PLFServiziEntity> findAllByImpresa(BigDecimal idImpresa)
	{
		return repository.findAllByImpresa(idImpresa);
	}

	@Override
	public List<PLFServiziEntity> loadServiziByImpresa(BigDecimal idImpresa, boolean pubblicati)
	{
		List<PLFServiziEntity> servizi = null;

		if (pubblicati)
			servizi = repository.findByIdImpresaPubblicati(idImpresa);
		else
			servizi = repository.findByIdImpresa(idImpresa);

		if (servizi != null)
		{
			PLFRServiziImpresaEntityKey key = new PLFRServiziImpresaEntityKey();
			for (PLFServiziEntity servizio : servizi)
			{
				key.setIdImpresa(idImpresa);
				key.setIdServizi(servizio.getIdServizi());
				PLFRServiziImpresaEntity en = impresaServiziRepository.findOne(key);
				if (en != null)
					servizio.setLinkCollegamentoImpresa(en.getLinkCollegamentoImpresa());
			}
		}

		return servizi;
	}

	@Override
	public List<PLFServiziEntity> loadServiziAttiviByImpresa(BigDecimal idImpresa, boolean pubblicati)
	{
		List<PLFServiziEntity> serviziAttivi = new ArrayList<PLFServiziEntity>();
		List<PLFServiziEntity> servizi = loadServiziByImpresa(idImpresa, pubblicati);
		Date now = new Date();

		for (PLFServiziEntity servizio : servizi)
		{
			Date dataFine = servizio.getDataFine();
			if (dataFine == null || dataFine.after(now))
			{
				serviziAttivi.add(servizio);
			}
		}

		return serviziAttivi;
	}

	@Override
	public PLFServiziEntity loadServizioStandardForImpresa(BigDecimal idServizioStandard, BigDecimal idImpresa)
	{
		PLFServiziEntity servizio = repository.findOne(idServizioStandard);
		if (servizio != null)
		{
			PLFRServiziImpresaEntityKey key = new PLFRServiziImpresaEntityKey();
			key.setIdImpresa(idImpresa);
			key.setIdServizi(servizio.getIdServizi());
			PLFRServiziImpresaEntity en = impresaServiziRepository.findOne(key);
			if (en != null)
				servizio.setLinkCollegamentoImpresa(en.getLinkCollegamentoImpresa());
		}

		return servizio;
	}

	@Override
	public void salvaCollegamentoImpresaServiziStandard(PLFRServiziImpresaEntity impresaServiziStandard) throws Exception
	{
		PLFRServiziImpresaEntity entity = impresaServiziRepository.findOne(impresaServiziStandard.getCompositePrimaryKey());
		if (entity != null && entity.getIdServizi() != null && entity.getIdServizi().intValue() > 0)
			throw new Exception("Duplicate key");
		impresaServiziRepository.save(impresaServiziStandard);
	}

	@Override
	public void cancellaCollegamentoImpresaServiziStandard(PLFRServiziImpresaEntity impresaServiziStandard) throws Exception
	{
		impresaServiziRepository.delete(impresaServiziStandard);
	}

	
	@Override
	public long countServiziByDenominazione(BigDecimal idDenominazioneServizio)
	{
		return repository.countServiziByDenominazione(idDenominazioneServizio);
	}
}
