package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.model.PLFVImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFVInformazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFVRichiestaAccreditamentoEntity;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.web.dto.ParametriRicercaMyInfo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IRicercaService
{

	/**
	 * @param numPage
	 * @param pageSize
	 * @param ricerca
	 * @param onlyPublic
	 * @return
	 */
	Page<PLFVInformazioneEntity> findInformazioni(int numPage, int pageSize, String ricerca, boolean onlyPublic);

	/**
	 * @param numPage
	 * @param pageSize
	 * @param tipoInformazione
	 * @param ricerca
	 * @param onlyPublic
	 * @return
	 */
	Page<PLFVInformazioneEntity> findInformazioni(int numPage, int pageSize, BigDecimal tipoInformazione, String ricerca, boolean onlyPublic);
	
	
	List<PLFVInformazioneEntity> findInformazioniPersonal(ParametriRicercaMyInfo parametri);

	/**
	 * @param numPage
	 * @param pageSize
	 * @param tipoInformazione
	 * @param ricerca
	 * @param stato
	 * @param onlyPublic
	 * @return
	 */
	Page<PLFVInformazioneEntity> findInformazioniByStato(int numPage, int pageSize, BigDecimal tipoInformazione, String ricerca, BigDecimal[] stato, boolean onlyPublic);

	/**
	 * @param numPage
	 * @param pageSize
	 * @param tipoInformazione
	 * @param ricerca
	 * @param onlyPublic
	 * @return
	 */
	Page<PLFVInformazioneEntity> findInformazioniByTipo(int numPage, int pageSize, BigDecimal[] tipoInformazione, String ricerca, boolean onlyPublic);
	
	/**
	 * @param numPage
	 * @param pageSize
	 * @param tipoInformazione
	 * @param ricerca
	 * @param stato
	 * @param onlyPublic
	 * @return
	 */
	Page<PLFVInformazioneEntity> findInformazioniByTipoStato(int numPage, int pageSize, BigDecimal[] tipoInformazione, String ricerca, BigDecimal[] stato, boolean onlyPublic);

	/**
	 * @return
	 */
	long countProdotti();

	/**
	 * @return
	 */
	long countProdottiTecnologie();

	/**
	 * @return
	 */
	long countStartup();

	/**
	 * @return
	 */
	long countPmi();

	/**
	 * @return
	 */
	long countProgetti();

	/**
	 * @return
	 */
	long countSpinoff();

	/**
	 * @return
	 */
	long countGrandi();

	/**
	 * @param numPage
	 * @param pageSize
	 * @param filtri
	 * @return
	 */
	Page<PLFVImpresaEntity> elencoImprese(int numPage, int pageSize, CriteriQuery filtri);

	/**
	 * @param numPage
	 * @param pageSize
	 * @return
	 */
	Page<PLFLogEntity> elencoBatchLog(int numPage, int pageSize);

	/**
	 * @param numPage
	 * @param pageSize
	 * @param filtri
	 * @return
	 */
	Page<PLFVRichiestaAccreditamentoEntity> elencoRichiesteAccreditamento(int numPage, int pageSize, CriteriQuery filtri);
}
