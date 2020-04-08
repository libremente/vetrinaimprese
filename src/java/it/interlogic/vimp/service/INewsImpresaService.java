package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.util.List;

public interface INewsImpresaService
{
	/**
	 * @return
	 */
	List<PLFNewsImpresaEntity> find();

	/**
	 * @param id
	 * @return
	 */
	PLFNewsImpresaEntity find(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFNewsImpresaEntity> findByImpresa(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFNewsImpresaEntity> findByImpresaAttive(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFNewsImpresaEntity> findByProgettoImpresa(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFNewsImpresaEntity> findByServizi(BigDecimal id);

	/**
	 * @param dettaglio
	 * @return
	 * @throws InformazioneDuplicataException
	 */
	PLFNewsImpresaEntity update(PLFNewsImpresaEntity dettaglio) throws InformazioneDuplicataException;

	/**
	 * @param dettaglio
	 * @return
	 */
	PLFNewsImpresaEntity delete(PLFNewsImpresaEntity dettaglio);

	/**
	 * @param idProgetto
	 */
	void deleteByProgettoImpresa(BigDecimal idProgetto);

	/**
	 * @param idServizio
	 */
	void deleteByServizi(BigDecimal idServizio);

	/**
	 * @param idInformazione
	 * @return
	 */
	byte[] getImage(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 * @param image
	 */
	void updateImage(BigDecimal idInformazione, String image);

	/**
	 * @param idInformazione
	 */
	void deleteImage(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 */
	void updateAttibuti(BigDecimal idInformazione);

}
