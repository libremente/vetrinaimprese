package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;

import org.springframework.data.repository.query.Param;

public interface PLFTTipoErogazioneServizioJpaRepository extends PagingAndSortingRepository<PLFTTipoErogazioneServizioEntity, BigDecimal> {

	@Query(value = "SELECT t FROM PLFTTipoErogazioneServizioEntity t WHERE dataFine IS NULL")
	List<PLFTTipoErogazioneServizioEntity> findTipiErogazioneServizio();

	@Query(value = "SELECT TES.ID_TIPO_EROGAZIONE FROM PLF_R_SERVIZI_TIPO_EROGAZIONE TES WHERE TES.ID_SERVIZI = :idServizio", nativeQuery = true)
	List<BigDecimal> findIdsByServizio(@Param("idServizio") BigDecimal idServizio);

	@Query(value = "SELECT TES.* FROM PLF_T_TIPO_EROGAZIONE_SERVIZIO TES " +
			"JOIN PLF_R_SERVIZI_TIPO_EROGAZIONE ASS ON ASS.ID_TIPO_EROGAZIONE = TES.ID " +
			"WHERE ASS.ID_SERVIZI = :idServizio", nativeQuery = true)
	List<PLFTTipoErogazioneServizioEntity> findByServizio(@Param("idServizio") BigDecimal idServizio);
	
	
	
	@Query(value = "SELECT tipo from PLFTTipoErogazioneServizioEntity tipo where UPPER(tipo.descrizione) like :descrizione")
	public abstract List<PLFTTipoErogazioneServizioEntity> findTipiErogazioneServizioPerDescrizione(@Param("descrizione") String descrizione);

}
