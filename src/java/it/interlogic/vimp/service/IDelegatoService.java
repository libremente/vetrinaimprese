package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface IDelegatoService
{

	/**
	 * @param idUtente
	 * @param idPlfImpresa
	 * @return
	 */
	PLFVDelegatoEntity findDelegato(BigDecimal idUtente, BigDecimal idPlfImpresa);

	/**
	 * @param idUtente
	 * @return
	 */
	List<PLFImpresaEntity> findImpreseDelegato(BigDecimal idUtente);

	/**
	 * @param idImpresa
	 * @return
	 */
	List<PLFVDelegatoEntity> findUtentiDelegati(BigDecimal idImpresa);

	/**
	 * @param delegato
	 * @param idImpresa
	 */
	void save(PLFTUtenteEntity delegato, BigDecimal idImpresa);

	/**
	 * @param idUtente
	 * @param idPlfImpresa
	 */
	void saveAssociazione(BigDecimal idUtente, BigDecimal idPlfImpresa);

	/**
	 * @param idPlfImpresa
	 * @param idUtente
	 */
	void revocaDelegato(BigDecimal idPlfImpresa, BigDecimal idUtente);

	/**
	 * @param parametri
	 * @return
	 */
	List<PLFTUtenteEntity> searchDelegati(Map<String, String> parametri);

	/**
	 * @param numPage
	 * @param pageSize
	 * @param filtri
	 * @return
	 */
	Page<PLFVDelegatoEntity> elencoDelegati(int numPage, int pageSize, CriteriQuery filtri);

	/**
	 * @param idUtente
	 * @return
	 */
	List<PLFServiziEntity> findServiziImpreseDelegato(BigDecimal idUtente);
}
