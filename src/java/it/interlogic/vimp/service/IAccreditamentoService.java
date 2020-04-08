package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFRichiestaAccreditamentoEntity;

import java.math.BigDecimal;
import java.util.List;

public interface IAccreditamentoService
{

	/**
	 * @param dettaglio
	 * @return
	 */
	PLFRichiestaAccreditamentoEntity update(PLFRichiestaAccreditamentoEntity dettaglio);

	/**
	 * @return
	 */
	List<PLFRichiestaAccreditamentoEntity> find();

	/**
	 * @param id
	 * @return
	 */
	PLFRichiestaAccreditamentoEntity find(BigDecimal id);

	/**
	 * @param codiceFiscale
	 * @param partitaIva
	 * @return
	 */
	PLFRichiestaAccreditamentoEntity findRichiesta(String codiceFiscale, String partitaIva);

	/**
	 * @param codiceFiscaleRichiedente
	 * @param codiceFiscale
	 * @param partitaIva
	 * @param email
	 * @param idStatoImpresa
	 * @param impresa
	 * @param statoRichiesta
	 * @param idControllo
	 * @param flagAccreditamento
	 * @return
	 */
	PLFRichiestaAccreditamentoEntity salvaRichiesta(String codiceFiscaleRichiedente, String codiceFiscale, String partitaIva, String email, BigDecimal idStatoImpresa,
			PLFImpresaEntity impresa, int statoRichiesta, int idControllo, boolean flagAccreditamento);
}
