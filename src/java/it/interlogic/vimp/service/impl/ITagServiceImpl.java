package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.repository.PLFTTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRInformazioneTagJpaRepository;
import it.interlogic.vimp.service.ITagService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iTagService")
public class ITagServiceImpl extends IAbstractServiceImpl implements ITagService
{

	@Autowired
	PLFTTagJpaRepository tagJpaRepository;

	@Autowired
	PLFRInformazioneTagJpaRepository informazioneTagJpaRepository;

	@Override
	public List<PLFTTagEntity> findAll()
	{
		return (List<PLFTTagEntity>) tagJpaRepository.findAll();
	}

	@Override
	public List<PLFTTagEntity> findTags()
	{
		return (List<PLFTTagEntity>) tagJpaRepository.findTags();
	}

	@Override
	public List<PLFTTagEntity> findByInfoAndType(BigDecimal idInformazione, BigDecimal tipoInformazione)
	{
		List<PLFTTagEntity> tags = new ArrayList<PLFTTagEntity>();
		List<BigInteger> list = informazioneTagJpaRepository.findIdsByInformazioneAndType(idInformazione, tipoInformazione);

		for (BigInteger rId : list)
		{
			PLFTTagEntity tag = tagJpaRepository.findOne(new BigDecimal(rId));
			if (tag != null && tag.getId() != null && tag.getId().intValue()>0)
				tags.add(tag);
		}

		return tags;
	}
}
