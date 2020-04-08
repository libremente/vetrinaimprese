package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.dao.PLFNewsImpresTranslationDao;
import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.repository.PLFNewsImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.service.INewsImpresaService;
import it.interlogic.vimp.service.ITagService;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("iNewsImpresaService")
public class INewsImpresaServiceImpl extends IAbstractServiceImpl implements INewsImpresaService
{

	@Autowired
	PLFNewsImpresaJpaRepository repository;

	@Autowired
	PLFNewsImpresTranslationDao translation;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Autowired
	protected ITagService tagService;

	@Override
	public List<PLFNewsImpresaEntity> find()
	{
		return (List<PLFNewsImpresaEntity>) repository.findAll();
	}

	@Override
	public PLFNewsImpresaEntity find(BigDecimal id)
	{
		PLFNewsImpresaEntity result = (PLFNewsImpresaEntity) repository.findOne(id);
		if (result != null)
			super.updateAttibuti(result.getIdNewsImpresa(), IAbstractServiceImpl.TIPO_INFO_NEWS);
		return result;
	}

	@Override
	public PLFNewsImpresaEntity update(PLFNewsImpresaEntity dettaglio) throws InformazioneDuplicataException
	{
		if (dettaglio.getIdNewsImpresa() == null || dettaglio.getIdNewsImpresa().intValue() <= 0)
		{
			dettaglio.setIdNewsImpresa(null);
		}

		translation.saveOrUpdateTranslation(dettaglio);
		

		updateRTags(dettaglio);
		return dettaglio;
	}

	private void updateRTags(PLFNewsImpresaEntity dettaglio)
	{
		// tags
		List<BigDecimal> toDelete = new ArrayList<BigDecimal>();
		List<BigDecimal> toInsert = new ArrayList<BigDecimal>();

		List<BigInteger> previous = informazioneTagJpaRepository.findIdsByInformazioneAndType(dettaglio.getIdNewsImpresa(), BigDecimal.valueOf(TIPO_INFO_NEWS));

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

		if (toDelete != null)
		{
			for (BigDecimal id : toDelete)
			{
				if (id != null && id.intValue() > 0)
				{
					PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdNewsImpresa(), BigDecimal.valueOf(TIPO_INFO_NEWS), id);
					if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue()>0)
						informazioneTagJpaRepository.delete(entity);
				}
			}
		}

		if (toInsert != null)
		{
			for (BigDecimal tag : toInsert)
			{
				if (tag != null && tag.intValue() > 0)
				{
					PLFRInformazioneTagEntity entity = new PLFRInformazioneTagEntity(dettaglio.getIdNewsImpresa(), BigDecimal.valueOf(TIPO_INFO_NEWS), tag);
					if (entity != null && entity.getIdInformazione() != null && entity.getIdInformazione().intValue()>0)
						informazioneTagJpaRepository.save(entity);
				}
			}
		}
	}

	@Override
	public PLFNewsImpresaEntity delete(PLFNewsImpresaEntity dettaglio)
	{
		if (dettaglio.getIdNewsImpresa() == null || dettaglio.getIdNewsImpresa().intValue() > 0)
		{
			dettaglio.setDataCancellazione(new Date());
			repository.save(dettaglio);
		}
		return dettaglio;
	}

	@Override
	public byte[] getImage(BigDecimal idInformazione)
	{
		return super.getImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_NEWS);
	}

	@Override
	public void updateImage(BigDecimal idInformazione, String image)
	{
		super.updateImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_NEWS, image);
	}

	@Override
	public void deleteImage(BigDecimal idInformazione)
	{
		super.deleteImage(idInformazione, IAbstractServiceImpl.TIPO_INFO_NEWS);
	}

	@Override
	public void updateAttibuti(BigDecimal idInformazione)
	{
		super.updateAttibuti(idInformazione, IAbstractServiceImpl.TIPO_INFO_NEWS);
	}

	@Override
	public List<PLFNewsImpresaEntity> findByImpresa(BigDecimal id)
	{
		return repository.findNewsImpresaByImpresa(id);
	}

	@Override
	public List<PLFNewsImpresaEntity> findByImpresaAttive(BigDecimal id)
	{
		return repository.findNewsImpresaByImpresaAttive(id);
	}

	@Override
	public List<PLFNewsImpresaEntity> findByProgettoImpresa(BigDecimal id)
	{
		return repository.findNewsImpresaByProgettoImpresa(id);
	}

	@Override
	public List<PLFNewsImpresaEntity> findByServizi(BigDecimal id)
	{
		return repository.findNewsImpresaByServizi(id);
	}

	@Override
	@Transactional
	public void deleteByProgettoImpresa(BigDecimal idProgetto)
	{
		List<PLFNewsImpresaEntity> list = repository.findNewsByIdProgetto(idProgetto);
		if (list != null && list.size() > 0)
		{
			Date today = new Date();
			for (PLFNewsImpresaEntity news : list)
			{

				if (news.getIdNewsImpresa() != null)
				{
					List<PLFTTagEntity> tags = tagService.findByInfoAndType(news.getIdNewsImpresa(), BigDecimal.valueOf(IAbstractServiceImpl.TIPO_INFO_NEWS));
					news.setTags(tags);

					List<BigDecimal> ids = new ArrayList<BigDecimal>();

					for (PLFTTagEntity tag : tags)
					{
						ids.add(tag.getId());
					}
					news.setElencoIdTags(ids);
				}

				try
				{
					news.setDataCancellazione(today);
					update(news);
				}
				catch (InformazioneDuplicataException e)
				{
				}
			}
		}
	}

	@Override
	public void deleteByServizi(BigDecimal idServizio)
	{
		List<PLFNewsImpresaEntity> list = repository.findNewsByIdServizio(idServizio);
		if (list != null && list.size() > 0)
		{
			Date today = new Date();
			for (PLFNewsImpresaEntity news : list)
			{
				try
				{
					news.setDataCancellazione(today);
					update(news);
				}
				catch (InformazioneDuplicataException e)
				{
				}
			}
		}
	}

}
