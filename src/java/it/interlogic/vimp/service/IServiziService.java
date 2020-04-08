package it.interlogic.vimp.service;

import java.math.BigDecimal;
import java.util.List;

import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

public interface IServiziService
{
	/**
	 * @return
	 */
	List<PLFServiziEntity> find();

	/**
	 * @return
	 */
	List<PLFServiziEntity> findAttivi();

	/**
	 * @param id
	 * @return
	 */
	PLFServiziEntity find(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFServiziEntity> findByImpresa(BigDecimal id);

	/**
	 * @param dettaglio
	 * @return
	 * @throws InformazioneDuplicataException
	 */
	PLFServiziEntity update(PLFServiziEntity dettaglio) throws InformazioneDuplicataException;

	/**
	 * @param dettaglio
	 * @return
	 */
	PLFServiziEntity delete(PLFServiziEntity dettaglio);

	/**
	 * @param idImpresa
	 * @param pubblicati
	 * @return
	 */
	List<PLFServiziEntity> loadServiziByImpresa(BigDecimal idImpresa, boolean pubblicati);

	/**
	 * @param idImpresa
	 * @param pubblicato
	 * @return
	 */
	List<PLFServiziEntity> loadServiziAttiviByImpresa(BigDecimal idImpresa, boolean pubblicato);
	

	/**
	 * @param idServizioStandard
	 * @param idImpresa
	 * @return
	 */
	PLFServiziEntity loadServizioStandardForImpresa(BigDecimal idServizioStandard, BigDecimal idImpresa);

	/**
	 * @param impresaServiziStandard
	 * @throws Exception
	 */
	void salvaCollegamentoImpresaServiziStandard(PLFRServiziImpresaEntity impresaServiziStandard) throws Exception;

	/**
	 * @param impresaServiziStandard
	 * @throws Exception
	 */
	void cancellaCollegamentoImpresaServiziStandard(PLFRServiziImpresaEntity impresaServiziStandard) throws Exception;

	/**
	 * @param idInformazione
	 * @return
	 */
	byte[] getImage(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 * @param image
	 */
	void updateImage(BigDecimal idInformazione,String  image);

	/**
	 * @param idInformazione
	 */
	void deleteImage(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 */
	void updateAttibuti(BigDecimal idInformazione);
	
	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFServiziEntity> findAllByImpresa(BigDecimal idImpresa);
	
	
	long countServiziByDenominazione(BigDecimal idDenominazioneServizio);

}
