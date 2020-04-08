package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ITagService
{

	/**
	 * @return
	 */
	List<PLFTTagEntity> findAll();

	/**
	 * @return
	 */
	List<PLFTTagEntity> findTags();

	/**
	 * @param idInformazione
	 * @param tipoInformazione
	 * @return
	 */
	List<PLFTTagEntity> findByInfoAndType(BigDecimal idInformazione, BigDecimal tipoInformazione);

}
