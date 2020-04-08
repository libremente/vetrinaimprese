package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTMercatiJpaRepository extends PagingAndSortingRepository<PLFTMercatiEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_MERCATI tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTMercatiEntity> findMercati();

	@Query(value = "SELECT tipo from PLFTMercatiEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTMercatiEntity> findMercatiPerCodice(@Param("codice") String codice);
	
	
	@Query(value = "SELECT * FROM PLF_T_MERCATI PR,PLF_R_IMPRESA_MERCATI RPR WHERE PR.ID = RPR.ID_MERCATI AND RPR.ID_PLF_IMPRESA = :idImpresa", nativeQuery = true)
	public abstract List<PLFTMercatiEntity> findByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);

}
