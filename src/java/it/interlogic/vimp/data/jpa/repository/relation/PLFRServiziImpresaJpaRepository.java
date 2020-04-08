package it.interlogic.vimp.data.jpa.repository.relation;

import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziImpresaEntityKey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFRServiziImpresaJpaRepository extends PagingAndSortingRepository<PLFRServiziImpresaEntity, PLFRServiziImpresaEntityKey>
{
	@Query(value = "SELECT SI.ID_PLF_IMPRESA FROM PLF_R_SERVIZI_IMPRESA SI WHERE SI.ID_SERVIZI = :idServizio", nativeQuery = true)
	List<BigDecimal> findIdsByServizio(@Param("idServizio") BigDecimal id);

}
