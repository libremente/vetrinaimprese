package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaStakeholderEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.util.List;

public interface IImpresaService
{
	/**
	 * @return
	 */
	List<PLFImpresaEntity> find();

	/**
	 * @param id
	 * @return
	 */
	PLFImpresaEntity find(BigDecimal id);

	/**
	 * @param partitaIva
	 * @param codiceFiscale
	 * @return
	 */
	PLFImpresaEntity find(String partitaIva, String codiceFiscale);

	/**
	 * @param dettaglio
	 * @return
	 * @throws InformazioneDuplicataException
	 */
	PLFImpresaEntity update(PLFImpresaEntity dettaglio) throws InformazioneDuplicataException;

	/**
	 * @param dettaglio
	 * @return
	 */
	PLFImpresaEntity updateImpresa(PLFImpresaEntity dettaglio);

	/**
	 * @param idServizio
	 * @return
	 */
	List<PLFImpresaEntity> loadImpreseServizi(BigDecimal idServizio);

	/**
	 * @param idStakeholder
	 * @return
	 */
	List<PLFImpresaEntity> loadImpreseStakeholderCollegate(BigDecimal idStakeholder);

	/**
	 * @param idProgetto
	 * @return
	 */
	List<PLFCollaborazioniEntity> loadCollaborazioniByIdProgetto(BigDecimal idProgetto);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFCollaborazioniEntity> loadCollaborazioniByIdImpresa(BigDecimal idImpresa);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFTMercatiEntity> loadMercati(BigDecimal idImpresa);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFTInnovazioneEntity> loadInnovazioni(BigDecimal idImpresa);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFImpresaEntity> loadStakeholder(BigDecimal idImpresa);

	/**
	 * @param idStakeholder
	 * @return
	 */
	List<PLFImpresaEntity> loadImpreseCollegate(BigDecimal idStakeholder);

	/**
	 * @return
	 */
	List<PLFImpresaEntity> getStakeholders();

	/**
	 * @return
	 */
	List<PLFImpresaEntity> getImprese();

	/**
	 * @param impresaStakeholder
	 * @throws Exception
	 */
	void salvaCollegamentoImpresaStakeholder(PLFRImpresaStakeholderEntity impresaStakeholder) throws Exception;

	/**
	 * @param impresaStakeholder
	 * @throws Exception
	 */
	void cancellaCollegamentoImpresaStakeholder(PLFRImpresaStakeholderEntity impresaStakeholder) throws Exception;

	/**
	 * @param impresaInnovazione
	 * @throws Exception
	 */
	void salvaCollegamentoImpresaInnovazione(PLFRImpresaInnovazioneEntity impresaInnovazione) throws Exception;

	/**
	 * @param impresaInnovazione
	 * @throws Exception
	 */
	void cancellaCollegamentoImpresaInnovazione(PLFRImpresaInnovazioneEntity impresaInnovazione) throws Exception;

	/**
	 * @param impresaMercati
	 * @throws Exception
	 */
	void salvaCollegamentoImpresaMercati(PLFRImpresaMercatiEntity impresaMercati) throws Exception;

	/**
	 * @param impresaMercati
	 * @throws Exception
	 */
	void cancellaCollegamentoImpresaMercati(PLFRImpresaMercatiEntity impresaMercati) throws Exception;

	/**
	 * @param idInformazione
	 * @return
	 */
	byte[] getImage(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 * @param image
	 */
	void updateImageImpresa(BigDecimal idInformazione, String image);

	/**
	 * @param idInformazione
	 * @param image
	 */
	void updateImageStakeholder(BigDecimal idInformazione, String image);

	/**
	 * @param idInformazione
	 */
	void deleteImageImpresa(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 */
	void deleteImageStakeholder(BigDecimal idInformazione);

	/**
	 * @param idInformazione
	 */
	void updateAttibuti(BigDecimal idInformazione);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFImpresaAllegatiEntity> loadAllegati(BigDecimal idImpresa);

	/**
	 * @param allegato
	 */
	void cancellaAllegato(PLFImpresaAllegatiEntity allegato);

	/**
	 * @param allegato
	 * @return
	 */
	PLFImpresaAllegatiEntity salvaAllegato(PLFImpresaAllegatiEntity allegato);

	/**
	 * @param idAllegato
	 * @return
	 */
	PLFImpresaAllegatiEntity findAllegato(BigDecimal idAllegato);

	/**
	 * @param translation
	 * @return
	 */
	PLFImpresaAllegatiTranslationEntity salvaTranslationAllegato(PLFImpresaAllegatiTranslationEntity translation);

}
