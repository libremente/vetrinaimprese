package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTOrigineImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTOrigineImpresaJpaRepository extends PagingAndSortingRepository<PLFTOrigineImpresaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_ORIGINE_IMPRESA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTOrigineImpresaEntity> findOrigineImpresa();
	
	@Query(value = "SELECT a from PLFTOrigineImpresaEntity a where a.codice = :codice")
	public abstract List<PLFTOrigineImpresaEntity> findOrigineImpresaByCodice(@Param("codice") String codice);

}
