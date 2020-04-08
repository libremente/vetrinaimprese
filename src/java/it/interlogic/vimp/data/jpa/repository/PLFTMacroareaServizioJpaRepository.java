package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.interlogic.vimp.data.jpa.model.PLFTMacroareaServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;

public interface PLFTMacroareaServizioJpaRepository extends PagingAndSortingRepository<PLFTMacroareaServiziEntity, BigDecimal> {

	@Query(value = "SELECT t FROM PLFTMacroareaServiziEntity t WHERE dataFine IS NULL")
	List<PLFTTipoErogazioneServizioEntity> findTipiErogazioneServizio();

	@Query(value = "SELECT TM.ID_MACROAREA FROM PLF_R_SERVIZI_MACROAREA TM WHERE TM.ID_SERVIZI = :idServizio", nativeQuery = true)
	List<BigDecimal> findIdsByServizio(@Param("idServizio") BigDecimal idServizio);

	@Query(value = "SELECT MS.* FROM PLF_T_MACROAREA_SERVIZI MS " +
			"JOIN PLF_R_SERVIZI_MACROAREA ASS ON ASS.ID_MACROAREA = MS.ID " +
			"WHERE ASS.ID_SERVIZI = :idServizio", nativeQuery = true)
	List<PLFTMacroareaServiziEntity> findByServizio(@Param("idServizio") BigDecimal idServizio);

}
