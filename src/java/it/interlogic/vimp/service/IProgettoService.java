package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.util.List;

public interface IProgettoService
{
	/**
	 * @return
	 */
	List<PLFProgettiProdottiEntity> find();

	/**
	 * @param id
	 * @return
	 */
	PLFProgettiProdottiEntity find(BigDecimal id);

	/**
	 * @param dettaglio
	 * @return
	 * @throws InformazioneDuplicataException
	 */
	PLFProgettiProdottiEntity update(PLFProgettiProdottiEntity dettaglio) throws InformazioneDuplicataException;

	/**
	 * @param id
	 * @return
	 */
	List<PLFProgettiProdottiEntity> findByImpresa(BigDecimal id);
	/**
	 * @param id
	 * @return
	 */
	List<PLFProgettiProdottiEntity> findByImpresaAttivi(BigDecimal id);

	/**
	 * @param id
	 * @return
	 */
	List<PLFCollaborazioniEntity> findCollaborazioniByProgetto(BigDecimal id);
	
	/**
	 * @param collaborazione
	 * @return
	 */
	PLFCollaborazioniEntity salvaCollaborazione(PLFCollaborazioniEntity collaborazione);
	/**
	 * @param collaborazione
	 */
	void cancellaCollaborazione(PLFCollaborazioniEntity collaborazione);
	/**
	 * @param id
	 * @return
	 */
	PLFCollaborazioniEntity findCollaborazioniById(BigDecimal id);
	/**
	 * @param idImpresa
	 * @param idProgetto
	 * @return
	 */
	PLFCollaborazioniEntity findCollaborazioniImpresaProdotto(BigDecimal idImpresa, BigDecimal idProgetto);
	/**
	 * @param partitaIva
	 * @param idProgetto
	 * @return
	 */
	PLFCollaborazioniEntity findCollaborazioniPartitaIvaProdotto(String partitaIva, BigDecimal idProgetto);
	/**
	 * @param codiceFiscale
	 * @param idProgetto
	 * @return
	 */
	PLFCollaborazioniEntity findCollaborazioniCodiceFiscaleProdotto(String codiceFiscale, BigDecimal idProgetto);

	/**
	 * @param dettaglio
	 * @return
	 */
	PLFProgettiProdottiEntity delete(PLFProgettiProdottiEntity dettaglio);

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

	/**
	 * @param allegato
	 */
	void cancellaAllegato(PLFProgettiProdottiAllegatiEntity allegato);
	/**
	 * @param allegato
	 * @return
	 */
	PLFProgettiProdottiAllegatiEntity salvaAllegato(PLFProgettiProdottiAllegatiEntity allegato);
	/**
	 * @param idAllegato
	 * @return
	 */
	PLFProgettiProdottiAllegatiEntity findAllegato(BigDecimal idAllegato);
	
	/**
	 * @param translation
	 * @return
	 */
	PLFProgettiProdottiAllegatiTranslationEntity saveAllegatiTranslation(PLFProgettiProdottiAllegatiTranslationEntity translation);

	/**
	 * @param id
	 * @return
	 */
	List<PLFProgettiProdottiEntity> findByProgettoProdotto(BigDecimal id);

	/**
	 * @param idPlfProgettoProdotto
	 * @return
	 */
	List<PLFProgettiProdottiAllegatiEntity> loadAllegati(BigDecimal idPlfProgettoProdotto);
	
	List<PLFProgettiProdottiEntity> findByImpresaAttiviNonScaduti(BigDecimal id);
}
