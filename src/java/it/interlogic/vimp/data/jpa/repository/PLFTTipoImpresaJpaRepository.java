package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoImpresaJpaRepository extends PagingAndSortingRepository<PLFTTipoImpresaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_IMPRESA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoImpresaEntity> findTipoImpresa();
	
	@Query(value = "SELECT a from PLFTTipoImpresaEntity a where a.codice = :codice")
	public abstract List<PLFTTipoImpresaEntity> findTipoImpresaByCodice(@Param("codice") String codice);

}
