package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFLogEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFLogJpaRepository extends PagingAndSortingRepository<PLFLogEntity, BigDecimal>, JpaSpecificationExecutor<PLFLogEntity>
{

	@Query("SELECT i FROM PLFLogEntity i WHERE i.plfImpresa.idPlfImpresa = :idImpresa")
	List<PLFLogEntity> findLogImpresa(@Param("idImpresa") BigDecimal idImpresa);
}