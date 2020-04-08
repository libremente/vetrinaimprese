package it.interlogic.vimp.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.interlogic.vimp.data.jpa.model.*;
import it.interlogic.vimp.data.jpa.model.relation.*;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.interlogic.vimp.data.dao.PLFProgettiProdottiAllegatiTranslationDao;
import it.interlogic.vimp.data.dao.PLFProgettiProdottiTranslationDao;
import it.interlogic.vimp.data.jpa.repository.PLFCollaborazioniJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiAllegatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreTecnologieJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRProgettoSettoreProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRProgettoSettoreTecnologieJpaRepository;
import it.interlogic.vimp.service.IProgettoService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

@Service("iProgettoService")
public class IProgettoServiceImpl extends IAbstractServiceImpl implements IProgettoService
{

	@Autowired
	PLFProgettiProdottiJpaRepository repository;

	@Autowired
	PLFProgettiProdottiAllegatiJpaRepository progettiProdottiAllegatiRepository;

	@Autowired
	PLFProgettiProdottiTranslationDao translation;

	@Autowired
	PLFProgettiProdottiAllegatiTranslationDao allegatiTranslation;

	@Autowired
	PLFCollaborazioniJpaRepository collaborazioniRepository;

	@Autowired
	PLFTSettoreProgettiProdottiJpaRepository settoreProgettiProdottiRepository;

	@Autowired
	PLFTSettoreTecnologieJpaRepository settoreTecnologieRepository;

	@Autowired
	PLFRProgettoSettoreProgettiProdottiJpaRepository progettoSettoreProgettiProdottiRepository;

	@Autowired
	PLFRProgettoSettoreTecnologieJpaRepository progettoSettoreTecnologieRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Override
	public List<PLFProgettiProdottiEntity> find()
	{
		return (List<PLFProgettiProdottiEntity>) repository.findAll();
	}

	@Override
	public PLFProgettiProdottiEntity find(BigDecimal id)
	{
		PLFProgettiProdottiEntity result = (PLFProgettiProdottiEntity) repository.findOne(id);
		if (result != null)
		{
			super.updateAttibuti(result.getIdPlfProgettiProdotti(), IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO);

			List<BigInteger> oldProg = settoreProgettiProdottiRepository.findIdsByProgetto(id);
			List<BigDecimal> toAddProg = new ArrayList<BigDecimal>();
			for (BigInteger bI : oldProg)
			{
				toAddProg.add(new BigDecimal(bI));
			}
			result.setElencoSettoreProgettiProdotti(toAddProg);

			result.setSettoriProgettiProdotti(settoreProgettiProdottiRepository.findByProgetto(id));

			List<BigInteger> oldTec = settoreTecnologieRepository.findIdsByProgetto(id);
			List<BigDecimal> toAddTec = new ArrayList<BigDecimal>();
			for (BigInteger bI : oldTec)
			{
				toAddTec.add(new BigDecimal(bI));
			}
			result.setElencoSettoreTecnologie(toAddTec);

			result.setSettoriTecnologie(settoreTecnologieRepository.findByProgetto(id));
		}

		return result;
	}

	@Override
	public PLFProgettiProdottiEntity update(PLFProgettiProdottiEntity dettaglio) throws InformazioneDuplicataException
	{
		if (dettaglio.getIdPlfProgettiProdotti() == null || dettaglio.getIdPlfProgettiProdotti().intValue() <= 0)
		{
			dettaglio.setIdPlfProgettiProdotti(null);
		}

		List<PLFProgettiProdottiEntity> result = repository.findProgettiByNome(dettaglio.getProgettiProdottiTranslation().getNomeProgettoProdotto());
		if (result != null && result.size() > 0)
		{
			// controllo se esiste con dettaglio NUOVO
			if (dettaglio.getIdPlfProgettiProdotti() == null)
			{
				throw new InformazioneDuplicataException();
			}
			else
			{
				// se è un aggiornamento
				for (PLFProgettiProdottiEntity prodotto : result)
				{
					if (!dettaglio.getIdPlfProgettiProdotti().equals(prodotto.getIdPlfProgettiProdotti()))
					{
						throw new InformazioneDuplicataException();
					}
				}
			}
		}

		translation.saveOrUpdateTranslation(dettaglio);

		if (dettaglio.getElencoSettoreProgettiProdotti() == null)
		{
			dettaglio.setElencoSettoreProgettiProdotti(new ArrayList<BigDecimal>());
		}
		if (dettaglio.getElencoSettoreTecnologie() == null)
		{
			dettaglio.setElencoSettoreTecnologie(new ArrayList<BigDecimal>());
		}

		updateSettoriProgettiProdotti(dettaglio);
		updateSettoriTecnologie(dettaglio);

		updateRTags(dettaglio);

		return dettaglio;
	}

	private void updateRTags(PLFProgettiProdottiEntity dettaglio)
	{
		// tags
		List<BigInteger> previous = informazioneTagJpaRepository
				.findIdsByInformazioneAndType(dettaglio.getIdPlfProgettiProdotti(), BigDecimal.valueOf(TIPO_INFO_PROGETTO_PRODOTTO));
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
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfProgettiProdotti(), BigDecimal.valueOf(TIPO_INFO_PROGETTO_PRODOTTO), id);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.delete(entity);
		}

		for (BigDecimal tag : toInsert)
		{
			PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdPlfProgettiProdotti(), BigDecimal.valueOf(TIPO_INFO_PROGETTO_PRODOTTO), tag);
			if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue() > 0)
				informazioneTagJpaRepository.save(entity);
		}
	}

	private void updateSettoriProgettiProdotti(PLFProgettiProdottiEntity progetto)
	{
		List<BigDecimal> oldSettoriProgettiProdotti = new ArrayList<BigDecimal>();
		List<BigInteger> oldList = settoreProgettiProdottiRepository.findIdsByProgetto(progetto.getIdPlfProgettiProdotti());
		for (BigInteger bI : oldList)
		{
			oldSettoriProgettiProdotti.add(new BigDecimal(bI));
		}
		List<BigDecimal> newSettoriProgettiProdotti = progetto.getElencoSettoreProgettiProdotti();
		List<BigDecimal> toDelete = new ArrayList<BigDecimal>();
		List<BigDecimal> toInsert = new ArrayList<BigDecimal>();
		for (BigDecimal o : oldSettoriProgettiProdotti)
		{
			toDelete.add(o);
		}
		for (BigDecimal n : newSettoriProgettiProdotti)
		{
			if (toDelete.contains(n))
			{
				toDelete.remove(n);
			}
			else
			{
				toInsert.add(n);
			}
		}
		for (BigDecimal d : toDelete)
		{
			progettoSettoreProgettiProdottiRepository.delete(new PLFRProgettoSettoreProgettiProdottiEntityKey(d, progetto.getIdPlfProgettiProdotti()));
		}
		for (BigDecimal n : toInsert)
		{
			progettoSettoreProgettiProdottiRepository.save(new PLFRProgettoSettoreProgettiProdottiEntity(n, progetto.getIdPlfProgettiProdotti()));
		}
	}

	private void updateSettoriTecnologie(PLFProgettiProdottiEntity progetto)
	{
		List<BigDecimal> oldSettoriTecnologie = new ArrayList<BigDecimal>();
		List<BigInteger> oldList = settoreTecnologieRepository.findIdsByProgetto(progetto.getIdPlfProgettiProdotti());
		for (BigInteger bI : oldList)
		{
			oldSettoriTecnologie.add(new BigDecimal(bI));
		}
		List<BigDecimal> newSettoriTecnologie = progetto.getElencoSettoreTecnologie();
		List<BigDecimal> toDelete = new ArrayList<BigDecimal>();
		List<BigDecimal> toInsert = new ArrayList<BigDecimal>();
		for (BigDecimal o : oldSettoriTecnologie)
		{
			toDelete.add(o);
		}
		for (BigDecimal n : newSettoriTecnologie)
		{
			if (toDelete.contains(n))
			{
				toDelete.remove(n);
			}
			else
			{
				toInsert.add(n);
			}
		}
		for (BigDecimal d : toDelete)
		{
			progettoSettoreTecnologieRepository.delete(new PLFRProgettoSettoreTecnologieEntityKey(d, progetto.getIdPlfProgettiProdotti()));
		}
		for (BigDecimal n : toInsert)
		{
			progettoSettoreTecnologieRepository.save(new PLFRProgettoSettoreTecnologieEntity(n, progetto.getIdPlfProgettiProdotti()));
		}
	}

	@Override
	public List<PLFProgettiProdottiEntity> findByImpresa(BigDecimal id)
	{
		return repository.findProgettiByIdImpresa(id);
	}

	@Override
	public List<PLFProgettiProdottiEntity> findByImpresaAttivi(BigDecimal id)
	{
		return repository.findProgettiByIdImpresaAttivi(id);
	}
	
	
	@Override
	public List<PLFProgettiProdottiEntity> findByImpresaAttiviNonScaduti(BigDecimal id)
	{
		return repository.findProgettiByIdImpresaAttiviNonScaduti(id);
	}

	@Override
	public List<PLFProgettiProdottiEntity> findByProgettoProdotto(BigDecimal id)
	{
		return repository.findProgettiByIdProgettoProdotto(id);
	}

	@Override
	public PLFProgettiProdottiEntity delete(PLFProgettiProdottiEntity dettaglio)
	{
		if (dettaglio.getIdPlfProgettiProdotti() == null || dettaglio.getIdPlfProgettiProdotti().intValue() > 0)
		{
			dettaglio.setDataCancellazione(new Date());
			repository.save(dettaglio);
		}
		return dettaglio;
	}

	@Override
	public List<PLFCollaborazioniEntity> findCollaborazioniByProgetto(BigDecimal id)
	{
		return collaborazioniRepository.findCollaborazioniByIdProgetto(id);
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO);
	}

	@Override
	public void updateImage(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO, image);
	}

	@Override
	public void deleteImage(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO);
	}

	@Override
	public PLFCollaborazioniEntity salvaCollaborazione(PLFCollaborazioniEntity collaborazione)
	{
		collaborazione = collaborazioniRepository.save(collaborazione);
		return collaborazione;
	}

	@Override
	public PLFCollaborazioniEntity findCollaborazioniById(BigDecimal id)
	{
		return collaborazioniRepository.findOne(id);
	}

	@Override
	public void cancellaCollaborazione(PLFCollaborazioniEntity collaborazione)
	{
		collaborazioniRepository.delete(collaborazione);
	}

	@Override
	public PLFCollaborazioniEntity findCollaborazioniImpresaProdotto(BigDecimal idImpresa, BigDecimal idProgetto)
	{
		List<PLFCollaborazioniEntity> list = collaborazioniRepository.findByImpresaProdotto(idImpresa, idProgetto);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public PLFCollaborazioniEntity findCollaborazioniPartitaIvaProdotto(String partitaIva, BigDecimal idProgetto)
	{
		List<PLFCollaborazioniEntity> list = collaborazioniRepository.findByPartitaIvaProdotto(partitaIva, idProgetto);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public PLFCollaborazioniEntity findCollaborazioniCodiceFiscaleProdotto(String codiceFiscale, BigDecimal idProgetto)
	{
		List<PLFCollaborazioniEntity> list = collaborazioniRepository.findByCodiceFiscaleProdotto(codiceFiscale, idProgetto);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<PLFProgettiProdottiAllegatiEntity> loadAllegati(BigDecimal idProgettoProdotto)
	{
		return progettiProdottiAllegatiRepository.findByProgettoProdotto(idProgettoProdotto);
	}

	@Override
	public void cancellaAllegato(PLFProgettiProdottiAllegatiEntity allegato)
	{
		allegatiTranslation.deleteAllegatoTranslation(allegato);
		// progettiProdottiAllegatiRepository.delete(allegato); // Original code
	}

	@Override
	public PLFProgettiProdottiAllegatiEntity salvaAllegato(PLFProgettiProdottiAllegatiEntity allegato)
	{
		allegatiTranslation.saveOrUpdateAllegatoTranslation(allegato);
		return allegato;
	}

	@Override
	public PLFProgettiProdottiAllegatiEntity findAllegato(BigDecimal idAllegato)
	{
		return progettiProdottiAllegatiRepository.findOne(idAllegato);
	}

	@Override
	public PLFProgettiProdottiAllegatiTranslationEntity saveAllegatiTranslation(PLFProgettiProdottiAllegatiTranslationEntity translation)
	{
		return allegatiTranslation.saveOrUpdateTranslation(translation);
	}

}
