package it.interlogic.vimp.service;

import java.math.BigDecimal;
import java.util.List;

import it.interlogic.vimp.data.jpa.model.PLFOpportunitaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFROpportunitaStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFROpportunitaStatoImpresaEntityKey;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

public interface IOpportunitaService
{
	/**
	 * @return
	 */
	List<PLFOpportunitaEntity> find();

	/**
	 * @param id
	 * @return
	 */
	PLFOpportunitaEntity find(BigDecimal id);
	
	/**
	 * @param dettaglio
	 * @return
	 * @throws InformazioneDuplicataException
	 */
	PLFOpportunitaEntity update(PLFOpportunitaEntity dettaglio) throws InformazioneDuplicataException;
	
	/**
	 * @param dettaglio
	 * @return
	 */
	PLFOpportunitaEntity delete(PLFOpportunitaEntity dettaglio);
	
	
	/**
	 * @param opportunitaStatoImpresa
	 */
	void salvaCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa);
	/**
	 * @param opportunitaStatoImpresa
	 * @return
	 */
	PLFROpportunitaStatoImpresaEntity findCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntityKey opportunitaStatoImpresa);
	/**
	 * @param opportunitaStatoImpresa
	 */
	void cancellaCollegamentoOpportunitaStatoImpresa(PLFROpportunitaStatoImpresaEntity opportunitaStatoImpresa);
	
	/**
	 * @param idOpportunita
	 * @return
	 */
	List<PLFTStatoImpresaEntity> loadOpportunitaStatoImpresa(BigDecimal idOpportunita);

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
