package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTStatoImpresaJpaRepository extends PagingAndSortingRepository<PLFTStatoImpresaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_STATO_IMPRESA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTStatoImpresaEntity> findStatoImpresa();
	
	@Query(value = "SELECT a from PLFTStatoImpresaEntity a where a.codice = :codice")
	public abstract List<PLFTStatoImpresaEntity> findStatoImpresaByCodice(@Param("codice") String codice);
	
	
	@Query(value = "SELECT * FROM PLF_T_STATO_IMPRESA PR,PLF_R_OPPORTUNITA_STATO_IMPRESA RPR WHERE PR.ID = RPR.ID_STATO_IMPRESA AND RPR.ID_PLF_OPPORTUNITA = :idOpportunitaParam", nativeQuery = true)
	List<PLFTStatoImpresaEntity> findByIdOpportunita(@Param("idOpportunitaParam") BigDecimal idOpportunita);

}
